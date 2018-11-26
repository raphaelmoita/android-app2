package com.momo.app2;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Player {

    private final Executor executor = Executors.newFixedThreadPool(20);

    private final Context context;

    public Player(Context context) {
        this.context = context;
    }

    public void playPlot() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                MediaPlayer sound = null;
                try {
                    sound = MediaPlayer.create(context, R.raw.plop);
                    sound.start();
                    while (sound.isPlaying()) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    if (sound != null) {
                        sound.release();
                    }
                }
            }
        });
    }

    public void playCleaver() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                MediaPlayer sound = null;
                try {
                    sound = MediaPlayer.create(context, R.raw.cleaver);
                    sound.start();
                    while (sound.isPlaying()) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    if (sound != null) {
                        sound.release();
                    }
                }
            }
        });
    }
}
