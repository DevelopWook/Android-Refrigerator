package com.example.mira.refrigerator;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;

/*
    상품 추가 Activity
 */
public class AddCold extends AppCompatActivity {

    private MyDataBase myDB;

    private int whoIntent;
    private ToggleButton toggleButton;
    private EditText editTextBuyDay;
    private EditText editTextLastDay;
    private EditText editTextName;

    private String nowSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cold);

        myRun();
    }

    private void myRun() {
        setTitle("추가하기");

        // 인텐트로 넘어온 데이타 받기
        Intent intent = new Intent(this.getIntent());
        whoIntent = intent.getIntExtra("content", Gloval.INTENT_ERROR);

        // 초기화작업
        ini();

        // 어느 탭에서 들어왔는지 파악
        switch(whoIntent){
            case Gloval.INTENT_COLD:
                // 냉장추가
                // 토글버튼 냉장으로
                toggleButton.setChecked(true);
//                addCold();
                break;
            case Gloval.INTENT_FREEZE:
                // 냉동추가
                // 토글버튼 냉동으로
                toggleButton.setChecked(false);
//                addFreeze();
                break;
            case Gloval.INTENT_ERROR:
                // 에러 (니까 밑으로패스)
            default:
                // 에러
                Toast.makeText(getBaseContext(), "에러입니다", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

//    private void addCold(){
////        Toast.makeText(getBaseContext(), "addCold()", Toast.LENGTH_SHORT).show();
//        if(editTextName.getText().toString().trim().equals("")){
//            Toast.makeText(this, "이름칸이 비었습니다(냉장추가)", Toast.LENGTH_SHORT).show();
//            return;
//        }
//    }
//    private void addFreeze(){
////        Toast.makeText(getBaseContext(), "addFreeze()", Toast.LENGTH_SHORT).show();
//        if(editTextName.getText().toString().trim().equals("")){
//            Toast.makeText(this, "이름칸이 비었습니다(냉동추가)", Toast.LENGTH_SHORT).show();
//            return;
//        }
//    }


    public void onClickBtnAdd(View v){
        if(editTextName.getText().toString().trim().equals("")){
            Toast.makeText(this, "이름칸이 비었습니다", Toast.LENGTH_SHORT).show();
            return;
        }

        // DB 초기화
        myDB = new MyDataBase(this);


        String location;
        if(Gloval.INTENT_COLD == whoIntent){
            location = "cold";
        }
        else{
            location = "freeze";
        }
        Product product = new Product();
        product.setLocation(location);
        product.setFraction(nowSpinner);
        product.setName(editTextName.getText().toString());
        product.setBuyDay(editTextBuyDay.getText().toString());
        product.setLastDay(editTextLastDay.getText().toString());
        product.setImage("nullImage");

        myDB.insert(product);
        Log.d("pbw", "insert 완료 : " + product.getName());

        Toast.makeText(this, product.getName()+" 추가되었습니다.", Toast.LENGTH_SHORT).show();

        finish();
    }
    public void onClickBtnReturn(View v){
        finish();
    }


    private void ini(){
        // 변수들 초기화
        toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
        editTextBuyDay = (EditText)findViewById(R.id.editTextBuyDay);
        editTextLastDay = (EditText)findViewById(R.id.editTextLastDay);
        editTextName = (EditText)findViewById(R.id.editTextName);

        // 토글버튼 리스너 달기
        iniToggle();
        // 캘린더 초기화
        iniCalendar();
        // 스피너뷰 초기화
        iniSpinner();

        // 이미지 뷰 기본이미지 설정
        Drawable dr = getResources().getDrawable(android.R.drawable.ic_menu_camera);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageDrawable(dr);
    }


    // 토글버튼 리스너달기
    private void iniToggle(){
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Gloval.INTENT_COLD == whoIntent) {
                    whoIntent = Gloval.INTENT_FREEZE;
                } else {
                    whoIntent = Gloval.INTENT_COLD;
                }
            }
        });
    }
    // 스피너뷰 초기화 하기
    ArrayList<String> spinnerArraylist;
    private void iniSpinner(){
        spinnerArraylist = new ArrayList<String>();
        spinnerArraylist.add("고기류"); // 0
        spinnerArraylist.add("생선류"); // 1
        spinnerArraylist.add("달걀"); // 2
        spinnerArraylist.add("잎채소"); // 3
        spinnerArraylist.add("뿌리채소"); // 4
        spinnerArraylist.add("기타"); // 5

        nowSpinner = spinnerArraylist.get(0);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, spinnerArraylist);

        //스피너 속성
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getBaseContext(), spinnerArraylist.get(position) + " : " + position , Toast.LENGTH_LONG).show();//해당목차눌렸을때

                // nowSpinner  변경
                nowSpinner = spinnerArraylist.get(position);

                switch (position){
                    case 0:
                        // 고기류
                        // 고기류는 냉장 4일, 냉동 5개월
                        if(Gloval.INTENT_COLD == whoIntent){
                            plusDay(Gloval.SPINNER_CHANGE_DAY, 4);
                        }else{
                            plusDay(Gloval.SPINNER_CHANGE_MONTH, 5);
                        }
                        break;
                    case 1:
                        // 생선류
                        // 생선류는 냉장3일, 냉동 3개월
                        if(Gloval.INTENT_COLD == whoIntent){
                            plusDay(Gloval.SPINNER_CHANGE_DAY, 3);
                        }else{
                            plusDay(Gloval.SPINNER_CHANGE_MONTH, 3);
                        }
                        break;
                    case 2:
                        // 달걀
                        // 달걀은 냉장 5주
                        plusDay(Gloval.SPINNER_CHANGE_DAY, 35);
                        break;
                    case 3:
                        // 잎채소
                        // 잎채소는 냉장 4일
                        plusDay(Gloval.SPINNER_CHANGE_DAY, 4);
                        break;
                    case 4:
                        // 뿌리채소
                        // 뿌리채소는 냉장 2주
                        plusDay(Gloval.SPINNER_CHANGE_DAY, 14);
                        break;
                    case 5:
                        // 기타
                        // 기타는 알아서 유통기한 설정
                        // 일단은 내일로 잡아둠
                        plusDay(Gloval.SPINNER_CHANGE_DAY, 1);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    // 캘린더 초기화하기
    private int mBuyYear;
    private int mBuyMonth;
    private int mBuyDay;
    private int mLastYear;
    private int mLastMonth;
    private int mLastDay;
    private void iniCalendar(){
        editTextBuyDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(Gloval.DATE_DIALOG_BUY);
            }
        });
        editTextLastDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(Gloval.DATE_DIALOG_LAST);
            }
        });

        final Calendar c = Calendar.getInstance();
        mBuyYear = c.get(Calendar.YEAR);
        mBuyMonth = c.get(Calendar.MONTH);
        mBuyDay = c.get(Calendar.DAY_OF_MONTH);
        datePickerNumber = Gloval.DATEPICKER_ID_BUY;
        updateCalender();

    }

    // 캘린더를 위한 다이얼로그
    public int datePickerNumber = 0;
    @Override
    protected Dialog onCreateDialog(int id) {
        DatePickerDialog datePickerDialog = null;

        switch (id){
            case Gloval.DATE_DIALOG_BUY:
                datePickerDialog = new DatePickerDialog(this,
                        mBuyDateSetListener, mBuyYear, mBuyMonth, mBuyDay);
                datePickerNumber = Gloval.DATEPICKER_ID_BUY;
                break;
            case Gloval.DATE_DIALOG_LAST:
                datePickerDialog = new DatePickerDialog(this,
                        mBuyDateSetListener, mLastYear, mLastMonth, mLastDay);
                datePickerNumber = Gloval.DATEPICKER_ID_LAST;
                break;
            default:
                break;
        }

//        return super.onCreateDialog(id);
        return datePickerDialog;
    }
    // DatePicker 리스너
    private DatePickerDialog.OnDateSetListener mBuyDateSetListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            // 구매일자 인지 유통기한인지 구분 후에 값 할당.
            if(Gloval.DATEPICKER_ID_BUY == datePickerNumber){
                mBuyYear = year;
                mBuyMonth = monthOfYear;
                mBuyDay = dayOfMonth;
            }
            else{
                mLastYear = year;
                mLastMonth = monthOfYear;
                mLastDay = dayOfMonth;
            }

            updateCalender();
        }
    };

    private void updateCalender(){

        if(Gloval.DATEPICKER_ID_BUY == datePickerNumber){
            editTextBuyDay.setText(
                    new StringBuffer()
                            .append(mBuyYear).append("-")
                            .append(mBuyMonth+1).append("-")
                            .append(mBuyDay).append(" ")
            );
        }
        else{
            editTextLastDay.setText(
                    new StringBuffer()
                            .append(mLastYear).append("-")
                            .append(mLastMonth+1).append("-")
                            .append(mLastDay).append(" ")
            );
        }

    }

    private void plusDay(int flag, int plus){
        int tmp_Year = mBuyYear;
        int tmp_Month = mBuyMonth;
        int tmp_Day = mBuyDay;

        if(Gloval.SPINNER_CHANGE_DAY == flag){
            // plus 날짜인경우
            // 귀찮아서 모든달을 31일로 가정하고 계산합니다.

            tmp_Day += plus;
        }
        else{
            // plus 개월인경우
            tmp_Month += plus;
        }

        while(!(tmp_Day <= 31)) {
            tmp_Day -= 31;
            tmp_Month += 1;
        }
        while(!(tmp_Month <= 12)){
            tmp_Month -= 12;
            tmp_Year += 1;
        }

        // 유통기한설정
        mLastYear = tmp_Year;
        mLastMonth = tmp_Month;
        mLastDay = tmp_Day;

        datePickerNumber = Gloval.DATEPICKER_ID_LAST;
        updateCalender();
    }
}
