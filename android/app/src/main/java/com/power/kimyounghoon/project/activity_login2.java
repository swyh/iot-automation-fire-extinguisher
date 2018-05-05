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

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class activity_login2 extends AppCompatActivity implements View.OnClickListener {
    private EditText idText, passwordText;
    private Button loginButton;
    private TextView registerButton;

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        //Util.setGlobalFont(this, getWindow().getDecorView());

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(activity_login2.this, MyfireActivity.class));
            return;
        }

        idText = (EditText) findViewById(R.id.idText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (TextView) findViewById(R.id.registerButton);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    private void userLogin(){
        final String ID = idText.getText().toString().trim();
        final String Password = passwordText.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,Utill.getURL("Login.php"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success)
                            { //로그인 성공하면
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .userLogin(
                                                jsonObject.getString("ID"),
                                                jsonObject.getString("name")

                                        );
                                startActivity(new Intent(getApplicationContext(), MyfireActivity.class));
                                finish();
                            }else
                                { //로그인 실패화면
                                AlertDialog.Builder builder = new AlertDialog.Builder(activity_login2.this);
                                builder.setMessage("아이디 또는 비밀번호가 맞지 않습니다")
                                        .setNegativeButton("다시 시도",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                null
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ID", ID);
                params.put("Password", Password);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if (view == loginButton) {
            userLogin();
        } /*else if (view == registerButton) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        */

    }
}
