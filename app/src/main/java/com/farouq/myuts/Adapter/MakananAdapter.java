package com.farouq.myuts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.farouq.myuts.DetailActivity;
import com.farouq.myuts.Model.Makanan;
import com.farouq.myuts.R;

import java.util.List;

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.MyViewHolder> {

    private List<Makanan> makananList;
    private Context context;

    public MakananAdapter(Context context,List<Makanan> makananList) {
        this.makananList = makananList;
        this.context = context;
    }

    @NonNull
    @Override
    public MakananAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_makanan,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MakananAdapter.MyViewHolder holder, int position) {
        Makanan makanan = makananList.get(position);

        holder.tv_nama.setText(makanan.getKode_makanan()+" "+makanan.getNama_makanan());
        holder.tv_kode.setText(makanan.getKode_makanan());
        holder.tv_asal.setText(makanan.getAsal_makanan());

    }

    @Override
    public int getItemCount() {
        return makananList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_nama,tv_asal,tv_kode;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_kode = itemView.findViewById(R.id.kode_makanan);
            tv_asal = itemView.findViewById(R.id.asal_makanan);
            tv_nama = itemView.findViewById(R.id.nama_makanan);
            itemView.setOnClickListener(this::onClick);;
        }

        @Override
        public void onClick(View v) {
            String id = tv_kode.getText().toString();
            Intent i = new Intent(v.getContext(), DetailActivity.class);
            i.putExtra("id", id);
            v.getContext().startActivity(i);
        }
    }
}
