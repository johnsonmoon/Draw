package com.xuyihao.drawdemo;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.view.SupportMenuInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DrawView dv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dv = (DrawView)findViewById(R.id.drawView1);//获取自定义的绘图view
        this.dv.paint.setXfermode(null);//初始化取消擦除效果
        this.dv.paint.setStrokeWidth(1);//初始化画笔宽度
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();//实例化menuInflator对象
        inflater=new SupportMenuInflater(this);
        inflater.inflate(R.menu.menu_main, menu);//解析菜单文件
        return true;//super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.red:
                dv.paint.setColor(Color.RED);
                item.setChecked(true);
                break;
            case R.id.green:
                dv.paint.setColor(Color.GREEN);
                item.setChecked(true);
                break;
            case R.id.blue:
                dv.paint.setColor(Color.BLUE);
                item.setChecked(true);
                break;
            case R.id.width_1:
                dv.paint.setStrokeWidth(1);
                break;
            case R.id.width_2:
                dv.paint.setStrokeWidth(5);
                break;
            case R.id.width_3:
                dv.paint.setStrokeWidth(10);
                break;
            case R.id.clear:
                dv.clear();
                break;
            case R.id.save:
                dv.save();
                break;
        }

        return true;
    }
}
