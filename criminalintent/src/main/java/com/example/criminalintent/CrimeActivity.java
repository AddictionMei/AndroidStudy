package com.example.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class CrimeActivity extends AppCompatActivity {
//    Called when the activity first created.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment =fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = new CrimeFragment();
            //创建提交了一个Fragment事务
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();



        }


    }
}
