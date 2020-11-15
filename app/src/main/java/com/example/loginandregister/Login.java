package com.example.loginandregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.loginandregister.parent.ParentHome;
import com.example.loginandregister.parent.ParentRegister;
import com.example.loginandregister.student.StudentHome;
import com.example.loginandregister.student.StudentRegister;
import com.example.loginandregister.teacher.TeacherHome;
import com.example.loginandregister.teacher.TeacherRegister;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button btnLogin;
    private TextView linkRegister;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        linkRegister = findViewById(R.id.loginRegister);

        Intent intentType = getIntent();
        type = intentType.getStringExtra("type");

        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("teacher")) {
                    Intent intent = new Intent(Login.this, TeacherRegister.class);
                    startActivity(intent);
                }else if(type.equals("student")){
                    Intent intent = new Intent(Login.this, StudentRegister.class);
                    startActivity(intent);
                }else if(type.equals("parent")){
                    Intent intent = new Intent(Login.this, ParentRegister.class);
                    startActivity(intent);
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {

        btnLogin.setVisibility(View.GONE);
        final String mEmail = email.getText().toString().trim();
        final String mPass = password.getText().toString().trim();

        if (TextUtils.isEmpty(mEmail)) {
            email.setError("Please enter your username");
            email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mPass)) {
            password.setError("Please enter your password");
            password.requestFocus();
            return;
        }

        if(type.equals("teacher")){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN_TEACHER,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("STR>>>>", response);

                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                JSONArray jsonArray = jsonObject.getJSONArray("login");

                                for (int i = 0; i <jsonArray.length() ; i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String fullname = object.getString("fullname").trim();
                                    Intent intent = new Intent(Login.this, TeacherHome.class);
                                    intent.putExtra("fullname",fullname);
                                    startActivity(intent);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(Login.this, "User is not exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            btnLogin.setVisibility(View.VISIBLE);
                            Toast.makeText(Login.this, "User is not exist", Toast.LENGTH_SHORT).show();
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String>params = new HashMap<>();
                    params.put("email",mEmail);
                    params.put("password",mPass);
                    return params;
                }
            };
            MySingleton.getInstance(this).addToRequestQueue(stringRequest);

        }else if(type.equals("Student")){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN_STUDENT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("login");

                                for (int i = 0; i <jsonArray.length() ; i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String fullname = object.getString("fullname").trim();
                                    String grade = object.getString("grade").trim();

                                    Intent intent = new Intent(Login.this, StudentHome.class);
                                    intent.putExtra("fullname",fullname);
                                    intent.putExtra("grade",grade);
                                    startActivity(intent);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                btnLogin.setVisibility(View.VISIBLE);
                                Toast.makeText(Login.this, "User is not exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            btnLogin.setVisibility(View.VISIBLE);
                            Toast.makeText(Login.this, "User is not exist", Toast.LENGTH_SHORT).show();
                        }
                    })
            {
                @Override
                protected Map<String, String>getParams() throws AuthFailureError{
                    Map<String, String>params = new HashMap<>();
                    params.put("email",mEmail);
                    params.put("password",mPass);
                    return params;
                }
            };
            MySingleton.getInstance(this).addToRequestQueue(stringRequest);

        }else if(type.equals("Parent")){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN_PARENT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("login");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String childName = object.getString("fullname").trim();
                                    String gradeChild = object.getString("grade").trim();
                                    Intent intent = new Intent(Login.this, ParentHome.class);
                                    intent.putExtra("childName", childName);
                                    intent.putExtra("gradeChild",gradeChild);
                                    startActivity(intent);
                                    finish();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                btnLogin.setVisibility(View.VISIBLE);
                                Toast.makeText(Login.this, "User is not exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            btnLogin.setVisibility(View.VISIBLE);
                            Toast.makeText(Login.this, "User is not exist", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", mEmail);
                    params.put("password", mPass);
                    return params;
                }
            };
            MySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }
    }
}
