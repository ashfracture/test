package com.example.userlogin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

public class UserLogin extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_user_login);
        View view=findViewById(R.id.arrow);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("date");
        EditText e2=findViewById(R.id.password_2);
        EditText e1=findViewById(R.id.tel);
        e1.setText(String.format(b.getString("tel")));
        e2.setText(String.format(b.getString("password")));

        ImageView head =findViewById(R.id.head_1);
        Glide.with(UserLogin.this)
                .load((BitmapDrawable)head.getDrawable())//lode里面的参数是图片资源
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(head);

        Button button=findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserLogin.this,WelcomeUser.class);

                intent.putExtra("name",b.getString("name"));
                Toast.makeText(UserLogin.this, "登陆成功", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}