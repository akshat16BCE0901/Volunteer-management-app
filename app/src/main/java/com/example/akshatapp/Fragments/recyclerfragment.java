package com.example.akshatapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.akshatapp.R;


class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>
{
    private String[] names,emails;
    public int[] profile;
    public MyRecyclerAdapter(String[] names,String[] emails,int[] profile)
    {
        this.names = names;
        this.emails = emails;
        this.profile = profile;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rowView = (View) layoutInflater.inflate(R.layout.fragment_detailsfragment,parent,false);
        ViewHolder vh= new ViewHolder(rowView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.main_name.setText(names[position]);
        holder.main_email.setText(emails[position]);
        holder.profile_pic.setImageResource(profile[position]);
        holder.cardlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Item clicked "+position, Toast.LENGTH_LONG).show();
            }
        });

//        holder.cardlayout.setOnTouchListener(new OnSwipeTouchListener(a) {
//            public void onSwipeTop() {
//                Toast.makeText(a, "top", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeRight(int posx,int posy) {
//                Toast.makeText(a, "left to right "+position, Toast.LENGTH_LONG).show();
//            }
//            public void onSwipeLeft(int posx,int posy) {
//                Toast.makeText(a, "right to left "+position, Toast.LENGTH_LONG).show();
//            }
//            public void onSwipeBottom() {
//                Toast.makeText(a, "bottom", Toast.LENGTH_SHORT).show();
//            }
//            public void pos(int c,int d)
//            {
//                Toast.makeText(a,""+c+" "+d,Toast.LENGTH_LONG).show();
//            }
//
//        });

    }
    @Override
    public int getItemCount() {
        return names.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView main_name,main_email;
        public LinearLayout cardlayout;
        public ImageView profile_pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cardlayout = (LinearLayout)itemView.findViewById(R.id.cardlayout);
            this.main_email = (TextView)itemView.findViewById(R.id.main_email);
            this.main_name = (TextView)itemView.findViewById(R.id.main_name);
            this.profile_pic = (ImageView)itemView.findViewById(R.id.profile_pic);
        }
    }
}



public class recyclerfragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String[] name = {"Akshat Singhal","Raj Shukla","Himal Kumar Singh","Rakshit Sinha"};
    String[] email = {"akshat.singhal2016@vitstudent.ac.in","raj.shukla2016@vitstudent.ac.in","himalkumar.singh@vitstudent.ac.in","rakshit.sinha2016@vitstudent.ac.in"};
    int[] profile = {R.mipmap.profile,R.mipmap.raj,R.mipmap.himal,R.mipmap.rakshit};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public recyclerfragment() {

    }

    public static recyclerfragment newInstance(String param1, String param2) {
        recyclerfragment fragment = new recyclerfragment();
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
        // Inflate the layout for this fragment;
        View v = inflater.inflate(R.layout.fragment_recyclerfragment, container, false);
        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(name,email,profile);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return v;
    }
}
