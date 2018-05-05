package com.power.kimyounghoon.project;

import android.*;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Alert2Activity extends Activity implements
        View.OnClickListener {

    private Button mCancel;

    ImageView btn_119;

    //String id_data = "dudgns987";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alert2);
        btn_119 = (ImageView)findViewById(R.id.btn_119_2);
        btn_119.setOnClickListener(this);


//        Alert2Activity.registDB rdb = new Alert2Activity.registDB();
//        rdb.execute();

        setContent();
    }

    private void setContent() {
        mCancel = (Button) findViewById(R.id.mCancel);

        mCancel.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mCancel:
                this.finish();
                break;
            case R.id.btn_119_2:
                String tel = "tel:119";
                // startActivity(new Intent("android.intent.action.CALL", Uri.parse(tel)));
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                this.finish();
                break;
            default:
                break;
        }
    }

}   /////

//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case 1: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//            }
//
//            // other 'case' lines to check for other
//            // permissions this app might request
//        }
//    }
//
//
//
//
//
//    public class registDB extends AsyncTask<Void, Integer, Void> { //DB로 소화기 정보 보냄
//
//        @Override
//        protected Void doInBackground(Void... unused) {
//
///* 인풋 파라메터값 생성 */
//
//
//            String param = "Data1=1";
//            try {
///* 서버연결 */
//                //URL url = new URL(IP+"/android/extinguish.php");
//                URL url = new URL("192.168.42.113/push.php");
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
//                conn.connect();
//
///* 안드로이드 -> 서버 파라메터값 전달 */
//                OutputStream outs = conn.getOutputStream();
//                outs.write(param.getBytes("UTF-8"));
//                outs.close();
//
///* 서버 -> 안드로이드 파라메터값 전달 */
//                InputStream is = null;
//                BufferedReader in = null;
//                String data = "";
//
//                is = conn.getInputStream();
//                in = new BufferedReader(new InputStreamReader(is), 8 * 1024);
//                String line = null;
//                StringBuffer buff = new StringBuffer();
//                while ( ( line = in.readLine() ) != null )
//                {
//                    buff.append(line + "\n");
//                }
//                data = buff.toString().trim();
//                Log.e("RECV DATA",data);
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//    }
//}
