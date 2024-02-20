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
import com.nghiatbp_toanum.musicapp.Model.PlayList;
import com.nghiatbp_toanum.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachCacPlayListAdapter extends RecyclerView.Adapter<DanhSachCacPlayListAdapter.ViewHolder> {
    Context context;
    ArrayList<PlayList> arrayListplaylist;

    public DanhSachCacPlayListAdapter(Context context, ArrayList<PlayList> arrayListplaylist) {
        this.context = context;
        this.arrayListplaylist = arrayListplaylist;
    }

    @NonNull
    @Override
    public DanhSachCacPlayListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachcacplaylist, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhSachCacPlayListAdapter.ViewHolder viewHolder, int i) {
        PlayList playList = arrayListplaylist.get(i);
        Picasso.with(context).load(playList.getHinh()).into(viewHolder.imghinhnen);
        viewHolder.txttenplaylist.setText(playList.getTen());
    }

    @Override
    public int getItemCount() {
        return arrayListplaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinhnen;
        TextView txttenplaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhnen = itemView.findViewById(R.id.imageviewdanhsachcacplaylist);
            txttenplaylist = itemView.findViewById(R.id.textviewtendanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemplaylist", arrayListplaylist.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
