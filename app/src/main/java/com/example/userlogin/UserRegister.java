package com.example.userlogin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class UserRegister extends AppCompatActivity {

    int province_pos=0,city_pos=0,area_pos=0;
    boolean flag=true;
    ArrayAdapter<String> adapter1,adapter2,adapter3;

    String[] province = {"湖北","广东"};
    String[][] city={{"武汉","竟陵"},{"广州","深圳"}};
    String[][][] area={{{"洪山区","江汉区"},{"第九区","浣熊市","寂静岭"}},{{"海珠区","海淀区"},{"横林","岳口"}}};
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        /*<item name="windowNoTitle">true</item>   //project去标题栏，在styles.xml下
        requestWindowFeature(Window.FEATURE_NO_TITLE); //特定activity去标题栏，需要extends Activity
        */
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_user_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);  //添加返回的图标

        //将imageview转化为bitmap并裁剪为圆形
        ImageView img=findViewById(R.id.head);
        Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
        getCircleBitmap(bitmap);
        img.setImageBitmap(bitmap);

        ImageView head =findViewById(R.id.head);
        Glide.with(UserRegister.this)
                .load((BitmapDrawable)head.getDrawable())//lode里面的参数是图片资源
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(head);


        //checkBox
        Drawable drawable1 = this.getResources().getDrawable(R.drawable.checked);
        drawable1.setBounds(0,0,100,100);
        Drawable drawable2 = this.getResources().getDrawable(R.drawable.checkbox);
        drawable2.setBounds(0,0,100,100);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);


        final EditText editText = findViewById(R.id.password);
//        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());==xml:inputType="textPassword"
        checkBox.setCompoundDrawables(drawable2,null,null,null);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    Drawable drawable1 = getApplicationContext().getResources().getDrawable(R.drawable.checked);
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    checkBox.setCompoundDrawables(drawable1,null,null,null);
                } else {
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    checkBox.setCompoundDrawables(drawable2,null,null,null);
                }
            }
        });
        EditText editText1=findViewById(R.id.name);
        EditText editText2=findViewById(R.id.tel_1);
        Button button=findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserRegister.this, UserLogin.class);
                Bundle information = new Bundle();
                information.putString("tel",editText2.getText().toString());
                information.putString("password", editText.getText().toString());
                information.putString("name",editText1.getText().toString());
                intent.putExtra("date",information);
                Toast.makeText(UserRegister.this, "用户"+editText1.getText().toString()+"注册成功", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        initSpinner();
    }

    private void initSpinner() {

        //下拉块
        Spinner s1=findViewById(R.id.spinner1);
        Spinner s2=findViewById(R.id.spinner2);
        Spinner s3=findViewById(R.id.spinner3);

        /*动态增删
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        adapter.add();adapter.clear();adapter.remove();*/

        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, province);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, city[0]);
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, area[0][0]);
        s1.setAdapter(adapter1);
        s1.setSelection(-1,true);
        s2.setAdapter(adapter2);
        s2.setSelection(-1,true);
        s3.setAdapter(adapter3);
        s3.setSelection(-1,true);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                province_pos=pos;
                s2.setAdapter(new ArrayAdapter<String>(UserRegister.this, android.R.layout.simple_spinner_dropdown_item, city[pos]));
                s3.setAdapter(new ArrayAdapter<String>(UserRegister.this, android.R.layout.simple_spinner_dropdown_item, area[pos][0]));//  看具体补全需求
                Toast.makeText(UserRegister.this, province[pos], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                city_pos=pos;
                s3.setAdapter(new ArrayAdapter<String>(UserRegister.this, android.R.layout.simple_spinner_dropdown_item, area[province_pos][city_pos]));
                Toast.makeText(UserRegister.this, city[province_pos][pos], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(UserRegister.this, area[province_pos][city_pos][pos], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

/*    //显示菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);  //格式(R.menu.你的menu名字,menu)
        return super.onCreateOptionsMenu(menu);
    }

    //菜单点击实现
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){
            case R.id.back:  //你的menu里控件id
                finish();  //这里是执行代码
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/

    //这是exit箭头的监听，上面是菜单的监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Bitmap cropBitmap(Bitmap bitmap) {//从中间截取一个正方形
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();
        int cropWidth = w >= h ? h : w;// 裁切后所取的正方形区域边长

        return Bitmap.createBitmap(bitmap, (bitmap.getWidth() - cropWidth) / 2,
                (bitmap.getHeight() - cropWidth) / 2, cropWidth, cropWidth);
    }

    public static Bitmap getCircleBitmap(Bitmap bitmap) {//把图片裁剪成圆形
        if (bitmap == null) {
            return null;
        }
        bitmap = cropBitmap(bitmap);//裁剪成正方形
        try {
            Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(circleBitmap);
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());
            final RectF rectF = new RectF(new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight()));
            float roundPx = 0.0f;
            roundPx = bitmap.getWidth();
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(Color.WHITE);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            final Rect src = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());
            canvas.drawBitmap(bitmap, src, rect, paint);
            return circleBitmap;
        } catch (Exception e) {
            return bitmap;
        }
    }

}





