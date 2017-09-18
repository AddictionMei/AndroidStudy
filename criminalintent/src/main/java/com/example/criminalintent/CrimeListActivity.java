package com.example.criminalintent;


import android.support.v4.app.Fragment;

/**
 * Created by 梅 on 2017-9-15.
 */

public class CrimeListActivity extends  SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
