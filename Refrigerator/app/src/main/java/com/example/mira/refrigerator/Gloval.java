package com.example.mira.refrigerator;

/**
 * Created by MiRa on 2015-11-26.
 */
// 전역클래스
public final class Gloval {

    // 냉장인지 냉동인지 넘길때 필요함
    public static final int INTENT_ERROR = 100;
    public static final int INTENT_COLD = 101;
    public static final int INTENT_FREEZE = 102;

    // 추가할때 캘린더 다이얼로그 번호
    public static final int DATE_DIALOG_BUY = 201;
    public static final int DATE_DIALOG_LAST = 202;
    // 캘린더 두개중에 뭔지 알아보기
    public static final int DATEPICKER_ID_BUY = 203;
    public static final int DATEPICKER_ID_LAST = 204;
    public static final int DATEPICKER_ID_ALL = 205;

    // 스피너뷰 클릭시 캘린더 업뎃할때 Day인지 Month인지 구분
    public static final int SPINNER_CHANGE_DAY = 301;
    public static final int SPINNER_CHANGE_MONTH = 302;

    // 데이터베이스
    public static final String DATABASE_NAME = "Refrigerator";
    public static final int DATABASE_VERSION = 1;
    public static final  String TABLE_NAME = "Refrigerator";
}
