package com.power.kimyounghoon.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import static com.power.kimyounghoon.project.MyFirebaseMessagingService.temp;

public class AlertActivity extends Activity implements
        OnClickListener {

    private Button mConfirm, mCancel;
    private TextView textView;
    private WebView mainWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alert);

        mainWebView = (WebView ) findViewById(R.id.main_web_view);

        mainWebView.getSettings().setJavaScriptEnabled(true);
        mainWebView.loadUrl(Utill.getURL("push.php")); //아두이노주소, push.php 페이지의 화면을 띄운다.

        setContent();
    }

    private void setContent() {
        mCancel = (Button) findViewById(R.id.btnCancel);
        textView = (TextView)findViewById(R.id.AlertTitle);

       // mConfirm.setOnClickListener(this);
        mCancel.setOnClickListener(this);

        if(temp.equals("1")) {    //불꽃
            textView.setText("불꽃이 감지되었습니다!");
        }
        else if(temp.equals("2")){   //연기
            textView.setText("연기가 감지되었습니다!");
        }
        else{
            textView.setText("소화기 분사하기");
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel://취소
                this.finish();
                break;
//            case R.id.btnConfirm://확인
//                Intent intent = new Intent(v.getContext(),Alert2Activity.class);
//                startActivity(intent);
//                this.finish();
//                break;
            default:
                break;
        }
    }
}