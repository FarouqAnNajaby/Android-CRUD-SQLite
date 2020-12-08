package com.farouq.myuts.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.farouq.myuts.Model.Makanan;

import java.util.ArrayList;
import java.util.List;

public class MyDbHelper extends SQLiteOpenHelper {

    private Context context;
    SQLiteDatabase db;

    private static final String DATABASE_NAME = "FoodLibrary.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Farouq" ;


    public MyDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+
                "kode_makanan  varchar (20) primary key, "+
                "nama_makanan varhcar(50)," +
                "asal_makanan varchar(50)," +
                "bahan_makanan varchar(100)," +
                "carabuat_makanan varchar (100));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public int tambahMakanan(Makanan makanan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kode_makanan", makanan.getKode_makanan());
        values.put("nama_makanan", makanan.getNama_makanan());
        values.put("asal_makanan", makanan.getAsal_makanan());
        values.put("bahan_makanan", makanan.getBahan_makanan());
        values.put("carabuat_makanan",makanan.getCaraBuat_makanan());
        db.insert(TABLE_NAME, null, values);
        db.close();
        return 1;
    }

    public List<Makanan> getAllFood() {
        List<Makanan> userList = new ArrayList<>();
        String getAllQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(getAllQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Makanan makanan = new Makanan(
                        cursor.getString(cursor.getColumnIndex("kode_makanan")),
                        cursor.getString(cursor.getColumnIndex("nama_makanan")),
                        cursor.getString(cursor.getColumnIndex("asal_makanan")),
                        cursor.getString(cursor.getColumnIndex("bahan_makanan")),
                        cursor.getString(cursor.getColumnIndex("carabuat_makanan"))
                );
                userList.add(makanan);
            } while (cursor.moveToNext());
        }
        db.close();
        return userList;
    }

    public ArrayList<Makanan> getMakanan(String id){
        ArrayList<Makanan> listMakan = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE kode_makanan = '"+id+"'", null);
        int i = 0;
        if (cur.getCount() > 0) cur.moveToFirst();
        while (i < cur.getCount()){
            Makanan makanan = new Makanan(
                    cur.getString(cur.getColumnIndex("kode_makanan")),
                    cur.getString(cur.getColumnIndex("nama_makanan")),
                    cur.getString(cur.getColumnIndex("asal_makanan")),
                    cur.getString(cur.getColumnIndex("bahan_makanan")),
                    cur.getString(cur.getColumnIndex("carabuat_makanan"))
            );
            listMakan.add(makanan);
            cur.moveToNext();
            i++;
        }
        db.close();
        return listMakan;
    }

    public int hapus_makanan(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "kode_makanan = '"+id+"'", null);
        db.close();

        return 1;
    }

    public void update(String kode, String nama, String asal, String bahan,String cara) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_NAME + " SET nama_makanan = '"+nama+"'," +
                "asal_makanan = '"+asal+"', bahan_makanan = '" +bahan+"', carabuat_makanan = '"+cara+"'"+
                "WHERE " +
                "kode_makanan = '"+kode+"'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

}
