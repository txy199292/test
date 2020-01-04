package com.dxyy.myapplication.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.dxyy.myapplication.R;

public class CanvasView extends View {
    private Paint paint1, paint2;
    private Bitmap bitmap;
    private Path path;

    public CanvasView(Context context) {
        super(context);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint1 = new Paint();
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setColor(Color.GREEN);
        paint1.setStrokeWidth(8);


        paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(Color.RED);
        paint2.setStrokeWidth(8);

        setLayerType(LAYER_TYPE_SOFTWARE,null);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.avator);
        path = new Path();
        path.addCircle(bitmap.getWidth()/2,bitmap.getHeight()/2,bitmap.getWidth()/2, Path.Direction.CCW);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       /* Rect rect = new Rect(0, 0, 200, 400);
        canvas.drawRect(rect, paint1);
        //水平和竖直方向平移100px 平移后改变原点坐标
        canvas.translate(400, 400);
        canvas.drawRect(rect, paint2);

        //px，py 为旋转中心点  如果不设置px py 就以当前原点为中心旋转    旋转后x轴y轴方向也跟着转动了
        canvas.rotate(90, 100, 200);
        canvas.drawRect(rect, paint2);

        //x轴和y轴缩放  小数是缩小  整数放大  1不变
        canvas.scale(0.5f, 0.5f);
        canvas.drawRect(rect, paint2);

        //扭曲x坐标向x轴方向（逆时针）偏移60度 y坐标不变
        canvas.skew((float) Math.tan(60d), 0);
        canvas.drawRect(rect, paint2);*/


       /* canvas.drawColor(Color.GREEN);
        //保存画布
        canvas.save();
        //剪裁以后 画布的大小就是Rect所在区域
        canvas.clipRect(new Rect(100,200,300,400));
        canvas.drawColor(Color.BLUE);
        //恢复画布
        canvas.restore();
        canvas.drawColor(Color.YELLOW);*/


        /*canvas.drawColor(Color.RED);
        //save方法返回canvas栈的索引
        int c1 = canvas.save();

        canvas.clipRect(100, 100, 800, 800);
        canvas.drawColor(Color.GREEN);
        int c2 = canvas.save();

        canvas.clipRect(200, 200, 700, 700);
        canvas.drawColor(Color.BLUE);
        int c3 = canvas.save();

        canvas.clipRect(300, 300, 600, 600);
        canvas.drawColor(Color.BLACK);
        int c4 = canvas.save();

        canvas.clipRect(400, 400, 500, 500);
        canvas.drawColor(Color.WHITE);
        //c2索引画布出栈 (100, 100, 800, 800)
        canvas.restoreToCount(c2);
        canvas.drawColor(Color.YELLOW);*/


        canvas.save();
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,0,0,paint1);
        canvas.restore();

    }
}
