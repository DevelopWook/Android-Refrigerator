package com.example.mira.refrigerator;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
//import android.widget.Toolbar;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "추가기능", Snackbar.LENGTH_SHORT)
//                        .setAction("Action", null).show();
//            }
//        });


        /////////////////////////////////////////////
        myRun();
    }

    private void myRun() {
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        // 냉장 탭 추가
        intent = new Intent().setClass(this, ColdTab.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        spec = tabHost.newTabSpec("tab1").setIndicator("냉장").setContent(intent);
        tabHost.addTab(spec);

        // 냉동 탭 추가
        intent = new Intent().setClass(this, FreezeTab.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        spec = tabHost.newTabSpec("tab2").setIndicator("냉동").setContent(intent);
        tabHost.addTab(spec);

        // 설정 탭 추가
        intent = new Intent().setClass(this, SettingsTab.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        spec = tabHost.newTabSpec("tab3").setIndicator("설정").setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
}
