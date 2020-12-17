package com.example.leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import static java.lang.Thread.sleep;

public class SplashScreen extends AppCompatActivity {

    private long ms = 0;
    private long splashTime = 2000;
    private boolean splashActive = true;
    private boolean paused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);

        final ProgressBar splashProgress = findViewById(R.id.splash_progress_bar);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (splashActive && ms < splashTime){
                        if (!paused)
                            ms +=100;
                        sleep(100); }
                } catch (Exception e) {}
                finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        thread.start();

    }
}