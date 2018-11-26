package com.momo.app2;

import android.media.AudioManager;
import android.media.ToneGenerator;

public class Tones {

    private ToneGenerator tone;

    private boolean soundEnabled = true;

    public Tones() {
        tone = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
    }

    public void setSoundEnabled(boolean val) {
        soundEnabled = val;
    }

    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    public void play() {
        if (soundEnabled)
            tone.startTone(ToneGenerator.TONE_CDMA_PIP, 150);
    }

    public void destroy() {
        if (tone != null) {
            tone.release();
        }
    }
}
