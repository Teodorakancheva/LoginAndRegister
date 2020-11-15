package com.example.loginandregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnStudent;
    private Button btnTeacher;
    private Button btnParent;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStudent = findViewById(R.id.btnStudent);
        btnTeacher = findViewById(R.id.btnTeacher);
        btnParent = findViewById(R.id.btnParent);

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "student";
                Intent intent = new Intent(MainActivity.this, Login.class);
                intent.putExtra("type","student");
                startActivity(intent);
            }
        });

        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "teacher";
                Intent intent = new Intent(MainActivity.this, Login.class);
                intent.putExtra("type","teacher");
                startActivity(intent);
            }
        });

        btnParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "parent";
                Intent intent = new Intent(MainActivity.this, Login.class);
                intent.putExtra("type","parent");
                startActivity(intent);
            }
        });
    }

}