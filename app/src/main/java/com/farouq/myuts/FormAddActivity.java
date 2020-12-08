package com.farouq.myuts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.farouq.myuts.Database.MyDbHelper;
import com.farouq.myuts.Model.Makanan;

public class FormAddActivity extends AppCompatActivity {

    EditText edt_nama,edt_asal,edt_bahan,edt_kode,edt_cara;
    Button btnSimpan,btnBatal;
    String namaMakanan,kodeMakanan,asalMakanan,bahanMakanan,carabuatMakanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add);

        edt_nama = findViewById(R.id.namaMakanan);
        edt_kode = findViewById(R.id.kodeMakanan);
        edt_asal = findViewById(R.id.asalMakanan);
        edt_bahan = findViewById(R.id.bahanMakanan);
        edt_cara = findViewById(R.id.carabuatMakanan);
        btnSimpan = findViewById(R.id.btnSave);
        btnBatal = findViewById(R.id.btnCancel);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tambah Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namaMakanan = edt_nama.getText().toString();
                kodeMakanan = edt_kode.getText().toString();
                asalMakanan = edt_asal.getText().toString();
                bahanMakanan = edt_bahan.getText().toString();
                carabuatMakanan = edt_cara.getText().toString();

                Makanan userMakan = new Makanan(kodeMakanan,
                        namaMakanan, asalMakanan, bahanMakanan,carabuatMakanan);

                MyDbHelper db = new MyDbHelper(FormAddActivity.this);

                if(edt_nama.getText().toString().length()==0) {
                    edt_nama.setError("Nama Makanan diperlukan!");
                }else if(edt_kode.getText().toString().length()==0) {
                    edt_kode.setError("Kode Makanan diperlukan!");
                }else if(edt_asal.getText().toString().length()==0) {
                    edt_asal.setError("Asal Makanan diperlukan!");
                }else if(edt_bahan.getText().toString().length()==0) {
                    edt_bahan.setError("Deskripsi Makanan diperlukan!");
                }else if(edt_cara.getText().toString().length()==0) {
                    edt_cara.setError("Deskripsi Makanan diperlukan!");
                }else{
                    if (db.tambahMakanan(userMakan) == 1){
                        Toast.makeText(FormAddActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                        clear();
                        Intent i = new Intent(FormAddActivity.this, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                }
            }
        });
    }

    void clear(){
        edt_cara.setText("");
        edt_asal.setText("");
        edt_nama.setText("");
        edt_bahan.setText("");
        edt_kode.setText("");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}