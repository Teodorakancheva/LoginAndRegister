package com.example.loginandregister.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.loginandregister.R;

public class StudentHome extends AppCompatActivity {

    private TextView viewFullname, viewGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        viewFullname = findViewById(R.id.studName);
        viewGrade = findViewById(R.id.studGrade);

        Intent intentFullname = getIntent();
        Intent intentGrade = getIntent();
        String mFullname = intentFullname.getStringExtra("fullname");
        String mGrade = intentGrade.getStringExtra("grade");
        viewFullname.setText(mFullname);
        viewGrade.setText(mGrade);
    }
}