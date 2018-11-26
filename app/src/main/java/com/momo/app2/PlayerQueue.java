package com.momo.app2;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class PlayerQueue {

    public BlockingQueue<MediaPlayer> plot;
    public BlockingQueue<MediaPlayer> cleaver;

    public PlayerQueue(Context context) {
        plot =  new LinkedBlockingDeque<>();
        cleaver =  new LinkedBlockingDeque<>();

        plot.add(MediaPlayer.create(context, R.raw.plop));
        plot.add(MediaPlayer.create(context, R.raw.plop));
        plot.add(MediaPlayer.create(context, R.raw.plop));
        plot.add(MediaPlayer.create(context, R.raw.plop));
        plot.add(MediaPlayer.create(context, R.raw.plop));

        cleaver.add(MediaPlayer.create(context, R.raw.cleaver));
        cleaver.add(MediaPlayer.create(context, R.raw.cleaver));
        cleaver.add(MediaPlayer.create(context, R.raw.cleaver));
        cleaver.add(MediaPlayer.create(context, R.raw.cleaver));
        cleaver.add(MediaPlayer.create(context, R.raw.cleaver));
        cleaver.add(MediaPlayer.create(context, R.raw.cleaver));
        cleaver.add(MediaPlayer.create(context, R.raw.cleaver));
        cleaver.add(MediaPlayer.create(context, R.raw.cleaver));
        cleaver.add(MediaPlayer.create(context, R.raw.cleaver));
        cleaver.add(MediaPlayer.create(context, R.raw.cleaver));
        cleaver.add(MediaPlayer.create(context, R.raw.cleaver));
        cleaver.add(MediaPlayer.create(context, R.raw.cleaver));
        cleaver.add(MediaPlayer.create(context, R.raw.cleaver));
        cleaver.add(MediaPlayer.create(context, R.raw.cleaver));
        cleaver.add(MediaPlayer.create(context, R.raw.cleaver));
    }

    public void playPlot() {
        MediaPlayer sound = null;
        try {
            sound = plot.take();
            sound.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (sound != null)
                plot.add(sound);
        }
    }

    public void playCleaver() {
        MediaPlayer sound = null;
        try {
            sound = cleaver.take();
            sound.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (sound != null)
                cleaver.add(sound);
        }
    }

    public void destroy() {
        for (MediaPlayer player : plot) {
            player.release();
        }

        for (MediaPlayer player : cleaver) {
            player.release();
        }
    }
}
