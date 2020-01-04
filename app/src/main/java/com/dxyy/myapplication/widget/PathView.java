package com.dxyy.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PathView extends View {
    private Paint mPaint;


    public PathView(Context context) {
        super(context);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);// mPaint.setStyle(Paint.Style.FILL); 如果为FILL那么就会填充绘制的多边形
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        //设置起始点
        path.moveTo(100,100);
        //设置连接到下一个点
        path.lineTo(200,600);
        path.lineTo(300,700);
        //连回起始点
        path.close();
        canvas.drawPath(path,mPaint);


        path.moveTo(300,300);
        RectF rect = new RectF(500,500,900,900);
        //特别注意  最后一个参数  是否强制将起始点移动到弧的起始点  也就是不连接(300,300)这个点到弧起始点的线
        path.arcTo(rect,10,190,true);
        canvas.drawPath(path,mPaint);

        //同一个path画出来的颜色以最后设置的为准  如果要改变path的颜色必须新new一个path
        mPaint.setColor(Color.YELLOW);
        Path path1 = new Path();
        path1.moveTo(300,500);
        path1.lineTo(300,700);
        RectF rect1 = new RectF(100,200,300,400);
        //使用addXX方法画出来的path可以间断
        path1.addArc(rect1,0,90);
        canvas.drawPath(path1,mPaint);

        //mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        Path path2 = new Path();
        path2.moveTo(800,800);
        //创建逆时针方向的矩形
        path2.addRect(100,200,300,400, Path.Direction.CCW);
        //创建顺时针方向的矩形
        //path2.addRect(400,500,600,700, Path.Direction.CW);
        canvas.drawPath(path2,mPaint);
        String text = "老子真是操了啊啊啊啊";
        mPaint.setTextSize(35);
        mPaint.setColor(Color.BLACK);
        //Path.Direction.CCW Path.Direction.CW单纯绘制线和矩形等几何图形时没区别  但是绘制文字方向有区别
        //hOffset 文字水平间距  vOffset竖直水平间距
        canvas.drawTextOnPath(text,path2,30f,30f,mPaint);




        Paint paint2 = new Paint();
        paint2.setColor(Color.RED);
        paint2.setStyle(Paint.Style.FILL);

        Path path3 = new Path();
        path3.addRect(100,100,300,300, Path.Direction.CW);
        path3.addCircle(300,300,100, Path.Direction.CW);
        //WINDING 表示取两个图形并集  EVEN_ODD表示取两个图形并集并且去掉交集  其他两个取反
        path3.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path3,paint2);

        //reset不会清除FillType  rewind会
        path3.reset();
        path3.addCircle(500,500,300, Path.Direction.CW);
        canvas.drawPath(path3,paint2);

    }
}
