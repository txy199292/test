package com.dxyy.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class RegionView extends View {
    public RegionView(Context context) {
        super(context);
    }

    public RegionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RegionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        RectF rectF = new RectF(50,50,200,500);
        path.addOval(rectF, Path.Direction.CW);
        Region region = new Region();
        //取交集
        region.setPath(path,new Region(50,50,200,500));
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        drawRegion(canvas,region,paint);
        drawUnion(canvas,paint);
        drawRegionOp(canvas,paint);
    }

    private void drawRegion(Canvas canvas, Region region, Paint paint){
        //RegionIterator 可以获取组成区域矩形集
        RegionIterator iterator = new RegionIterator(region);
        Rect rect = new Rect();
        while (iterator.next(rect)){
            canvas.drawRect(rect,paint);
        }
    }

    private void drawUnion(Canvas canvas,  Paint paint){
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        Region region  = new Region(10,600,200,700);
        //求并集
        region.union(new Rect(10,600,50,900));
        drawRegion(canvas,region,paint);
    }

    private void drawRegionOp(Canvas canvas,  Paint paint){
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        Region region  = new Region(300,100,600,600);
        //op可以取两个region的各种集 成功返回true 并且将结果赋值给region
        boolean result = region.op(new Rect(200,400,700,500), Region.Op.DIFFERENCE);
       if(result){
           drawRegion(canvas,region,paint);
       }
    }


}
