package com.xuyihao.drawdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Xu Yihao
 */
public class DrawView extends View{
    private int view_width = 0;//屏幕宽度
    private int view_height = 0;//屏幕高度
    private float preX;//起始点X坐标值
    private float preY;//起始点Y坐标值
    private Path path;//路径
    public Paint paint = null;//画笔
    Bitmap cacheBitmap = null;//内存图片，作为缓冲区
    Canvas cacheCanvas = null;//cacheBitmap上的Canvas对象

    //constructor
    public DrawView(Context context, AttributeSet attrs){
        super(context, attrs);
        view_width = context.getResources().getDisplayMetrics().widthPixels;//从上文获取屏幕宽度
        view_height = context.getResources().getDisplayMetrics().heightPixels;//从上文获取屏幕高度

        //创建缓存区，语view大小相同
        cacheBitmap = Bitmap.createBitmap(view_width, view_height, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();//新画布
        path = new Path();
        cacheCanvas.setBitmap(cacheBitmap);//在画布上绘制cacheBitmap
        paint = new Paint(Paint.DITHER_FLAG);//画笔，以下为设置画笔
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(paint.Cap.ROUND);
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    //重写onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


}
