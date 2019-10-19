package com.example.akshatapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.akshatapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class implicitintent extends AppCompatActivity {

    public final int CAMERA_REQUEST = 1000;
    EditText name,email,phone,address1,address2,dob,web;
    Button login,register;
    ImageView iv;
    Bitmap pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicitintent);

        Button b1,b2,b3;
        final RadioButton male,female;
        final RadioGroup rg;
        iv = (ImageView)findViewById(R.id.contactimg);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.btn3);
        rg = (RadioGroup)findViewById(R.id.gender);
        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);
        final CheckBox html,css,js,jquery,bootstrap,php;
        html = (CheckBox)findViewById(R.id.html);
        css = (CheckBox)findViewById(R.id.css);
        js = (CheckBox)findViewById(R.id.javascript);
        jquery = (CheckBox)findViewById(R.id.jquery);
        bootstrap = (CheckBox)findViewById(R.id.bootstrap);
        php = (CheckBox)findViewById(R.id.php);
        web  =(EditText)findViewById(R.id.website);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);
        address1 = (EditText)findViewById(R.id.address1);
        address2 = (EditText)findViewById(R.id.address2);
        dob = (EditText)findViewById(R.id.date);

        ImageButton ib = (ImageButton)findViewById(R.id.profile);

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it,CAMERA_REQUEST);

            }

        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedgender = "";
                if(rg.getCheckedRadioButtonId()==-1)
                {
                    selectedgender = "Gender not Selected";
                }
                else
                {
                    selectedgender = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
                }

                ArrayList<String> skills = new ArrayList<>();
                if(html.isChecked())
                {
                    skills.add("HTML");
                }
                if(css.isChecked())
                {
                    skills.add("CSS");
                }
                if(js.isChecked())
                {
                    skills.add("JavaScript");
                }
                if(jquery.isChecked())
                {
                    skills.add("jQuery");
                }
                if(bootstrap.isChecked())
                {
                    skills.add("Bootstrap");
                }
                if(php.isChecked())
                {
                    skills.add("PHP");
                }
                String skls = skills.toString();
                JSONObject obj = new JSONObject();
                try {
                    obj.put("name",name.getText().toString().trim());
                    obj.put("email",email.getText().toString().trim());
                    obj.put("dob",dob.getText());
                    obj.put("phone",phone.getText().toString().trim());
                    obj.put("address",address1.getText().toString().trim()+" "+address2.getText().toString().trim());
                    obj.put("website",web.getText().toString());
                    obj.put("gender",selectedgender);
                    obj.put("skills",skls);
                    obj.put("img",pic);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                Toast.makeText(implicitintent.this, "Gender : "+selectedgender+ " and skills : "+ skls, Toast.LENGTH_LONG).show();
                Intent newint = new Intent(implicitintent.this, Display.class);
                newint.putExtra("data",obj.toString());
                startActivity(newint);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(implicitintent.this, "Clicked register", Toast.LENGTH_LONG).show();
                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(implicitintent.this);
                alertdialogbuilder.setCancelable(false);
                alertdialogbuilder.setMessage("Are you sure want to navigate ?");
                alertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Clicked No", Toast.LENGTH_LONG).show();
                    }
                });

                alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent ii = new Intent(getApplicationContext(), candidateslist.class);
                        startActivity(ii);
                    }
                });
                alertdialogbuilder.setIcon(R.mipmap.midfing);
                AlertDialog ad = alertdialogbuilder.create();
                ad.setTitle("Tussi Jaare ho ?");
                ad.show();


            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = web.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            pic = (Bitmap) data.getExtras().get("data");
            iv.setImageBitmap(pic);
        }

    }

}
