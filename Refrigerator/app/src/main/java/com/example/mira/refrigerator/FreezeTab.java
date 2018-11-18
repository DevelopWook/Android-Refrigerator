package com.example.mira.refrigerator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/*
    냉동탭
 */
public class FreezeTab extends AppCompatActivity {

    private MyDataBase myDB;
    ArrayList<Product> products;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeze_tab);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "냉동식품 추가", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent = new Intent(getBaseContext(), AddCold.class);
                intent.putExtra("content", Gloval.INTENT_FREEZE);
                startActivity(intent);
            }
        });

        myRun();

    }

    private void myRun(){
        myDB = new MyDataBase(this);

        products = myDB.selectFreeze();
        ArrayList<MyListViewItems> itemList = new ArrayList<MyListViewItems>();
        MyListViewItems item;
        for(Product p : products){
            item = new MyListViewItems();
            item.setName(p.getName());
            item.setBuyDay(p.getBuyDay());
            item.setLastDay(p.getLastDay());
            itemList.add(item);
        }

        listView = (ListView)findViewById(R.id.listViewFreeze);

        final MyListViewAdapter myAdapter = new MyListViewAdapter(this, R.layout.mylist, itemList);
        listView.setAdapter(myAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


    }
}
