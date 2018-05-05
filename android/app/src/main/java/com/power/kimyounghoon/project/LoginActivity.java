package com.power.kimyounghoon.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(LoginActivity.this, MyfireActivity.class));
            return;
        }
        imageView = (ImageView) findViewById(R.id.imageButton7);
        imageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == imageView)
        {
            Intent intent = new Intent(LoginActivity.this,activity_login2.class);   //activity_login2
            startActivity(intent);
            finish();
        }
    }
}
