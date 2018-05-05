package com.power.kimyounghoon.project;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.IDNA;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static com.power.kimyounghoon.project.MyFirebaseMessagingService.temp;


public class MyfireActivity extends AppCompatActivity implements View.OnClickListener{

    private static String TAG = "phptest_MyfireActivity";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_STARTDATE = "startdate";
    private static final String TAG_NEXTDATE = "nextdate";
    private static final String TAG_IMAGE = "image";

    ImageView btn_extinguish2;
    ImageView btn_119;
    ImageView btn_info;
    ImageView btn_logout;

    String nextdate;

    public static Context mContext;


    //private TextView mTextViewResult;
    ArrayList<HashMap<String, String>> mArrayList;
    ListView mlistView;
    String mJsonString;

    //ImageView photo= (ImageView)findViewById(R.id.fireImage);


    @Override
    protected void onStart() {
        super.onStart();

        //mTextViewResult = (TextView)findViewById(R.id.textView_main_result);
        mlistView = (ListView) findViewById(R.id.listView);

        mArrayList = new ArrayList<>();


        GetData task = new GetData();
        task.execute(Utill.getURL("/android/index.php"));

        FloatingActionButton button = (FloatingActionButton)findViewById(R.id.addButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),AddfireActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfire);
        btn_extinguish2 = (ImageView)findViewById(R.id.btn_extinguish2);
        btn_extinguish2.setOnClickListener(this);
        btn_119 = (ImageView)findViewById(R.id.btn_119);
        btn_119.setOnClickListener(this);
        btn_info = (ImageView)findViewById(R.id.info_btn);
        btn_info.setOnClickListener(this);
        btn_logout = (ImageView)findViewById(R.id.imageButton2);
        btn_logout.setOnClickListener(this);
        mContext = this;

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.findViewById(R.id.)

    }

    @Override
    public void onClick(View v) {
        if(v == btn_extinguish2) {
            Log.i("입력완료","1");
            temp = "3";
            Intent intent = new Intent(this, AlertActivity.class);    // 분사확인 Activity
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_CANCEL_CURRENT); //FLAG_ONE_SHOT
            startActivity(intent);
        }
        else if(v == btn_119){
            String tel = "tel:119";
           // startActivity(new Intent("android.intent.action.CALL", Uri.parse(tel)));
            startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
        }
        else if(v == btn_info){
            Intent intent = new Intent(this, InformationActivity.class);    // 분사확인 Activity
            startActivity(intent);

        }
        else if(v == btn_logout)
        {
            SharedPrefManager.getInstance(MyfireActivity.this).logout();
            finish();
            startActivity(new Intent(MyfireActivity.this,LoginActivity.class));
        }
    }

    private class GetData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MyfireActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //mTextViewResult.setText(result);
            Log.d(TAG, "response  - " + result);

            if (result == null){

                //mTextViewResult.setText(errorString);
            }
            else {

                mJsonString = result;
                showResult();
            }
        }

// 서버 -> 어플로 
        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }

    String strDate;

    private void showResult(){

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);



                String name = item.getString(TAG_NAME);
                String startdate = item.getString(TAG_STARTDATE);
 //               String image = item.getString(ipurl + TAG_IMAGE);

                HashMap<String,String> hashMap = new HashMap<>();
//
//                URL url = new URL(image);
//                InputStream is = url.openStream();
//                Bitmap bitmap = BitmapFactory.decodeStream(is);
//                photo.setImageBitmap(bitmap);

                hashMap.put(TAG_NAME, name);
                hashMap.put(TAG_STARTDATE, startdate);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(startdate); //deadline이 String이에요
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.MONTH, 1);
                strDate = dateFormat.format(cal.getTime());
                Log.i("다음 점검일 : ", strDate);
                hashMap.put(TAG_NEXTDATE, strDate);
                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    MyfireActivity.this, mArrayList, R.layout.listimage,
                    new String[]{TAG_NAME,TAG_STARTDATE,TAG_NEXTDATE},
                    new int[]{R.id.name, R.id.startdate,R.id.nextdate}
            );



            mlistView.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
