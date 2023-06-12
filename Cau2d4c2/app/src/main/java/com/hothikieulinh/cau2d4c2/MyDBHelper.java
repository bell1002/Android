package com.hothikieulinh.cau2d4c2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.hothikieulinh.models.Student;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper
{
    public static final String DB_NAME = "student_db.db";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Student";
    public static final String COL_ID = "StudentId";
    public static final String COL_NAME = "StudentName";
    public static final String COL_CLASS = "StudentClass";

    public MyDBHelper(@Nullable Context context){
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql="create table if not exists " + TBL_NAME + "(" + COL_NAME + " varchar(50), " + COL_CLASS + " varchar(50), " + COL_ID + " varchar(15) primary key)";
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
            exeSQL("insert into " + TBL_NAME + " values('Pham Thi Ly', 'Cong nghe thong tin K61',6151071069)");
            exeSQL("insert into " + TBL_NAME + " values('Nguyen Thi Nhat Phuong', 'Cong nghe thong tin K61',6151071085)");
            exeSQL("insert into " + TBL_NAME + " values('Ho Thi Kieu Linh', 'Cong nghe thong tin K61',6151071066)");
            exeSQL("insert into " + TBL_NAME + " values('Pham Cao Dai An', 'Ke toan ',248576732)");


        }

    }

    public List<Student> getAllEmployee() {
        List<Student> students=new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TBL_NAME, null);
        while (cursor.moveToNext()) {
            String sName = cursor.getString(0);
            String sClass = cursor.getString(1);
            String sId = cursor.getString(2);


            Student e = new Student(sName,sClass,sId);
            students.add(e);
        }
        cursor.close();
        return students;
    }


    public long insertStudent(String StudentName, String StudentClass, String StudentId){
         SQLiteDatabase db=getWritableDatabase();
         ContentValues values=new ContentValues();
         values.put("StudentName", StudentName);
         values.put("StudentClass", StudentClass);
         values.put("StudentId", StudentId);
         long id=db.insert(TBL_NAME, null, values);
         db.close();
         return id;

    }
}
