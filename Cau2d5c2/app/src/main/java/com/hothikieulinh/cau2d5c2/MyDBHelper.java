package com.hothikieulinh.cau2d5c2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.hothikieulinh.models.Book;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "book_db.db";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Book";
    public static final String COL_ID = "BookId";
    public static final String COL_NAME = "BookName";
    public static final String COL_PRICE = "BookPrice";

    public MyDBHelper(@Nullable Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table if not exists " + TBL_NAME + " (" + COL_ID + " varchar(15) primary key ," + COL_NAME + " varchar(30) , " + COL_PRICE + " real)";
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
            exeSQL("insert into " + TBL_NAME + " values('Book-111', 'Cong nghe thong tin K61',6151071069)");
            exeSQL("insert into " + TBL_NAME + " values('Book-112', 'Cong nghe thong tin , an toan bao mat',6151071085)");
            exeSQL("insert into " + TBL_NAME + " values('Book-113', 'Bao Nghe',6151071066)");
            exeSQL("insert into " + TBL_NAME + " values('Book-114', 'Ke toan 1',248576732)");


        }

    }
    public List<Book> getAllBook(){
        List<Book> books=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(TBL_NAME, null, null, null, null,null, null);
        while(cursor.moveToNext()){
            String bId=cursor.getString(0);
            String bName=cursor.getString(1);
            Double bPrice=cursor.getDouble(2);

            Book b=new Book(bId, bName, bPrice);
            books.add(b);
        }
        cursor.close();
        return books;
    }
    public long insertBook(String BookId, String BookName, Double BookPrice){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("BookId", BookId);
        values.put("BookName", BookName);
        values.put("BookPrice", BookPrice);

        long id=db.insert(TBL_NAME, null, values);
        db.close();
        return id;
    }

}
