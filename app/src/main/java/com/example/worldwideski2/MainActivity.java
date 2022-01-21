package com.example.worldwideski2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
{
    private ImageButton volumeButtonOn;
    private ImageButton volumeButtonOff;
    private ImageButton settingsButton;
    private ImageButton infoButton;
    private Dialog dialogInfo;
    private MediaPlayer musicPlayer;
    private int flowMusic;
    private Button letsstartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volumeButtonOn = findViewById(R.id.image_button_volume_on);
        volumeButtonOff = findViewById(R.id.image_button_volume_off);
        settingsButton = findViewById(R.id.image_button_settings);
        infoButton = findViewById(R.id.image_button_info);
        letsstartButton = findViewById(R.id.button_lets_start);
        flowMusic = R.raw.audio;

        MusicManager.Instance().initializeMusic(this, flowMusic);



        dialogInfo = new Dialog(this);
        startMusic();
        //musicPlayer.start();

        changeToVolumeOff();
        changeToVolumeOn();

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PopupSettingsActivity.class);
                startActivity(intent);
            }
        });

        letsstartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LevelActivity.class);
                startActivity(intent);
            }
        });
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
     *
     */
    private void startMusic(){
        if(musicPlayer == null){
            musicPlayer = MediaPlayer.create(this, R.raw.audio);

            musicPlayer.setOnCompletionListener(mp -> startMusic());
        }

        musicPlayer.start();
    }

    /**
     * Here we stop the music by realising the resources.
     */
    private void stopMusic(){
        if(musicPlayer != null){
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

//    /**
//     * This method responsible for showing the dialog settings popup.
//     * @param view - the MainActivity on which we display the dialog settings popup.
//     */
//    public void showSettingsPopup(View view){
//        dialogSettings.setContentView(R.layout.pop_up_settings);
//        dialogSettings.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialogSettings.show();
//    }

    public void showInfoPopup(View view){
        dialogInfo.setContentView(R.layout.activity_info);
        dialogInfo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogInfo.show();
    }
}