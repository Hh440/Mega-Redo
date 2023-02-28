package com.company.book_novel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Bookopen_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Bookopen_frag extends Fragment  {
String name,url,description;
TextView txt;
ImageView img;
ReadMoreTextView rdmtxt;
RecyclerView recyclerView;
RecyclerBook_openAdapter adapter;
ImageView tg_fav;
DatabaseReference databaseReference,favref,fav_list;
boolean test_click=false;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Bookopen_frag() {
        // Required empty public constructor
    }
    Bookopen_frag(String name,String url,String description){
        this.name=name;
        this.url=url;
        this.description=description;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Bookopen_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static Bookopen_frag newInstance(String param1, String param2) {
        Bookopen_frag fragment = new Bookopen_frag();
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
        View v= inflater.inflate(R.layout.fragment_bookopen_frag, container, false);
        txt=(TextView) v.findViewById(R.id.txt_show_novel);
        img=(ImageView) v.findViewById(R.id.img_shownovel);
        Picasso.get().load(url).fit().into(img);
        rdmtxt=v.findViewById(R.id.text_view);
        txt.setText(name);
        rdmtxt.setText(description);
        databaseReference=FirebaseDatabase.getInstance().getReference("Fav");

       tg_fav = (ImageView) v.findViewById(R.id.tg_fav);
// to display fav icon
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               if (snapshot.hasChild(name)){
                  tg_fav.setImageResource(R.drawable.ic_baseline_favorite_24);
               }else{
                   tg_fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
       // to add value
   tg_fav.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           test_click=true;
           databaseReference.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                  if(test_click==true){
                      if (snapshot.hasChild(name)){
                          databaseReference.child(name).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                              @Override
                              public void onSuccess(Void unused) {
                                  Toast.makeText(getContext(), "Removed from library", Toast.LENGTH_SHORT).show();
                              }
                          });
                          test_click=false;
                      }else {
                          Map<String, Object> m = new HashMap<String, Object>();
                          m.put("name", name);
                          m.put("url", url);
                          m.put("description",description);
                          databaseReference.child(name).setValue(m).addOnSuccessListener(new OnSuccessListener<Void>() {
                              @Override
                              public void onSuccess(Void unused) {
                                  Toast.makeText(getContext(), "Added to library", Toast.LENGTH_SHORT).show();
                              }
                          });
                          test_click=false;
                      }
                  }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });

       }
   });



        recyclerView =(RecyclerView)v.findViewById(R.id.rec_frag_bookopen);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Book_chapter> options =
                new FirebaseRecyclerOptions.Builder<Book_chapter>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(name), Book_chapter.class)
                        .build();
        adapter=new RecyclerBook_openAdapter(options);
        recyclerView.setAdapter(adapter);


        return v;

    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }}

