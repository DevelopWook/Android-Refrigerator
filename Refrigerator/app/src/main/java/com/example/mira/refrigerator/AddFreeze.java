package com.example.mira.refrigerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddFreeze extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_freeze);

        myRun();
    }

    private void myRun(){
        setTitle("냉동 추가");
    }
}
