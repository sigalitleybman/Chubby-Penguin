package com.example.worldwideski2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

//public class FinishedLevelActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.pop_up_finished_level);
//
//
//
//        //create the button animation - change scaleX and scaleY
//        ImageButton imageButtonArrow = findViewById(R.id.image_button_arrow);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(imageButtonArrow,"scaleX",((float)(1.15))).setDuration(250);
//        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageButtonArrow,"scaleY",((float)(1.15))).setDuration(250);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator2.setRepeatMode(ValueAnimator.REVERSE);
//        animator2.setRepeatCount(ValueAnimator.INFINITE);
//        AnimatorSet set1 = new AnimatorSet();
//        set1.playTogether(animator,animator2);
//        set1.start();
//    }
//}
