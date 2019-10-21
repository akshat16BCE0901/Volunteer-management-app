package com.example.akshatapp.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.akshatapp.R;
import com.example.akshatapp.activities.NewRecord;
import com.example.akshatapp.activities.testdrawer;
import com.example.akshatapp.listeners.OnSwipeTouchListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.ACTION_VIEW;

class myAdapter extends ArrayAdapter<String>
{
    private ArrayList<Integer> imgid;
    private AppCompatActivity context;
    private ArrayList<String> instanames,names,fbnames;
    public myAdapter(AppCompatActivity context, ArrayList<String> names, ArrayList<String> instanames, ArrayList<String> fbnames, ArrayList<Integer> imgid)
    {
        super(context,R.layout.candidate_custom,names);
        this.imgid = imgid;
        this.context = context;
        this.instanames = instanames;
        this.fbnames = fbnames;
        this.names = names;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView;
        LayoutInflater inflater = context.getLayoutInflater();
        rowView = inflater.inflate(R.layout.candidate_custom,null,true);
        TextView name = (TextView) rowView.findViewById(R.id.namecustom);
        final TextView fbi = (TextView)rowView.findViewById(R.id.fb);
        final TextView insta = (TextView) rowView.findViewById(R.id.nameinsta);
        ImageView img = (ImageView) rowView.findViewById(R.id.imgcustom);
        ImageButton btninsta = (ImageButton)rowView.findViewById(R.id.btninsta);
        ImageButton btnfb = (ImageButton)rowView.findViewById(R.id.btnfb);

        btninsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ACTION_VIEW);
                String url = "https://www.instagram.com/"+insta.getText().toString()+"/";
                in.setData(Uri.parse(url));
                context.startActivity(in);
            }
        });
        btnfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ACTION_VIEW);
                String url = "https://www.facebook.com/"+fbi.getText().toString()+"/";
                in.setData(Uri.parse(url));
                context.startActivity(in);
            }
        });
        name.setText(names.get(position));
        insta.setText(instanames.get(position));
        img.setImageResource(imgid.get(position));
        fbi.setText(fbnames.get(position));
        return rowView;

    }

    @Override
    public int getCount() {
        return names.size();
    }
}

public class candidateslistfragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> nicknames = new ArrayList<String>();
    ArrayList<String> fbnames = new ArrayList<String>();
    ArrayList<Integer> imgid = new ArrayList<Integer>();
    ArrayList<Integer> ide = new ArrayList<Integer>();
    ListView lv;
    Button insert;
    SQLiteDatabase db = null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public candidateslistfragment() {
        setHasOptionsMenu(true);
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment candidateslistfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static candidateslistfragment newInstance(String param1, String param2) {
        candidateslistfragment fragment = new candidateslistfragment();
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navmenu, menu);
        super.onCreateOptionsMenu(menu,menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.item1:
                Intent it = new Intent(getActivity(), NewRecord.class);
                startActivityForResult(it,1);
                return true;
            case R.id.item2:
                Toast.makeText(getActivity(),"Item 2 selected", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(getActivity(), testdrawer.class);
                startActivity(a);
                return true;
            case R.id.item3:
                Toast.makeText(getActivity(),"Item 3 selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item4:
                Toast.makeText(getActivity(),"Item 4 selected",Toast.LENGTH_LONG).show();
                return true;
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        v = inflater.inflate(R.layout.fragment_candidateslistfragment, container, false);
        lv = (ListView)v.findViewById(R.id.candidates_list);
        insert = (Button)v.findViewById(R.id.insertnew);
        names.add("Akshat Singhal");
        nicknames.add("akkidarapstar");
        fbnames.add("akkidarapstar");
        ide.add(1);
        imgid.add(R.drawable.ic_sentiment_very_satisfied_black_24dp);
        db = getActivity().openOrCreateDatabase("instagram",MODE_PRIVATE,null);
        File database = getActivity().getDatabasePath("instagram");
        if(database.exists())
        {
            Log.e("DATABASE","Connection Successful");
            //db.execSQL("DROP table users");
//            Toast.makeText(this, "Database is created", Toast.LENGTH_SHORT).show();
        }

        myAdapter adapter = new myAdapter((AppCompatActivity) getActivity(),names,nicknames,fbnames,imgid);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), names.get(position).toString() + "  --  "+nicknames.get(position).toString(), Toast.LENGTH_SHORT).show();

            }
        });

        lv.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            public void onSwipeTop() {
                Toast.makeText(getActivity(), "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight(int posx,int posy) {
                int position = lv.pointToPosition(posx,posy);
                Toast.makeText(getActivity(), "left to right "+position, Toast.LENGTH_LONG).show();
                deleteUser(position);
            }
            public void onSwipeLeft(int posx,int posy) {
                int position = lv.pointToPosition(posx,posy);
                Toast.makeText(getActivity(), "right to left "+position, Toast.LENGTH_LONG).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(getActivity(), "bottom", Toast.LENGTH_SHORT).show();
            }
            public void pos(int a,int b)
            {
                Toast.makeText(getActivity(),""+a+" "+b,Toast.LENGTH_LONG).show();
            }

        });

        createLists();
        return v;
    }

    public void createTable()
    {
        String query = "CREATE TABLE IF NOT EXISTS `users`(id integer primary key autoincrement, name varchar(100), insta varchar(100),fb varchar(200),profileimg integer);";
        try
        {
            db.execSQL(query);
            Toast.makeText(getActivity(), "Table created ", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
    public void insertName(String name,String instaname,String fbname)
    {
        String query = "INSERT into `users`(`name`,`insta`,`fb`,`profileimg`) values('"+name+"','"+instaname+"','"+fbname+"',"+R.drawable.ic_sentiment_very_satisfied_black_24dp+")";
        try
        {
            db.execSQL(query);
            Toast.makeText(getActivity(), "Record inserted Succesfully", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(getActivity(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void deleteUser(int pos)
    {
        int id = ide.get(pos);
        String named = names.get(pos);
        String sql = "DELETE from `users` where `id`="+id;
        try {
            db.execSQL(sql);
            Toast.makeText(getActivity(), "Deleting  - "+named, Toast.LENGTH_LONG).show();

            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    Fragment fr = new candidateslistfragment();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.framecontainer,fr)
                            .commit();
                }
            },3000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    public void createLists()
    {
        Cursor cursor = db.rawQuery("select * from users;",null);
        cursor.moveToFirst();
        int idcolumn = cursor.getColumnIndex("id");
        int namecolumn = cursor.getColumnIndex("name");
        int instanamecolumn = cursor.getColumnIndex("insta");
        int fbnamecolumn = cursor.getColumnIndex("fb");
        int imgidf= cursor.getColumnIndex("profileimg");
        if((cursor!=null) && (cursor.getCount()>0))
        {
            ide.clear();
            names.clear();
            nicknames.clear();
            imgid.clear();
            fbnames.clear();
            do {
                ide.add(cursor.getInt(idcolumn));
                names.add(cursor.getString(namecolumn));
                nicknames.add(cursor.getString(instanamecolumn));
                imgid.add(cursor.getInt(imgidf));
                fbnames.add(cursor.getString(fbnamecolumn));

            }while (cursor.moveToNext());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("newname");
            String instaname = data.getStringExtra("newinstaname");
            String fbname = data.getStringExtra("newfbname");
            Toast.makeText(getActivity(), name + " --- " + instaname, Toast.LENGTH_LONG).show();
            insertName(name, instaname, fbname);
            createLists();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event

}
