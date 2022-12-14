package com.example.android_collage_fragments_l5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//style="?android:attr/borderlessButtonStyle"
    FragmentManager fragmentManager;
//    MyFragmentManagerIt myFragmentManagerIt;

    private MyFragmentManagerItZero myFragmentManagerItZero;
    private Button goToFr2_btn;
    private Button goToFr1_btn;
    private Button goToFr3_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager=getSupportFragmentManager();
//        myFragmentManagerIt=new MyFragmentManagerIt(getAllKeys(),fragmentManager);
        myFragmentManagerItZero=new MyFragmentManagerItZero();
        goToFr1_btn=findViewById(R.id.goToFr1_btn);
        goToFr2_btn=findViewById(R.id.goToFr2_btn);
        goToFr3_btn=findViewById(R.id.goToFr3_btn);


        goToFr1_btn.setOnClickListener((view)->{
            showFragment(new Fragment1(myFragmentManagerItZero));
        });

        goToFr2_btn.setOnClickListener((view)->{
            showFragment(new Fragment2(myFragmentManagerItZero));
        });
        goToFr3_btn.setOnClickListener((view)->{
            showFragment(new Fragment3(myFragmentManagerItZero));

        });

    }

    private void showFragment(Fragment fragmentToShow){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentsContainer,fragmentToShow);
        fragmentTransaction.commit();
    }
    private ArrayList<String> getAllKeys(){
        ArrayList<String> keys=new ArrayList<>();
        keys.add(Keys.CODE1);
        keys.add(Keys.CODE2);
        return keys;
    }
}