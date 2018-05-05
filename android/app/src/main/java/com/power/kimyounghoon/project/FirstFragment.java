package com.power.kimyounghoon.project;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by KIMYOUNGHOON on 2017-08-17.
 */

public class FirstFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.first_fragment,container,false);

        Button button1 = (Button)v.findViewById(R.id.myfire);
        Button button2 = (Button)v.findViewById(R.id.information);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),MyfireActivity.class);
                    startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),InformationActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
