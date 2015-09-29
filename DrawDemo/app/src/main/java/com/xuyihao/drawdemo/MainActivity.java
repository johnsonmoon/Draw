package com.xuyihao.drawdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    DrawView dv;

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
        MenuInflater inflater = new MenuInflater(this);//实例化menuInflator对象
        inflater.inflate(R.menu.menu_main, menu);//解析菜单文件
        return super.onCreateOptionsMenu(menu);
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
                this.dv.paint.setColor(Color.RED);
                item.setChecked(true);
                break;
            case R.id.green:
                this.dv.paint.setColor(Color.GREEN);
                item.setChecked(true);
                break;
            case R.id.blue:
                this.dv.paint.setColor(Color.BLUE);
                item.setChecked(true);
                break;
            case R.id.width_1:
                this.dv.paint.setStrokeWidth(1);
                break;
            case R.id.width_2:
                this.dv.paint.setStrokeWidth(5);
                break;
            case R.id.width_3:
                this.dv.paint.setStrokeWidth(10);
                break;
            case R.id.clear:
                this.dv.clear();
                break;
            case R.id.save:
                this.dv.save();
                break;
        }


        return true;
    }
}
