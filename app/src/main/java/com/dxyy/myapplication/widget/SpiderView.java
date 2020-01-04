package com.dxyy.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SpiderView extends View {

    private int count = 6;
    private float radius;//半径
    private int centerX;
    private int centerY;
    private Paint radarPaint, valuePaint;
    private float angle = (float) Math.PI * 2 / count;
    //数据
    private double[] data = {2, 5, 1, 6, 4, 5};
    //最大值
    private float maxValue = 6;

    public SpiderView(Context context) {
        super(context);
        init();
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        radarPaint = new Paint();
        radarPaint.setStyle(Paint.Style.STROKE);
        radarPaint.setStrokeWidth(4);
        radarPaint.setColor(Color.GREEN);

        valuePaint = new Paint();
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = (float) Math.min(w, h) / 2 * 0.9f;
        centerX = w / 2;
        centerY = h / 2;
        //通知重绘
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);
        drawLine(canvas);
        drawValue(canvas);
    }

    private void drawLine(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            //path.reset();
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));
            path.moveTo(centerX, centerY);
            path.lineTo(x, y);
            canvas.drawPath(path, radarPaint);
        }
    }

    private void drawValue(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(127);
        for (int i = 0; i < count; i++) {
            double percent = data[i] / maxValue;
            float x = (float) (centerX + radius * percent * Math.cos(angle * i));
            float y = (float) (centerY + radius * percent * Math.sin(angle * i));
            canvas.drawCircle(x,y,20,valuePaint);
            if(i == 0){
                path.moveTo(x,y);
            }else{
                path.lineTo(x,y);
            }
        }
        path.close();
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path,valuePaint);
    }


    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / count;
        for (int i = 1; i <= count; i++) {
            float curR = r * i;
            //path.reset();
            for (int j = 0; j < count; j++) {
                float x = (float) (centerX + curR * Math.cos(angle * j));
                float y = (float) (centerY + curR * Math.sin(angle * j));
                if (j == 0) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, radarPaint);
        }
    }
}
