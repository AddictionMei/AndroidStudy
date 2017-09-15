package com.example.criminalintent;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 梅 on 2017-9-15.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();

}
