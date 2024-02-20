package com.nghiatbp_toanum.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nghiatbp_toanum.musicapp.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.nghiatbp_toanum.musicapp.Model.ChuDe;
import com.nghiatbp_toanum.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachTatCaChuDeAdapter extends RecyclerView.Adapter<DanhSachTatCaChuDeAdapter.ViewHolder> {
    Context context;
    ArrayList<ChuDe> arrayListchude;

    public DanhSachTatCaChuDeAdapter(Context context, ArrayList<ChuDe> arrayListchude) {
        this.context = context;
        this.arrayListchude = arrayListchude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_cacchude, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ChuDe chuDe = arrayListchude.get(i);
        Picasso.with(context).load(chuDe.getHinhchude()).into(viewHolder.imgchude);
    }

    @Override
    public int getItemCount() {
        return arrayListchude.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgchude;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgchude = itemView.findViewById(R.id.imageviewdongcacchude);
            imgchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhSachTheLoaiTheoChuDeActivity.class);
                    intent.putExtra("chude", arrayListchude.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
