package com.farouq.myuts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.farouq.myuts.Database.MyDbHelper;
import com.farouq.myuts.Model.Makanan;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private EditText edtKode,edtNama,edtAsal,edtBahan,edtCara;
    private ImageView btnHapus;
    private Button btnUbah;

    private ArrayList<Makanan> listMakan;
    String id;
    MyDbHelper db = new MyDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        edtKode = findViewById(R.id.kodeMakan);
        edtNama = findViewById(R.id.namaMakan);
        edtAsal = findViewById(R.id.asalMakan);
        edtBahan = findViewById(R.id.bahanMakan);
        edtCara = findViewById(R.id.caraBuatMakan);
        btnHapus = findViewById(R.id.hapusData);
        btnUbah = findViewById(R.id.btnUbah);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapus();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Makanan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent i = getIntent();
        id = i.getStringExtra("id");

        MyDbHelper db = new MyDbHelper(this);
        listMakan = db.getMakanan(id);
        setMakan();

    }

    private void edit() {
        if(edtNama.getText().toString().length()==0) {
            edtNama.setError("Nama Makanan diperlukan!");
        }else if(edtAsal.getText().toString().length()==0) {
            edtAsal.setError("Asal Makanan diperlukan!");
        }else if(edtBahan.getText().toString().length()==0) {
            edtBahan.setError("Bahan Makanan diperlukan!");
        }else if(edtCara.getText().toString().length()==0) {
            edtCara.setError("Bahan Makanan diperlukan!");
        }else{
            db.update(edtKode.getText().toString().trim(),
                    edtNama.getText().toString().trim(),
                    edtAsal.getText().toString().trim(),
                    edtBahan.getText().toString().trim(),
                    edtCara.getText().toString().trim());
            Toast.makeText(this, "Ubah Berhasil", Toast.LENGTH_SHORT).show();
            finish();
            Intent i = new Intent(DetailActivity.this,MainActivity.class);
            startActivity(i);
        }
    }

    private void hapus() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Anda yakin ingin menghapus?");
        builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (db.hapus_makanan(id) == 1){
                    Toast.makeText(DetailActivity.this, "Berhasil menghapus", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(DetailActivity.this, MainActivity.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(in);
                }
            }
        }).setNegativeButton("Batal", null).show();
    }

    private void setMakan() {
        edtNama.setText(listMakan.get(0).getNama_makanan());
        edtKode.setText(listMakan.get(0).getKode_makanan());
        edtAsal.setText(listMakan.get(0).getAsal_makanan());
        edtBahan.setText(listMakan.get(0).getBahan_makanan());
        edtCara.setText(listMakan.get(0).getCaraBuat_makanan());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}