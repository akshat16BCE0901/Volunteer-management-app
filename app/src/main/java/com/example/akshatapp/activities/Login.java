package com.example.akshatapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.akshatapp.R;

public class Login extends AppCompatActivity {

    Button b1,b2;
    EditText user,pass,confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        b1 = (Button)findViewById(R.id.login);
        b2 = (Button)findViewById(R.id.signup);
        user  = (EditText)findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.password);
        confirm = (EditText)findViewById(R.id.confirm);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a= user.getText().toString();
                String b = pass.getText().toString();
                String c = confirm.getText().toString();
                if(a.isEmpty())
                {
                    user.setError("Enter Username");
                    user.requestFocus();
                }
                else if (b.isEmpty())
                {
                    pass.setError("Enter password");
                    pass.requestFocus();
                }
                else if(c.isEmpty())
                {
                    confirm.setError("Fill out this field");
                    confirm.requestFocus();
                }
                else
                {
                    if(!a.equals(new String("Akshat")))
                    {
                        Toast.makeText(Login.this, "Invalid Username", Toast.LENGTH_SHORT).show();
                    }
                    else if(!b.equals(c))
                    {
                        Toast.makeText(Login.this,"Passwords do not match",Toast.LENGTH_LONG).show();
                    }
                    else if (!b.equals(new String("akshat_71")))
                    {
                        Toast.makeText(Login.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent it = new Intent(Login.this,Welcome.class);
                        it.putExtra("Username",a);
                        it.putExtra("Password",b);
                        startActivity(it);
                        finish();
                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "Click sign up", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
