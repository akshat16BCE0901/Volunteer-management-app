package com.example.akshatapp.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.fragment.app.Fragment;

import com.example.akshatapp.R;
import com.example.akshatapp.activities.User;
import com.example.akshatapp.activities.candidateslist;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link adduserfragment.OnadduserFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link adduserfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class adduserfragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View v;
    public final int CAMERA_REQUEST = 1000;
    EditText name,email,phone,address1,address2,dob,web;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference reff= db.getReference();
    Button login,register;
    ImageView iv;
    Button b1,b2,b3;
    Bitmap pic;

    public adduserfragment() {
        // Required empty public constructor
    }


    public static adduserfragment newInstance(String param1, String param2) {
        adduserfragment fragment = new adduserfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v  =  inflater.inflate(R.layout.fragment_adduserfragment, container, false);
        final RadioButton male,female;
        final RadioGroup rg;
        iv = (ImageView)v.findViewById(R.id.contactimg);
        b1 = (Button)v.findViewById(R.id.button1);
        b2 = (Button)v.findViewById(R.id.button2);
        b3 = (Button)v.findViewById(R.id.btn3);
        rg = (RadioGroup)v.findViewById(R.id.gender);
        male = (RadioButton)v.findViewById(R.id.male);
        female = (RadioButton)v.findViewById(R.id.female);
        final CheckBox html,css,js,jquery,bootstrap,php;
        html = (CheckBox)v.findViewById(R.id.html);
        css = (CheckBox)v.findViewById(R.id.css);
        js = (CheckBox)v.findViewById(R.id.javascript);
        jquery = (CheckBox)v.findViewById(R.id.jquery);
        bootstrap = (CheckBox)v.findViewById(R.id.bootstrap);
        php = (CheckBox)v.findViewById(R.id.php);
        web  =(EditText)v.findViewById(R.id.website);
        name = (EditText)v.findViewById(R.id.name);
        email = (EditText)v.findViewById(R.id.email);
        phone = (EditText)v.findViewById(R.id.phone);
        address1 = (EditText)v.findViewById(R.id.address1);
        address2 = (EditText)v.findViewById(R.id.address2);
        dob = (EditText)v.findViewById(R.id.date);

        ImageButton ib = (ImageButton)v.findViewById(R.id.profile);

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
                    selectedgender = ((RadioButton)getActivity().findViewById(rg.getCheckedRadioButtonId())).getText().toString();
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
                User obj = new User();
                String getname = name.getText().toString();
                String getemail = email.getText().toString();
                String getphone = phone.getText().toString();
                String getadd = address1.getText().toString() + " " +address2.getText().toString();
                String getgender = selectedgender;
                String getwebsite = web.getText().toString();
                obj.setName(getname);
                obj.setAddress(getadd);
                obj.setEmail(getemail);
                obj.setPhone(getphone);
                obj.setWebsite(getwebsite);
                obj.setGender(getgender);
                obj.setSkills(skills);
                String unique_id = getname + new Date().getDate()+ new Date().getTime();
                reff.child("users").child(unique_id).setValue(obj)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "Inserted into database", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(getActivity());
                alertdialogbuilder.setCancelable(false);
                alertdialogbuilder.setMessage("Are you sure want to navigate ?");
                alertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Clicked No", Toast.LENGTH_LONG).show();
                    }
                });

                alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent ii = new Intent(getActivity(), candidateslist.class);
                        getActivity().startActivity(ii);
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
                getActivity().startActivity(intent);
            }
        });

        return v;
    }

    public interface OnadduserFragmentInteractionListener {

        void onadduserFragmentInteraction(Uri uri);
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            pic = (Bitmap) data.getExtras().get("data");
            iv.setImageBitmap(pic);
        }

    }
}
