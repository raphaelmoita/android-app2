package com.momo.app2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.momo.app2.builder.TeamBuilder;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int MAX_LIMIT = 99;
    private static final int MIN_LIMIT = 0;

    private AtomicInteger counter = new AtomicInteger(0);

    private TeamComponentFactory teamComponentFactory;

    private Tones tones;

    private boolean soundEnabled;

    private AdView mAdView;

    private ScheduledExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-6284413583481076~8097274886");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        executor = Executors.newScheduledThreadPool(1);
//        Handler handler = new Handler();
//
//        Runnable task = () -> {
//
//            WeatherClient weather = new WeatherClient();
//            WeatherInfo weatherInfo = weather.getWeatherInfo();
//            TextView temperature = findViewById(R.id.temperature);
//            handler.post(() -> temperature.setText(String.valueOf(weatherInfo.getMain().getTemp())));
//        };
//
//        executor.scheduleWithFixedDelay(task, 5, 10, TimeUnit.SECONDS);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("sound_enabled", tones.isSoundEnabled());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        soundEnabled = savedInstanceState.getBoolean("sound_enabled");
        Log.i(TAG, "onRestoreInstanceState::soundEnabled: " + soundEnabled);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);

        MenuItem soundEnabledMenuItem = menu.findItem(R.id.sound_enabled);
        soundEnabledMenuItem.setChecked(!this.soundEnabled);
        switchSound(soundEnabledMenuItem);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.reset:
                restartActivity();
                break;

            case R.id.sound_enabled:
                Log.i(TAG, "isChecked(): " + item.isChecked());
                switchSound(item);
                break;

            case R.id.about:
                showCredits(null);
                break;

            default:
                break;
        }
        return true;
    }

    public void switchSound(MenuItem soundEnabledMenuItem) {
        ImageView soundIcon = findViewById(R.id.sound);

        if (soundEnabledMenuItem.isChecked()) {
            //soundIcon.setImageResource(R.drawable.audio_off);
            tones.setSoundEnabled(false);
            soundEnabledMenuItem.setChecked(false);
        } else {
            //soundIcon.setImageResource(R.drawable.audio_on);
            tones.setSoundEnabled(true);
            soundEnabledMenuItem.setChecked(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        tones = new Tones();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }

    public void restartActivity() {
        ((LinearLayout) findViewById(R.id.container_linear_layout)).removeAllViews();
        findViewById(R.id.teams).setVisibility(View.INVISIBLE);
        ((TextView) findViewById(R.id.quantity_text_view)).setText("0");
        counter.set(0);

        Toast.makeText(this, "Restarted", Toast.LENGTH_SHORT).show();
    }

    public void showCredits(View view) {
        Intent intent = new Intent(this, CreditsActivity.class);
        intent.putExtra("name", "Raphael Moita");
        intent.putExtra("email", "raphael.moita@gmail.com");
        startActivity(intent);
    }

    public void createTeams(View view) {

        tones.play();

        final LinearLayout container = findViewById(R.id.container_linear_layout);
        container.removeAllViews();

        teamComponentFactory = new TeamComponentFactory(getApplicationContext());

        final TeamBuilder teamBuilder = new TeamBuilder(counter.get());

        List<RelativeLayout> teams = teamComponentFactory.create(teamBuilder.getTeams());
        for (RelativeLayout team : teams) {
            container.addView(team);
        }

        if (teamBuilder.hasUnluckyPlayer()) {
            RelativeLayout unluckyPlayer = teamComponentFactory.create(valueOf(teamBuilder.getUnluckyPlayer()));
            container.addView(unluckyPlayer);
        }

        findViewById(R.id.teams).setVisibility(View.VISIBLE);
    }

    public void increment(View view) {
        tones.play();

        if (counter.get() < MAX_LIMIT) {
            display(counter.incrementAndGet());
        } else {
            makeText(getApplicationContext(),
                    "Maximum number of players reached!",
                    LENGTH_LONG).show();
        }
    }

    public void decrement(View view) {
        tones.play();

        if (counter.get() > MIN_LIMIT) {
            display(counter.decrementAndGet());
        }
    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(valueOf(number));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, (arg0, arg1) -> {
                    MainActivity.super.onBackPressed();
                    finish();
                }).create().show();
    }
}
