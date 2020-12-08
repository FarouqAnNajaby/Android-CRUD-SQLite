package com.farouq.myuts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.farouq.myuts.Adapter.MakananAdapter;
import com.farouq.myuts.Database.MyDbHelper;
import com.farouq.myuts.Model.Makanan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MakananAdapter makananAdapter;
    private FloatingActionButton addbutton;
    private RecyclerView recyclerView;
    private MyDbHelper db;
    private List<Makanan> makananList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        recyclerView = findViewById(R.id.rv);

        MyDbHelper myDbHelper = new MyDbHelper(this);
        myDbHelper.getWritableDatabase();

        db = new MyDbHelper(this);
        makananList.addAll(db.getAllFood());

        makananAdapter = new MakananAdapter(this,makananList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(makananAdapter);

        addbutton = findViewById(R.id.btnAdd);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this,FormAddActivity.class);
                    startActivity(i);
            }
        });
    }
}