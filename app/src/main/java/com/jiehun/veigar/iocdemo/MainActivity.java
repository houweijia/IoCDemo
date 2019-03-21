package com.jiehun.veigar.iocdemo;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jiehun.veigar.iocdemo.base.BaseActivity;
import com.jiehun.veigar.library.ContentView;
import com.jiehun.veigar.library.InjectView;
import com.jiehun.veigar.library.OnClick;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @InjectView(R.id.tv)
    private TextView tv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv.setText("你好");
        /*tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });*/
    }

    @OnClick({R.id.tv})
    public void adb(View view){
        switch (view.getId()){
            case R.id.tv:
                Toast.makeText(this,"点击了",Toast.LENGTH_SHORT).show();
                break;
        }
    }



}
