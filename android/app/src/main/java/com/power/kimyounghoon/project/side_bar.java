package com.power.kimyounghoon.project;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by KIMYOUNGHOON on 2017-08-20.
 */

public class side_bar extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        TextView loginbar = (TextView)findViewById(R.id.login_bar);
        loginbar.setText(getIntent().getStringExtra("id"));

        Log.i("반값","ㅋ");
    }
}
