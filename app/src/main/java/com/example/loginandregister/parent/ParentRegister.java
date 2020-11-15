package com.example.loginandregister.parent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.loginandregister.Login;
import com.example.loginandregister.MySingleton;
import com.example.loginandregister.R;
import com.example.loginandregister.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ParentRegister extends AppCompatActivity {

    private Button btnRegister;
    private TextView linkLogin;
    private EditText fullname, email, password, childName, gradeChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_register);

        btnRegister = findViewById(R.id.btnRegister);
        linkLogin = findViewById(R.id.linkLogin);
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        childName = findViewById(R.id.childName);
        gradeChild = findViewById(R.id.gradeChild);
        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentRegister.this, Login.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });
    }
    private void Regist(){

        btnRegister.setVisibility(View.VISIBLE);

        final String mFullname = fullname.getText().toString().trim();
        final String mEmail = email.getText().toString().trim();
        final String mPassword = password.getText().toString().trim();
        final String mChildName = childName.getText().toString().trim();
        final String mGradeChild = gradeChild.getText().toString().trim();

        if (TextUtils.isEmpty(mFullname)) {
            fullname.setError("Please enter fullname");
            fullname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mEmail)) {
            email.setError("Please enter your email");
            email.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mPassword)) {
            password.setError("Enter a password");
            password.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(mChildName)) {
            childName.setError("Enter a child name");
            childName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mGradeChild)) {
            gradeChild.setError("Enter a child grade");
            gradeChild.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_REGISTER_PARENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if(success.equals("2")){
                                Toast.makeText(ParentRegister.this, "Email already exist!", Toast.LENGTH_SHORT).show();
                            }
                            if(success.equals("1")){
                                Toast.makeText(ParentRegister.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(ParentRegister.this, ParentHome.class);
                                intent.putExtra("childName",mChildName);
                                intent.putExtra("gradeChild",mGradeChild);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ParentRegister.this, "Registered Error!"+e.toString(), Toast.LENGTH_SHORT).show();
                            btnRegister.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ParentRegister.this, "Registered Error!"+error.toString(), Toast.LENGTH_SHORT).show();
                        btnRegister.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();

                params.put("fullname",mFullname);
                params.put("email",mEmail);
                params.put("password",mPassword);
                params.put("childName",mChildName);
                params.put("gradeChild",mGradeChild);
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    }
