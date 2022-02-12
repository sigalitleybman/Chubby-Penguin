package com.example.worldwideski2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;


public class GameActivity extends MusicalBase {

    // game view represents the logic of the app
    private GameView gameView;
    private Level level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //For full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        Intent intent = getIntent();

        level = intent.getParcelableExtra("level");


        Point point = new Point();

        getWindowManager().getDefaultDisplay().getSize(point);

        /**
         * point.x and point.y represents the screen size
         */
        gameView = new GameView(this,point.x, point.y, level);

        setContentView(gameView);
    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.game_menu_options,menu);
//
//
////        AudioManager manager = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
////        if(!manager.isMusicActive())
////            menu.findItem(R.id.sound).setIcon(getDrawable(R.drawable.volume_off));
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//  @Override
//  public boolean onOptionsItemSelected(MenuItem item) {
//      switch (item.getItemId()){
////          when press home -  create alert dialog if the user want to exit the game
////          case R.id.home:
////              //stop the game
////              gameView.pause();
////              pressHome=true;
////              android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
////              LayoutInflater inflater = this.getLayoutInflater();
////              View viewInflater = inflater.inflate(R.layout.exit_game, null);
////              builder.setView(viewInflater);
////              AlertDialog exitDialog = builder.create();
////              exitDialog.setCancelable(false);
////              exitDialog.show();
////
////              ImageButton yes_btn = viewInflater.findViewById(R.id.yes_exit);
////              yes_btn.setOnClickListener(new View.OnClickListener() {
////                  //if press yes - move to the play menu page
////                  @Override
////                  public void onClick(View view) {
////                      exitDialog.dismiss();
////                      Intent intent = new Intent(GameActivity.this, PlayMenu.class);
////                      startActivity(intent);
////                      finish();
////                  }
////              });
////              ImageButton no_btn = viewInflater.findViewById(R.id.no_exit);
////              no_btn.setOnClickListener(new View.OnClickListener() {
////                  //if press no - return the game
////                    @Override
////                    public void onClick(View view) {
////                        pressHome = false;
////                        onPostResume();
////                        exitDialog.dismiss();
////                    }
////                });
////
////                break;
//            case R.id.pause:
//                //when the user press to resume the game and the icon of the button change to the pause bitmap
//                if(item.getIcon().getConstantState().equals(getDrawable(R.drawable.play).getConstantState())) {
//                    onPostResume();
//                    MusicManager.Instance().play(false);
//                    item.setIcon(getDrawable(R.drawable.pause));
//                }else {//when the user press to pause the game stop and the icon of the button change to the resume bitmap
//                    onPause();
//                    MusicManager.Instance().play(false);
//                    item.setIcon(getDrawable(R.drawable.play));
//                }
//                break;
//            case  R.id.sound:
//                //when the user press to start the music and the icon of the button change to the no music bitmap
//                if(item.getIcon().getConstantState().equals(getDrawable(R.drawable.ice_volume_off).getConstantState())) {
//                    MusicManager.Instance().play(true);
//                    item.setIcon(getDrawable(R.drawable.ice_volume_on));
//                } else {//when the user press to stop the music and the icon of the button change to the music bitmap
//                    MusicManager.Instance().pause(true);
//                    item.setIcon(getDrawable(R.drawable.ice_volume_off));
//                }
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }



}