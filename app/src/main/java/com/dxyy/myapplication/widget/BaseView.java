package com.dxyy.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class BaseView extends View {


    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(0xFFFF0000);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);
        //一般有弧度的控件需要设置这个属性  矩形等没弧度的不需要
        paint.setAntiAlias(true);
        // canvas.drawColor(); 以下三个方法都是设置画布背景 需要写在最前面  会覆盖前面的
        // canvas.drawARGB();
        //canvas.drawRGB();

        //背景
        canvas.drawRGB(255,0,255);


        //圆形
        canvas.drawCircle(190,200,150,paint);
        paint.setColor(0x7EFFFF00);
        canvas.drawCircle(190,200,100,paint);


        //线段
        //paint.setStrokeWidth();方法在style为STROKE、FILL_AND_STROKE时设置边框宽度  FILL的时候表示画笔线条宽度
        paint.setStrokeWidth(10);
        canvas.drawLine(100,500,1000,500,paint);
        //pts数组代表线段的起点和终点坐标 (10,10)-(100,100) (200,200)-(400,400)
        paint.setColor(Color.YELLOW);
        float[] pts = {10,10,100,100,200,200,400,400};
        canvas.drawLines(pts,paint);
        //表示从数组中索引为2开始 绘制4个数值 就是两个点 一条线
        //canvas.drawLines(pts,2,4,paint);


        //点
        paint.setStrokeWidth(30);
        canvas.drawPoint(100,100,paint);
        canvas.drawPoints(pts,paint);
        //同上面的drawLines一样
        //canvas.drawPoints(pts,2,4,paint);



        //矩形
        //canvas.drawRect(500,500,800,800,paint);
        //Rect,RectF基本相同 都是描述矩形的上下左右坐标，唯一不同的是Rect是int RectF是float
        Rect rect = new Rect(500,500,800,800);
        Rect rectsub = new Rect(500,600,900,1000);
        //RectF rectF = new RectF(500f,500f,800f,800f);
        canvas.drawRect(rect,paint);
        canvas.drawRect(rectsub,paint);
        boolean isIntersects  = Rect.intersects(rect,rectsub);
        Log.e("两个矩形是否交叉",isIntersects+"");
        //特别注意下面两个方法: intersects方法判断两个矩形是否相交
        // intersect方法不仅判断矩形是否相交 并且将矩形相交部分赋值给rect

        // boolean isIntersects = rect.intersects(500,600,900,1000);
        //boolean isIntersectsrect = rect.intersect(rectsub);
        //合并两个矩形  注意 并不是传统意义上的合并  而是上下左右都取最大的两个矩形的最大值 然后重新构建矩形
        //rect.union(rectsub);

        //同时也可以把矩形和点合并  rect.union(x,y); 原理同上







        //圆角矩形
        // 第二个参数为圆角矩形椭圆X轴半径 第三个为圆角矩形椭圆Y轴半径
        RectF rectF = new RectF(700f,700f,1000f,1000f);
        canvas.drawRoundRect(rectF,20,30,paint);


        //椭圆
        //第一个参数 RectF表示一个矩形  椭圆外切该矩形 根据矩形确定椭圆
        RectF rectF2 = new RectF(100,100,300,200);
        canvas.drawOval(rectF2,paint);


        //圆弧 根据矩形确定椭圆
        // 根据椭圆确定圆弧  第二个参数为 起始角度 ->方向为0  第二个参数为结束角度 角度竖直向下方向递增 顺时针转
        // 第三个参数为时候连接椭圆圆心到弧边界的线 当为true时并且为 Paint.Style.FILL 绘制为扇形   否则为只有圆弧部分
        RectF rectF3 = new RectF(1000f,1000f,1200f,1300f);
        canvas.drawArc(rectF3,0,90,true,paint);









    }
}
