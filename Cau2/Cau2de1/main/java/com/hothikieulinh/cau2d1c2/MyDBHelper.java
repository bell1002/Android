package com.hothikieulinh.cau2d1c2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hothikieulinh.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "employee_db.db";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Employee";
    public static final String COL_ID = "EmployeeId";
    public static final String COL_NAME = "EmployeeName";
    public static final String COL_AGE = "EmployeeAge";

    public MyDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + "(" + COL_ID + " varchar(15) PRIMARY KEY, " + COL_NAME + " VARCHAR(50), " + COL_AGE + " INTEGER )";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TBL_NAME);
    }

    public void exeSQL(String sql) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    public Cursor queryData(String sql) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(sql, null);

    }

    public int getNumberOfRow(String sql) {
        Cursor c = queryData(sql);
        int numbOfRow = c.getCount();
        c.close();
        return numbOfRow;
    }

    public void createSampleData() {
        int numb = getNumberOfRow("select * from " + TBL_NAME);
        if (numb == 0) {
            exeSQL("insert into " + TBL_NAME + " values('NV-112', 'Tran Dai Nghia',34)");
            exeSQL("insert into " +TBL_NAME+ " values('NV-113', 'Tran Dai Quang',32)");
            exeSQL("insert into " +TBL_NAME+ " values('NV-114', 'Nguyen Trong Tin',36)");
            exeSQL("insert into " +TBL_NAME+ " values('NV-115', 'Ho Tan Tai',24)");
            exeSQL("insert into " +TBL_NAME+ " values('NV-116', 'Tran Van Hop',32)");
            exeSQL("insert into " +TBL_NAME+ " values('NV-117', 'Nhan Van Thin',39)");

        }

    }

    public List<Employee> getAllEmployee() {
        List<Employee> employees=new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TBL_NAME, null);
        while (cursor.moveToNext()) {
            String eId = cursor.getString(0);
            String eName = cursor.getString(1);
            int eAge = cursor.getInt(2);
            Employee e = new Employee(eId, eName, eAge);
            employees.add(e);
        }
        cursor.close();
        return employees;
    }

    public long deleteEmployee(String EmployeeId){
        SQLiteDatabase db=getWritableDatabase();
        long id=db.delete(TBL_NAME, COL_ID + "=?", new String[]{String.valueOf(EmployeeId.toString())});
       // db.execSQL("delete from "+TBL_NAME +" where EmployeeId=? ", new String[]{String.valueOf(EmployeeId)});

        return id;
    }

}
