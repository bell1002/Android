package com.hothikieulinh.cau2d3c2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.hothikieulinh.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "product_db.db";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Product";
    public static final String COL_ID = "ProductId";
    public static final String COL_NAME = "ProductName";
    public static final String COL_PRICE = "ProductPrice";

    public MyDBHelper(@Nullable Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table if not exists " + TBL_NAME + "(" + COL_ID + " varchar(15) primary key, " + COL_NAME + " varchar(30), " + COL_PRICE + " real)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists " + TBL_NAME);
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
        Cursor cursor=queryData(sql);
        int numOfRow=cursor.getCount();
        cursor.close();
        return numOfRow;
    }
    public void createSampleData(){
        int num=getNumberOfRow("select * from " + TBL_NAME);
        if(num==0){
            exeSQL("insert into " + TBL_NAME + " values('MH13562','Thuoc tru sau', 17400)");
            exeSQL("insert into " + TBL_NAME + " values('MH13678','Thuoc tru sau duc rang', 105420)");
            exeSQL("insert into " + TBL_NAME + " values('MH18362','Thuoc tru ti bug', 80000)");
            exeSQL("insert into " + TBL_NAME + " values('MH38362','Thuoc tru sau duc tham', 18700)");
            exeSQL("insert into " + TBL_NAME + " values('MH15762','Thuoc tru bo', 19300)");

        }
    }
    public List<Product> getAllProduct(){
        List<Product> products=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(TBL_NAME, null, null, null, null, null,null);
        while(cursor.moveToNext()){
            String pId=cursor.getString(0);
            String pName=cursor.getString(1);
            Double pPrice=cursor.getDouble(2);

            Product p=new Product(pId, pName, pPrice);
            products.add(p);
        }
        cursor.close();
        return products;
    }
    public long updateProduct(String ProductId, String ProductName, Double ProductPrice){
        SQLiteDatabase db=getWritableDatabase();
        ;
        ContentValues values=new ContentValues();
        values.put("ProductName",ProductName );
        values.put("ProductPrice", ProductPrice);
        long id=db.update(TBL_NAME, values, COL_ID +"=?", new String[]{String.valueOf(ProductId.toString())});
        db.close();
        return id;
    }
}
