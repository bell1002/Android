package com.hothikieulinh.cau2de2c2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.hothikieulinh.models.Phone;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="phone_db.db";
    public static final int DB_VERSION=1;
    public static final String TB_NAME="Phone";
    public static final String COL_ID="PhoneId";
    public static final String COL_NAME="PhoneName";
    public static final String COL_PRICE="PhonePrice";

    public MyDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table if not exists " + TB_NAME + "(" + COL_ID + " varchar(15) primary key, " + COL_NAME + " varchar(50), " + COL_PRICE + " real)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TB_NAME);
    }
    public void exeSQL(String sql){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }
    public Cursor queryData(String sql){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        return sqLiteDatabase.rawQuery(sql, null);

    }
    public int getNumberOfRow(String sql){
        Cursor c=queryData(sql);
        int numOfRow=c.getCount();
        c.close();
        return numOfRow;
    }
    public void createSampleData(){
        int numb=getNumberOfRow("select * from " + TB_NAME);
        if(numb==0){
            exeSQL("insert into " + TB_NAME + " values('SP-110', 'IPhone 1',100000)");
            exeSQL("insert into " + TB_NAME + " values('SP-111', 'IPhone 3',300000)");
            exeSQL("insert into " + TB_NAME + " values('SP-112', 'IPhone 5',200000)");
            exeSQL("insert into " + TB_NAME + " values('SP-113', 'IPhone 7',900000)");
            exeSQL("insert into " + TB_NAME + " values('SP-114', 'IPhone 9',100000)");
            exeSQL("insert into " + TB_NAME + " values('SP-115', 'IPhone 2',700000)");
            exeSQL("insert into " + TB_NAME + " values('SP-116', 'IPhone 6',800000)");

        }
    }
    public List<Phone> getAllPhone(){
        ArrayList<Phone> phones=new ArrayList<Phone>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(TB_NAME, null, null, null, null, null , null);
        while(cursor.moveToNext()){
            String pId=cursor.getString(0);
            String pName=cursor.getString(1);
            Double pPrice=cursor.getDouble(2);
            Phone p=new Phone(pId, pName, pPrice);
            phones.add(p);
        }
        cursor.close();
        return  phones;
    }
    public void deletePhone(String PhoneId){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("delete from " +TB_NAME + " where PhoneId=? ", new String[]{String.valueOf(PhoneId)});
    }
}
