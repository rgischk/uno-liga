package de.rgischk.unoliga;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class ArrowActivity extends Activity {

    private static final String LEFT_DIRECTION_STATE_KEY = "leftDirection";
    private boolean leftDirection = true;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrow);
        // Keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Restore activity state (e.g. after rotation of device)
        if (savedInstanceState != null) {
            leftDirection = savedInstanceState.getBoolean(LEFT_DIRECTION_STATE_KEY, leftDirection);
        }

        // Add listener to arrow button
        final ImageView arrowButton = findViewById(R.id.button_arrow);
        arrowButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Perform animation on button press
                animateRotation(arrowButton, leftDirection);
                // Change direction
                leftDirection = !leftDirection;
            }
        });

        // Perform initial animation after opening or rotating the device
        animateRotation(arrowButton, !leftDirection);
    }

    private void animateRotation(ImageView arrowButton, boolean leftDirection) {
        if (leftDirection) {
            arrowButton.setScaleX(-1); // Mirror image
            arrowButton.setRotation(0); // Reset rotation
            arrowButton.animate().rotation(-360).setDuration(600).start(); // Animate
        } else {
            arrowButton.setScaleX(1); // Un-mirror image
            arrowButton.setRotation(-360); // Reset rotation
            arrowButton.animate().rotation(0).setDuration(600).start(); // Animate
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save activity state
        outState.putBoolean(LEFT_DIRECTION_STATE_KEY, leftDirection);
    }
}
