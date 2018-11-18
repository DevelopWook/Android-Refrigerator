package com.example.mira.refrigerator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by MiRa on 2015-11-26.
 */
public class MyDataBase extends SQLiteOpenHelper {

//    private final String mDataBaseName = "RefrigeratorDB";

    public MyDataBase(Context context) {
        super(context, Gloval.DATABASE_NAME, null, Gloval.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 데이터베이스가 만들어질때 호출되는 메소드
        try{
            // 테이블 삭제
            String drop_sql = "drop table if exists " + Gloval.TABLE_NAME;
            db.execSQL(drop_sql);
        }catch(Exception ex){
            Log.d("pbw", "MyDataBase.onCreate 테이블 삭제 오류");
        }

        // 테이블 생성하기
        String create_sql = "create table " + Gloval.TABLE_NAME + "("
                            + "no integer primary key autoincrement, "
                            + "location text not null, "
                            + "fraction text not null, "
                            + "name text not null, "
                            + "buyday text not null, "
                            + "lastday text not null, "
                            + "image text"
                            + ")";

        try{
            db.execSQL(create_sql);
        }catch(Exception ex){
            Log.d("pbw", "MyDataBase.onCreate 테이블 생성 오류");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("pbw", "MyDataBase.onUpgrade 메소드 호출됨");
    }

    public void insert(Product product){
        SQLiteDatabase db = getWritableDatabase();

        String sql = "insert into " + Gloval.TABLE_NAME
                + "(location, fraction, name, buyday, lastday, image) "
                + "values ('" + product.getLocation() + "', "
                + "'" + product.getFraction() + "', "
                + "'" + product.getName() + "', "
                + "'" + product.getBuyDay() + "', "
                + "'" + product.getLastDay() + "', "
                + "'" + product.getImage() + "');";

        try{
            db.execSQL(sql);
        }catch(Exception ex){
            Log.d("pbw", "MyDataBase.insert() 오류");
        }

    }


    public ArrayList<Product> selectCold(){
        ArrayList<Product> products = new ArrayList<Product>();
        Product product = new Product();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from " + Gloval.TABLE_NAME + " where location='cold'";
//        String sql = "select * from " + Gloval.TABLE_NAME;
//        String sql = "select location, fraction, name, buyday, lastday, image from " + Gloval.TABLE_NAME;

        try{
            Cursor cursor = db.rawQuery(sql, null);

            while(cursor.moveToNext()){
                product = new Product();
                // no가 있기때문에 1부터일듯
                product.setLocation(cursor.getString(1));
                product.setFraction(cursor.getString(2));
                product.setName(cursor.getString(3));
                product.setBuyDay(cursor.getString(4));
                product.setLastDay(cursor.getString(5));
                product.setImage(cursor.getString(6));

                products.add(product);
            }
        }catch(Exception ex){
            Log.d("pbw", "MyDataBase.selectCold() 오류");
        }

        return products;
    }
    public ArrayList<Product> selectFreeze(){
        ArrayList<Product> products = new ArrayList<Product>();
        Product product;
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from " + Gloval.TABLE_NAME + " where location='freeze'";

        try {
            Cursor cursor = db.rawQuery(sql, null);

            while (cursor.moveToNext()) {
                product = new Product();
                // no가 있기때문에 1부터일듯
                product.setLocation(cursor.getString(1));
                product.setFraction(cursor.getString(2));
                product.setName(cursor.getString(3));
                product.setBuyDay(cursor.getString(4));
                product.setLastDay(cursor.getString(5));
                product.setImage(cursor.getString(6));

                products.add(product);
            }
        }catch(Exception ex){
            Log.d("pbw", "MyDataBase.selectFreeze 오류");
        }

        return products;
    }

}
