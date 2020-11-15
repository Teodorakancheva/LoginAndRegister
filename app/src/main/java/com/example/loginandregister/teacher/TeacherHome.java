package com.example.loginandregister.teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.loginandregister.R;

public class TeacherHome extends AppCompatActivity {

    private TextView fullname;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        fullname = findViewById(R.id.techName);
        email = findViewById(R.id.email);

        Intent intentFullname = getIntent();
        Intent intentGrade = getIntent();
        String mFullname = intentFullname.getStringExtra("fullname");
        String mGrade = intentGrade.getStringExtra("email");
        fullname.setText(mFullname);
        email.setText(mGrade);
    }
}