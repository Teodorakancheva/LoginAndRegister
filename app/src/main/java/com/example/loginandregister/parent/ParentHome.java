package com.example.loginandregister.parent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.loginandregister.R;

public class ParentHome extends AppCompatActivity {

    private TextView studentName;
    private TextView studentGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home);

        studentName = findViewById(R.id.studName);
        studentGrade = findViewById(R.id.studGrade);

        Intent intentName = getIntent();
        Intent intentGread = getIntent();
        String mChildName = intentName.getStringExtra("childName");
        String mChildGrade = intentGread.getStringExtra("gradeChild");
        studentName.setText(mChildName);
        studentGrade.setText(mChildGrade);
    }
}