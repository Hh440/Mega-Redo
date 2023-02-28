package com.company.book_novel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
BottomNavigationView btnvw;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =findViewById(R.id.tool_bar);
        btnvw= findViewById(R.id.bttmvg);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Book_Novel");

        btnvw.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if (id==R.id.nav_home){

                }else if(id==R.id.nav_browser){
                    loadfrag(new main_listview(),false);
                    toolbar.setSubtitle("Browser");

                }else if(id==R.id.nav_library){
                    loadfrag(new frag_library(),false);
                    toolbar.setSubtitle("Library");
                }else{

                }
                return true;
            }
        });
        btnvw.setSelectedItemId(R.id.nav_home);
    }
    private void loadfrag(Fragment fragment,Boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag){
            ft.add(R.id.containerframe,fragment);
        }else{
            ft.replace(R.id.containerframe,fragment);
        }
        ft.commit();



    }

}