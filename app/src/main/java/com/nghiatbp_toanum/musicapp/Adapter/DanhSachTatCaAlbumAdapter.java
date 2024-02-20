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
import com.nghiatbp_toanum.musicapp.Model.Album;
import com.nghiatbp_toanum.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachTatCaAlbumAdapter extends RecyclerView.Adapter<DanhSachTatCaAlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Album> arrayListalbum;

    public DanhSachTatCaAlbumAdapter(Context context, ArrayList<Album> arrayListalbum) {
        this.context = context;
        this.arrayListalbum = arrayListalbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachcacalbum,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Album album  = arrayListalbum.get(i);
        Picasso.with(context).load(album.getHinhalbum()).into(viewHolder.imgallalbum);
        viewHolder.txttenallalbum.setText(album.getTenalbum());
    }

    @Override
    public int getItemCount() {
        return arrayListalbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgallalbum;
        TextView txttenallalbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgallalbum = itemView.findViewById(R.id.imageviewdanhsachcacalbum);
            txttenallalbum = itemView.findViewById(R.id.textviewtendanhsachcacalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent   = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("album",arrayListalbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
