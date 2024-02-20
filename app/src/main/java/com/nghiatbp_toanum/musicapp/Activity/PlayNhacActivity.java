package com.nghiatbp_toanum.musicapp.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nghiatbp_toanum.musicapp.Adapter.ViewPagerPlayListNhac;
import com.nghiatbp_toanum.musicapp.Fragment.DiaNhacFragment;
import com.nghiatbp_toanum.musicapp.Fragment.PlayDanhSachCacBaiHatFragment;
import com.nghiatbp_toanum.musicapp.Model.BaiHat;
import com.nghiatbp_toanum.musicapp.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView txttimesong, txttotaltimesong;
    SeekBar seekBar;
    ImageButton imgplay, imgrepeat, imgnext, imgpre, imgrandom;
    ViewPager viewPagerplaynhac;
    public static ArrayList<BaiHat> arrayListbaihat = new ArrayList<>();
    public static ViewPagerPlayListNhac adapternhac;
    DiaNhacFragment diaNhacFragment;
    PlayDanhSachCacBaiHatFragment playDanhSachCacBaiHatFragment;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false, checkrandom = false, next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        eventClick();
    }

    private void eventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1) != null) {
                    if (arrayListbaihat.size() > 0) {
                        diaNhacFragment.PlayNhac(arrayListbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.ic_onplay);
                } else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.ic_onpause);
                }
            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.ic_onsyned);
                        imgrandom.setImageResource(R.drawable.ic_onsuffle);
                    }
                    imgrepeat.setImageResource(R.drawable.ic_onsyned);
                    repeat = true;
                } else {
                    imgrepeat.setImageResource(R.drawable.ic_onrepeat);
                    repeat = false;
                }
            }
        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkrandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imgrandom.setImageResource(R.drawable.ic_onshuffled);
                        imgrepeat.setImageResource(R.drawable.ic_onrepeat);
                    }
                    imgrandom.setImageResource(R.drawable.ic_onshuffled);
                    checkrandom = true;
                } else {
                    imgrandom.setImageResource(R.drawable.ic_onsuffle);
                    checkrandom = false;
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayListbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (arrayListbaihat.size())) {
                        imgplay.setImageResource(R.drawable.ic_onpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = arrayListbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(arrayListbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (arrayListbaihat.size() - 1)) {
                            position = 0;
                        }
                        new PlayMusic().execute(arrayListbaihat.get(position).getLinkbaihat());
                        diaNhacFragment.PlayNhac(arrayListbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(arrayListbaihat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });
        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayListbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (arrayListbaihat.size())) {
                        imgplay.setImageResource(R.drawable.ic_onpause);
                        position--;
                        if (position < 0) {
                            position = arrayListbaihat.size() - 1;
                        }
                        if (repeat == true) {
                            position += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(arrayListbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMusic().execute(arrayListbaihat.get(position).getLinkbaihat());
                        diaNhacFragment.PlayNhac(arrayListbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(arrayListbaihat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        arrayListbaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
                Toast.makeText(this, baiHat.getTenbaihat(), Toast.LENGTH_SHORT).show();
                arrayListbaihat.add(baiHat);
            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<BaiHat> arrayListbaihats = intent.getParcelableArrayListExtra("cacbaihat");
                arrayListbaihat = arrayListbaihats;
            }
        }
    }

    private void init() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txttimesong = findViewById(R.id.textviewtimesong);
        txttotaltimesong = findViewById(R.id.textviewtotaltimesong);
        seekBar = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        imgnext = findViewById(R.id.imagebuttonnext);
        imgpre = findViewById(R.id.imagebuttonpre);
        imgrandom = findViewById(R.id.imagebuttonsuffle);
        viewPagerplaynhac = findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(view -> {
            finish();
            mediaPlayer.stop();
            mediaPlayer.release();
            arrayListbaihat.clear();
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        diaNhacFragment = new DiaNhacFragment();
        playDanhSachCacBaiHatFragment = new PlayDanhSachCacBaiHatFragment();
        adapternhac = new ViewPagerPlayListNhac(getSupportFragmentManager());
        adapternhac.AddFragment(playDanhSachCacBaiHatFragment);
        adapternhac.AddFragment(diaNhacFragment);
        viewPagerplaynhac.setAdapter(adapternhac);
        diaNhacFragment = (DiaNhacFragment) adapternhac.getItem(1);
        if (arrayListbaihat.size() > 0) {
            getSupportActionBar().setTitle(arrayListbaihat.get(0).getTenbaihat());
            new PlayMusic().execute(arrayListbaihat.get(0).getLinkbaihat());
            imgplay.setImageResource(R.drawable.ic_onpause);
        }
    }

    class PlayMusic extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txttotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txttimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (position < (arrayListbaihat.size())) {
                        imgplay.setImageResource(R.drawable.ic_onpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = arrayListbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(arrayListbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (arrayListbaihat.size() - 1)) {
                            position = 0;
                        }
                        new PlayMusic().execute(arrayListbaihat.get(position).getLinkbaihat());
                        diaNhacFragment.PlayNhac(arrayListbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(arrayListbaihat.get(position).getTenbaihat());
                    }
                    imgpre.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgpre.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    }, 5000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}