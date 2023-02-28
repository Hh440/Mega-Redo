package com.company.book_novel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link main_listview#newInstance} factory method to
 * create an instance of this fragment.
 */
public class main_listview extends Fragment {
RecyclerView recyclerView;
RecyclerWebsiteAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public main_listview() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment main_listview.
     */
    // TODO: Rename and change types and number of parameters
    public static main_listview newInstance(String param1, String param2) {
        main_listview fragment = new main_listview();
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
        View view= inflater.inflate(R.layout.fragment_main_listview, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.mainlist_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<Website_java> options =
                new FirebaseRecyclerOptions.Builder<Website_java>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Browser"), Website_java.class)
                        .build();
        adapter=new RecyclerWebsiteAdapter(options);
        recyclerView.setAdapter(adapter);
        return view;
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem item= menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("search here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ProcessSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ProcessSearch(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    public void ProcessSearch(String s){
        FirebaseRecyclerOptions<Website_java> options =
                new FirebaseRecyclerOptions.Builder<Website_java>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Browser").orderByChild("name").startAt(s).endAt(s+"\uf8ff"), Website_java.class)
                        .build();
        adapter=new RecyclerWebsiteAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}