package com.example.worldwideski2;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Singleton music manager , inorder to save resources
 */
public final class MusicManager {

    private static MusicManager instance;
    private static final Object creationalSyncContext = new Object();
    private boolean isPaused = false;
    private MediaPlayer mediaPlayer;

    /**
     * In Singleton class the constructor must be private.
     */
    private MusicManager() {}

    /**
     * This method ensures that there is going to be only one instance of the class.
     * Using here a double check lock inorder to prevent a queue of threads,
     * actually it'll only be good for the first run, the next time when the thread would see a lock he wouldn't have
     * to wait so long and maybe even for nothing.
     * So, to prevent such case we've used a double check lock.
     *
     * @return the single instance of MusicManager.
     */
    public static MusicManager Instance() {
        if (instance == null) {
            synchronized (creationalSyncContext) {
                if (instance == null) {
                    instance = new MusicManager();
                }
            }
        }

        return instance;
    }

    /**
     * Initialize the music
     * @param context - context
     * @param musicID - it's the id of the audio.
     */
    public void initializeMusic(Context context, int musicID) {
        mediaPlayer = MediaPlayer.create(context, musicID);
        mediaPlayer.setLooping(true);
        play(false);
    }

    public void play(boolean byUser) {
        if (byUser) { //check if the music play by user or play because we move to the next page
            mediaPlayer.start();
            isPaused = false;
        }
        else if (!isPaused) {
            mediaPlayer.start();
        }
    }

    public void pause(boolean byUser) {
        if (byUser) //check if the music pause by user or pause because we move to the next page
            isPaused = true;
        this.mediaPlayer.pause();
    }

    public boolean isPaused() {
        return isPaused;
    }
}