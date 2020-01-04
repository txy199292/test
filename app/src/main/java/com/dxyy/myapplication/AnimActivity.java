package com.dxyy.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.annotation.Annotation;

public class AnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        //从xml文件中加载动画配置
        Animation animation = AnimationUtils.loadAnimation(AnimActivity.this, R.anim.translate1);
        //动画播放完成后 恢复原样


        AnimationDrawable animationDrawable = (AnimationDrawable)((ImageView)findViewById(R.id.iv)).getDrawable();

        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1.4f, 0, 1.4f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        RotateAnimation rotateAnimation = new RotateAnimation(30f,720f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE,0,Animation.ABSOLUTE,100,Animation.ABSOLUTE,0,Animation.ABSOLUTE,0);
        scaleAnimation.setRepeatCount(2);
        alphaAnimation.setRepeatCount(2);
        rotateAnimation.setRepeatCount(2);
        translateAnimation.setRepeatCount(2);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(3000);
        animationSet.setFillAfter(true);
        //animation.setRepeatCount 无效  必须在单独的每个动画上设置
        //animation.setRepeatCount(3);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(AnimActivity.this,"动画开始",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(AnimActivity.this,"动画结束",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Toast.makeText(AnimActivity.this,"repeat",Toast.LENGTH_SHORT).show();

            }
        });
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.tv).startAnimation(animation);
                animationDrawable.start();
            }
        });
        findViewById(R.id.btn_end).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.tv).clearAnimation();
            }
        });


    }
}
