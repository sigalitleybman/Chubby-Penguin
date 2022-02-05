package com.example.worldwideski2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class MainActivity extends MusicalBase {

    private ImageButton volumeButtonOn;
    private ImageButton volumeButtonOff;
    private ImageButton settingsButton;
    private ImageButton infoButton;
    private Dialog dialogInfo;
    private MediaPlayer musicPlayer;
    private int flowMusic;
    private Button letsStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        volumeButtonOn = findViewById(R.id.image_button_volume_on);
        volumeButtonOff = findViewById(R.id.image_button_volume_off);
        settingsButton = findViewById(R.id.image_button_settings);
        infoButton = findViewById(R.id.image_button_info);
        letsStartButton = findViewById(R.id.button_lets_start);
        flowMusic = R.raw.audio;

        MusicManager.Instance().initializeMusic(this, flowMusic);


        dialogInfo = new Dialog(this);

        MusicManager.Instance().initializeMusic(this, flowMusic);
        //startMusic();
        //musicPlayer.start();

        changeToVolumeOff();
        changeToVolumeOn();


        //create the button animation - change scaleX and scaleY
        Button buttonStart = findViewById(R.id.button_lets_start);
        ObjectAnimator animator = ObjectAnimator.ofFloat(buttonStart, "scaleX", ((float) (1.15))).setDuration(250);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(buttonStart, "scaleY", ((float) (1.15))).setDuration(250);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator2.setRepeatMode(ValueAnimator.REVERSE);
        animator2.setRepeatCount(ValueAnimator.INFINITE);
        AnimatorSet set1 = new AnimatorSet();
        set1.playTogether(animator, animator2);
        set1.start();


        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Intent intent = new Intent(MainActivity.this, PopupSettingsActivity.class);
                //                startActivity(intent);
                //AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //                  LayoutInflater inflater = (MainActivity.this).getLayoutInflater();
                //                  View viewInflater = inflater.inflate(R.layout.pop_up_settings, null,false);
                //                  builder.setView(viewInflater);
                //                  AlertDialog finishDialog = builder.create();
                //                  finishDialog.setCancelable(false);
                //                  finishDialog.show();
                //               LayoutInflater inflater = getLayoutInflater();
                //               View view = inflater.inflate(R.layout.pop_up_settings,null);
                showPopUpSettingsDialog();


                //                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                //                final View settingsPopUpView = getLayoutInflater().inflate(R.layout.pop_up_settings,null);
                //
                //                RadioGroup chooseLanguage = (RadioGroup) settingsPopUpView.findViewById(R.id.toggle);
                //                SeekBar volumeSeekBar = (SeekBar) settingsPopUpView.findViewById(R.id.volume_seekbar);
                //
                //                dialogBuilder.setView(settingsPopUpView);
                //                Dialog settingsPopUpDialog = dialogBuilder.create();
                //                settingsPopUpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //
                //                //Getting the window size
                //                DisplayMetrics displayMetrics = new DisplayMetrics();
                //                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                //                int width = displayMetrics.widthPixels;
                //                int height= displayMetrics.heightPixels;
                //
                //                settingsPopUpDialog.getWindow().setLayout((int)(width*0.9),(int)(height*0.2));

            }
        });

        letsStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LevelActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showPopUpSettingsDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.pop_up_settings);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        dialog.getWindow().setLayout((int) (width * 0.9), (int) (height * 0.7));


        SeekBar volumeSeekbar =  dialog.findViewById(R.id.volume_seekbar);
        AudioManager audioManager =  (AudioManager) (getSystemService(AUDIO_SERVICE));

        //get max volume
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        //get current volume
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        volumeSeekbar.setMax(maxVolume);

        volumeSeekbar.setProgress(currentVolume);



        volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //here we set the volume by putting the 0 in flag
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        dialog.show();
    }

    /**
     * Listener for volumeButtonOff.
     * First we set volumeButtonOff to INVISIBLE and then volumeButtonOn we set to VISIBLE.
     */
    private void changeToVolumeOn() {
        volumeButtonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMusic();
                volumeButtonOff.setVisibility(View.INVISIBLE);
                volumeButtonOn.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * Listener for volumeButtonOn.
     * First we set volumeButtonOn to INVISIBLE and then volumeButtonOff we set to VISIBLE.
     */
    private void changeToVolumeOff() {
        volumeButtonOn.setOnClickListener(v -> {
            stopMusic();
            volumeButtonOn.setVisibility(View.INVISIBLE);
            volumeButtonOff.setVisibility(View.VISIBLE);
        });
    }

    /**
     * This method is responsible for starting the music , while saving resources,
     * if the musicPlayer isn't allocated , it gets allocated.
     * then if the song is over, we release the resources.
     */
    private void startMusic() {
        if (musicPlayer == null) {
            musicPlayer = MediaPlayer.create(this, R.raw.audio);

            musicPlayer.setOnCompletionListener(mp -> startMusic());
        }

        musicPlayer.start();
    }

    /**
     * Here we stop the music by realising the resources.
     */
    private void stopMusic() {
        if (musicPlayer != null) {
            musicPlayer.release();
            musicPlayer = null;
        }
    }

    /**
     * This method responsible for stopping the audio when leaving the app.
     */
    @Override
    protected void onStop() {
        super.onStop();
        //stopMusic();
    }

    /**
     * This method responsible for showing the dialog settings popup.
     *
     * @param view - the MainActivity on which we display the dialog settings popup.
     */
    public void showInfoPopup(View view) {
        dialogInfo.setContentView(R.layout.activity_info);
        dialogInfo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogInfo.show();
    }
}