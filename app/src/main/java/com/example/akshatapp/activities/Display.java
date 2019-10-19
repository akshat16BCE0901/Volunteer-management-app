package com.example.akshatapp.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.akshatapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Display extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        tv = (TextView)findViewById(R.id.disp);
        Bundle bd = getIntent().getExtras();
        String details = bd.getString("data");
        tv.setText(details);
        try {
            JSONObject obj = new JSONObject(details);
            String name = obj.get("name").toString();
            String email = obj.get("email").toString();
            Date dob = new Date(obj.get("dob").toString());
            String phone = obj.get("dob").toString();
            String address = obj.get("address").toString();
            String website = obj.get("website").toString();
            String gender = obj.get("gender").toString();
            String skills = obj.get("skills").toString();
            skills = skills.replace('[',' ');
            skills = skills.replace(']',' ');
            ArrayList<String> sk = new ArrayList<>(Arrays.asList(skills.trim().split(",")));


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
