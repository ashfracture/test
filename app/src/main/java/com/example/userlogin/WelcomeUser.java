package com.example.userlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

public class WelcomeUser extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_user);
        Intent i = getIntent();
       ActionBar actionBar = getSupportActionBar();
        //actionBar.setLogo(R.drawable.add);
        actionBar.setTitle("欢迎用户"+ i.getStringExtra("name"));
//        actionBar.setSubtitle("我是副标题");
        actionBar.setDisplayHomeAsUpEnabled(true);

        int color = Color.parseColor("#1E90FF");
        ColorDrawable drawable = new ColorDrawable(color);
        actionBar.setBackgroundDrawable(drawable);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));//设置状态栏颜色
            getWindow().setStatusBarColor(color);//设置状态栏颜色
            getWindow().setNavigationBarColor(color);  //设置导航栏颜色
        }
    }

    //菜单的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                Toast.makeText(this, "保存", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(this, "关于", Toast.LENGTH_SHORT).show();
                break;
            case R.id.back:
                Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:super.onOptionsItemSelected(item);
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //使用菜单填充器获取menu下的菜单资源文件
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)menuItem.getActionView();

        //添加一个提交按钮
        searchView.setSubmitButtonEnabled(true);
        //设置默认搜索的文字 searchView.setQuery("ok", false);
        //设置默认搜索提示文字
        searchView.setQueryHint("请输入搜索关键字...");
        //放大镜图标
        searchView.setIconifiedByDefault(true);
        //默认把searchView展开
        //searchItem.expandActionView();
        //使searView清除焦点,关闭输入法键盘
        //searchView.clearFocus();

        //menu中search item下加app:actionViewClass = "android.widget.SearchView"，null pointer
        //设置搜索的响应事件
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast t = Toast.makeText(WelcomeUser.this, query, Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP,0,0);
                t.show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}