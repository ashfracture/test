package com.example.userlogin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class choose extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
//        if(actionBar!=null){ actionBar.hide(); }
        actionBar.hide();
        setContentView(R.layout.activity_choose);

        Button button1=findViewById(R.id.button1);
        Button button2=findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){

        if(v.getId()==R.id.button1)
        {
            Intent intent =new Intent(this,UserRegister.class);
            startActivity(intent);
        }
        else if (v.getId()==R.id.button2){
            Intent intent= new Intent(this,UserLogin.class);
            Bundle init = new Bundle();
            init.putString("tel","123456789");
            init.putString("password", "password");
            init.putString("name","你的名字");
            intent.putExtra("date",init);
            startActivity(intent);
        }

    }

}