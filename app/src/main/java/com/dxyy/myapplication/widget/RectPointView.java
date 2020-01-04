package com.dxyy.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class RectPointView extends View {
    private int mX,mY;
    private Rect mRect;
    private Paint mPaint;
    public RectPointView(Context context) {
        super(context);
        init();
    }

    public RectPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RectPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mRect = new Rect(100,100,800,900);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //判断是矩形是否包含某个点
        //mRect.contains() 也可以传一个rect 或者上下左右四个值 判断该矩形是否包含另外一个矩形
        if(mRect.contains(mX,mY)){
            mPaint.setColor(Color.GREEN);
        }else{
            mPaint.setColor(Color.YELLOW);
        }
        canvas.drawRect(mRect,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mX = (int)event.getX();
        mY = (int)event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //invalidate()方法 是通知系统重绘此控件 会重新调用ondraw方法 postInvalidate()也一样
                // 不同的是invalidate只能在主线程 而postInvalidate()没限制 此处在onTouchEvent中是处于主线程中
                invalidate();
                //此处返回true才会接收到后续的up事件  否则接收不到 返回false 系统认为不需要处理
                 return true;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                mX = -1;
                mY = -1;
                break;
        }
        invalidate();
        return super.onTouchEvent(event);
    }


}
