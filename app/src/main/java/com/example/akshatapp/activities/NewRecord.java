package com.example.akshatapp.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.akshatapp.R;

public class NewRecord extends AppCompatActivity {

    EditText name,username,fbname;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);
        name = (EditText)findViewById(R.id.newname);
        username = (EditText)findViewById(R.id.newinstaname);
        fbname = (EditText)findViewById(R.id.fbname);
        b1 = (Button)findViewById(R.id.insertbtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nrec = name.getText().toString();
                String nirec = username.getText().toString();
                String newfb = fbname.getText().toString();
                Intent in = new Intent(NewRecord.this, candidateslist.class);
                in.putExtra("newname",nrec);
                in.putExtra("newinstaname",nirec);
                in.putExtra("newfbname",newfb);
                setResult(RESULT_OK,in);
                finish();
            }
        });
    }
}