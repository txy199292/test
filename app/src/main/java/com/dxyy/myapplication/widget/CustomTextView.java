package com.dxyy.myapplication.widget;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class CustomTextView extends View {
    private Context mContext;

    public CustomTextView(Context context) {
        super(context);
        mContext = context;
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.RED);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        //字体加粗
        paint.setFakeBoldText(true);
        //下划线了
        paint.setUnderlineText(true);
        //中间删除线
        paint.setStrikeThruText(true);
        //设置倾斜度
        paint.setTextSkewX(-0.25f);
        //水平拉伸 1表示正常
        paint.setTextScaleX(1.25f);
        String text = "床前明月光，疑是地上霜";
        canvas.drawText(text, 400, 100, paint);
        //srtat end 指定绘制文字的起始和终止位置
        // 还有一个 传 char[]的重载方法  传起始位置 和 count 绘制个数
        canvas.drawText(text, 1, 3, 400, 300, paint);
        //指定每一个位置的绘制位置
        float[] pos = {300, 500, 300, 600, 300, 700, 300, 800};
        canvas.drawPosText("床前明月", pos, paint);

        Path path = new Path();
        path.addCircle(500, 500, 300, Path.Direction.CW);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        //沿着路径绘制文字 hoffset 离起点水平距离X轴方向为正  voffset离起点竖直距离 Y轴方向为正
        canvas.drawTextOnPath(text, path, 0, -100, paint);


        //加载自定义字体
        AssetManager assetManager = mContext.getAssets();
        Typeface typeface = Typeface.createFromAsset(assetManager,"font/jian_luobo.ttf");
        paint.setTypeface(typeface);
        paint.reset();
        paint.setTextSize(100);
        canvas.drawText(text,100,1200,paint);


    }


}
