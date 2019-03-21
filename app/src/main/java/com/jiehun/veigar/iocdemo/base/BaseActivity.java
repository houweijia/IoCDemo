package com.jiehun.veigar.iocdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.jiehun.veigar.library.InjectManager;

/**
 * @description:
 * @author: houwj
 * @date: 2019/3/21
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectManager.inject(this);
    }
}
