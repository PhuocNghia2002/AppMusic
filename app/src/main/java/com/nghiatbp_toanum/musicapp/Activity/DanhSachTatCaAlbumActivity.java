package com.nghiatbp_toanum.musicapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nghiatbp_toanum.musicapp.Adapter.DanhSachTatCaAlbumAdapter;
import com.nghiatbp_toanum.musicapp.Model.Album;
import com.nghiatbp_toanum.musicapp.R;
import com.nghiatbp_toanum.musicapp.Service.APIService;
import com.nghiatbp_toanum.musicapp.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTatCaAlbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllAlbum;
    Toolbar toolbarAllAlbum;
    DanhSachTatCaAlbumAdapter danhSachTatCaAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tat_ca_album);
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Album>> callback = dataservice.GetTatCaAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> arrayListalbum = (ArrayList<Album>) response.body();
                danhSachTatCaAlbumAdapter = new DanhSachTatCaAlbumAdapter(DanhSachTatCaAlbumActivity.this, arrayListalbum);
                recyclerViewAllAlbum.setLayoutManager(new GridLayoutManager(DanhSachTatCaAlbumActivity.this, 2));
                recyclerViewAllAlbum.setAdapter(danhSachTatCaAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewAllAlbum = findViewById(R.id.recyclerviewAllAlbum);
        toolbarAllAlbum = findViewById(R.id.toolbaralbum);
        setSupportActionBar(toolbarAllAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Album");
        toolbarAllAlbum.setTitleTextColor(getResources().getColor(R.color.purple_200));
        toolbarAllAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}