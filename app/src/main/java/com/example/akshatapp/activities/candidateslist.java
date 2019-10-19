package com.example.akshatapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
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

import com.example.akshatapp.R;
import com.example.akshatapp.listeners.OnSwipeTouchListener;

import java.io.File;
import java.util.ArrayList;

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


public class candidateslist extends AppCompatActivity {
//    final String[] names = {"Akshat","Himal","Raj","Rithwik"};
//    final String[] nicknames = {"akkidarapstar","himal_singhh","____raaaj____","rithwikawasthi"};
//    int[] imgid = {R.drawable.ic_sentiment_neutral_black_24dp,R.drawable.ic_sentiment_satisfied_black_24dp,R.drawable.ic_sentiment_very_dissatisfied_black_24dp,R.drawable.ic_sentiment_very_satisfied_black_24dp};

    private int REL_SWIPE_MIN_DISTANCE;
    private int REL_SWIPE_MAX_OFF_PATH;
    private int REL_SWIPE_THRESHOLD_VELOCITY;

    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> nicknames = new ArrayList<String>();
    ArrayList<String> fbnames = new ArrayList<String>();
    ArrayList<Integer> imgid = new ArrayList<Integer>();
    ListView lv;
    Button insert;
    SQLiteDatabase db = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.navmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.item1:
                Intent it = new Intent(candidateslist.this, NewRecord.class);
                startActivityForResult(it,1);
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(),"Item 2 selected", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(candidateslist.this,testdrawer.class);
                startActivity(a);
                return true;
            case R.id.item3:
                Toast.makeText(getApplicationContext(),"Item 3 selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item4:
                Toast.makeText(getApplicationContext(),"Item 4 selected",Toast.LENGTH_LONG).show();
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidateslist);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        lv = (ListView) findViewById(R.id.candidates_list);
        insert = (Button)findViewById(R.id.insertnew);
        names.add("Akshat Singhal");
        nicknames.add("akkidarapstar");
        fbnames.add("akkidarapstar");
        imgid.add(R.drawable.ic_sentiment_very_satisfied_black_24dp);
        db = this.openOrCreateDatabase("instagram",MODE_PRIVATE,null);
        File database = getApplicationContext().getDatabasePath("instagram");
        if(database.exists())
        {
            //db.execSQL("DROP table users");
            Toast.makeText(this, "Database is created", Toast.LENGTH_SHORT).show();
        }

        myAdapter adapter = new myAdapter(this,names,nicknames,fbnames,imgid);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(candidateslist.this, names.get(position).toString() + "  --  "+nicknames.get(position).toString(), Toast.LENGTH_SHORT).show();

            }
        });

        lv.setOnTouchListener(new OnSwipeTouchListener(candidateslist.this) {
            public void onSwipeTop() {
                Toast.makeText(candidateslist.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight(int posx,int posy) {
                Toast.makeText(candidateslist.this, "left to right "+posx+ " "+posy, Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft(int posx,int posy) {
                Toast.makeText(candidateslist.this, "right to left "+posx+ " "+posy, Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(candidateslist.this, "bottom", Toast.LENGTH_SHORT).show();
            }
            public void pos(int a,int b)
            {
                Toast.makeText(candidateslist.this,""+a+" "+b,Toast.LENGTH_LONG).show();
            }

        });

        createLists();

    }



    public void createTable()
    {
        String query = "CREATE TABLE IF NOT EXISTS `users`(id integer primary key autoincrement, name varchar(100), insta varchar(100),fb varchar(200),profileimg integer);";
        try
        {
            db.execSQL(query);
            Toast.makeText(this, "Table created ", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
    public void insertName(String name,String instaname,String fbname)
    {
        String query = "INSERT into `users`(`name`,`insta`,`fb`,`profileimg`) values('"+name+"','"+instaname+"','"+fbname+"',"+R.drawable.ic_sentiment_very_satisfied_black_24dp+")";
        try
        {
            db.execSQL(query);
            Toast.makeText(this, "Record inserted Succesfully", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void deleteUser()
    {

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
            names.clear();
            nicknames.clear();
            imgid.clear();
            fbnames.clear();
            do {
                names.add(cursor.getString(namecolumn));
                nicknames.add(cursor.getString(instanamecolumn));
                imgid.add(cursor.getInt(imgidf));
                fbnames.add(cursor.getString(fbnamecolumn));

            }while (cursor.moveToNext());
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("newname");
            String instaname = data.getStringExtra("newinstaname");
            String fbname = data.getStringExtra("newfbname");
            Toast.makeText(this, name + " --- " + instaname, Toast.LENGTH_LONG).show();
            insertName(name, instaname, fbname);
            createLists();
        }
    }
}
