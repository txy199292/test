package com.dxyy.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        final Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.scale_alpha_anim);
        final Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.scale_alpha_anim);
        final Animation animation3 = AnimationUtils.loadAnimation(this,R.anim.scale_alpha_anim);
        final Animation animation4 = AnimationUtils.loadAnimation(this,R.anim.scale_alpha_anim);

        final ImageView circle1 = findViewById(R.id.circle1);
        final ImageView circle2 = findViewById(R.id.circle2);
        final ImageView circle3 = findViewById(R.id.circle3);
        final ImageView circle4 = findViewById(R.id.circle4);
        findViewById(R.id.start_can).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                circle1.startAnimation(animation1);
                animation2.setStartOffset(600);
                circle2.startAnimation(animation2);
                animation3.setStartOffset(1200);
                circle3.startAnimation(animation2);
                animation4.setStartOffset(1800);
                circle4.startAnimation(animation2);

            }
        });

    }
}
