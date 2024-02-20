package com.nghiatbp_toanum.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nghiatbp_toanum.musicapp.Activity.DanhSachBaiHatActivity;
import com.nghiatbp_toanum.musicapp.Model.TheLoai;
import com.nghiatbp_toanum.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<DanhSachTheLoaiTheoChuDeAdapter.ViewHolder> {
    Context context;
    ArrayList<TheLoai> arrayListtheloai;

    public DanhSachTheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> arrayListtheloai) {
        this.context = context;
        this.arrayListtheloai = arrayListtheloai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_theloaitheochude, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TheLoai theLoai = arrayListtheloai.get(i);
        Picasso.with(context).load(theLoai.getHinhtheloai()).into(viewHolder.imghinhnen);
        viewHolder.txttentheloai.setText(theLoai.getTentheloai());
    }

    @Override
    public int getItemCount() {
        return arrayListtheloai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinhnen;
        TextView txttentheloai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhnen = itemView.findViewById(R.id.imageviewtheloaitheochude);
            txttentheloai = itemView.findViewById(R.id.textviewtentheloaitheochude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("idtheloai", arrayListtheloai.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
