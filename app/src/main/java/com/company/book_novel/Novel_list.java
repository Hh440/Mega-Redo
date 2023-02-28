package com.company.book_novel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Novel_list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Novel_list extends Fragment {
    RecyclerView recyclerView;
    RecyclerNovel_listAdapter adapter;
    String name,url;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Novel_list() {
        // Required empty public constructor
    }
    Novel_list(String name){
        this.name=name;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Novel_list.
     */
    // TODO: Rename and change types and number of parameters
    public static Novel_list newInstance(String param1, String param2) {
        Novel_list fragment = new Novel_list();
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
       View v=inflater.inflate(R.layout.fragment_novel_list, container, false);
       recyclerView=(RecyclerView) v.findViewById(R.id.rec_frag_novel_list);
       recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        FirebaseRecyclerOptions<Novel_java> options =
                new FirebaseRecyclerOptions.Builder<Novel_java>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(name), Novel_java.class)
                        .build();
        adapter=new RecyclerNovel_listAdapter(options);
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
    }
}