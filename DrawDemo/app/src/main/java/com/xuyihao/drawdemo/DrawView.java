package com.xuyihao.drawdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    //重写onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xFFFFFFFF);//背景颜色
        Paint bmpPaint = new Paint();//创建画笔
        canvas.drawBitmap(cacheBitmap, 0, 0, bmpPaint);//绘制cachebitmap
        canvas.drawPath(path, paint);//绘制路径
        canvas.save(Canvas.ALL_SAVE_FLAG);//保存canvas状态
        canvas.restore();//恢复canvas之前保存的状态，防止保存后对canvas执行的操作对后续操作有影响
    }

    //重写onTouchEvent方法
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取触摸事件发生的位置
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);//绘图起始点移动至触摸发生位置
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - preX);
                float dy = Math.abs(y - preY);
                if(dx >= 5 || dy >= 5){//判断是否在允许的范围之内
                    path.quadTo(preX, preY, (x+preX)/2, (y+preY)/2);
                    preX = x;
                    preY = y;
                }
                break;
            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(path, paint);//绘制路径
                path.reset();
                break;
        }
        invalidate();
        return true;//表示已经处理该事件
    }

    //编写clear方法，实现橡皮擦功能
    public void clear(){
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));//设置图形重叠时候的处理方式
        paint.setStrokeWidth(50);//设置笔触宽度
    }

    //编写save方法用以保存当前图片
    public void save(){
        try {
            saveBitmap("myPicture");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //编写saveBitmap方法,保存绘制好的位图
    public void saveBitmap(String fileName)throws IOException{
        File file = new File("/sdcard/pictures/"+ fileName +".png");//创建文件对象
        file.createNewFile();//创建新文件
        FileOutputStream fileOS = new FileOutputStream(file);//创建文件输出流对象

        //将绘图内容压缩为PNG格式输出至输出流对象
        cacheBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOS);
        fileOS.flush();//将缓冲区的数据全部写出到输出流中
        fileOS.close();//关闭文件输出流对象
    }
}
