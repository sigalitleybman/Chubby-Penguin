package com.example.worldwideski2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * MainActivity builds the main page of our app, it also extends from MusicalBase since
 * a music must be played within from here.
 */
public class MainActivity extends MusicalBase {

    private ImageButton volumeButtonOn;
    private ImageButton volumeButtonOff;
    private ImageButton volumeImageButton;
    private ImageButton settingsButton;
    private ImageButton infoButton;
    private Dialog dialogInfo;
    private int flowMusic;
    private Button letsStartButton;
    private LanguageSetter language;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        volumeImageButton = findViewById(R.id.volume_image_button);
        settingsButton = findViewById(R.id.image_button_settings);
        infoButton = findViewById(R.id.image_button_info);
        letsStartButton = findViewById(R.id.button_lets_start);
        flowMusic = R.raw.audio;

        //starting the music
        MusicManager.Instance().initializeMusic(this, flowMusic);

        dialogInfo = new Dialog(this);

        language = new LanguageSetter(this);


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


        settingsButton.setOnClickListener(v -> showPopUpSettingsDialog());

        letsStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LevelActivity.class);
                startActivity(intent);
            }
        });



        volumeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MusicManager.Instance().isPaused()){
                    v.setSelected(false);
                    MusicManager.Instance().play(true);
                }else{
                    v.setSelected(true);
                    MusicManager.Instance().pause(true);
                }

            }
        });
        if(!MusicManager.Instance().isPaused()){
            MusicManager.Instance().play(true);
            volumeImageButton.setSelected(false);
        }
        else{
            volumeImageButton.setSelected(true);
        }
    }

    /**
     *  create and display the settings dialog.
     */
    private void showPopUpSettingsDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.pop_up_settings);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Setting the dimensions of the settings pop up.
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        dialog.getWindow().setLayout((int) (width * 0.9), (int) (height * 0.7));

        //managing the language of the app.

        RadioButton hebrewRadioButton = dialog.findViewById(R.id.radio_button_selected_hebrew);
        RadioButton englishRadioButton = dialog.findViewById(R.id.radio_button_selected_english);

        hebrewRadioButton.setOnClickListener(view -> {
            language.updateResource("iw");
            recreate();
        });

        englishRadioButton.setOnClickListener(view -> {
            language.updateResource("en");
            recreate();
        });


        // managing the sound of the app via the seek bar.
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
     * This method responsible for stopping the audio when leaving the app.
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * This method responsible for showing the dialog settings popup.
     *
     * @param view - the MainActivity on which we display the dialog settings popup.
     */
    public void showInfoPopup(View view) {
        Dialog dialogInfo = new Dialog(MainActivity.this);
        dialogInfo.setContentView(R.layout.game_explanation);
        dialogInfo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogInfo.show();

        // Finger Animation.
        ImageView finger = dialogInfo.findViewById(R.id.image_view_finger_instructions);

        ObjectAnimator animator = ObjectAnimator.
                ofFloat(finger, "scaleX", ((float) (1.15))).setDuration(250);
        ObjectAnimator animator2 = ObjectAnimator.
                ofFloat(finger, "scaleY", ((float) (1.15))).setDuration(250);

        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator2.setRepeatMode(ValueAnimator.REVERSE);
        animator2.setRepeatCount(ValueAnimator.INFINITE);

        AnimatorSet set1 = new AnimatorSet();

        set1.playTogether(animator, animator2);
        set1.start();

        //Penguin Jumping.
        ImageView instructionsPenguin = dialogInfo.findViewById(R.id.image_view_penguin_instructions);
        Animation swipe_up = AnimationUtils.loadAnimation(MainActivity.this, R.anim.penguin_anim_instructions);
        swipe_up.setRepeatMode(Animation.INFINITE);
        instructionsPenguin.startAnimation(swipe_up);

        //Shark Moving
        ImageView shark = dialogInfo.findViewById(R.id.shark_instructions);

        Animation shark_slide = AnimationUtils.loadAnimation(MainActivity.this, R.anim.swipe_left_shark);
        shark_slide.setRepeatMode(Animation.INFINITE);
        shark.startAnimation(shark_slide);
    }
}