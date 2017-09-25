package com.example.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Created by 梅 on 2017-9-22.
 */

public class CrimePagerActivity extends FragmentActivity {

    private static final String EXTRA_CRIME_ID="com.bignerdranch.android.criminalintent.crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent= new Intent(packageContext,CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;

    }

    /**
     * Perform initialization of all fragments and loaders.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);



        UUID crimeId=(UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);


        mViewPager=(ViewPager) findViewById(R.id.activity_crime_pager_view_pager);

        //从CrimeLab中获取数据集，然后获取activity的FragmentManager实例
        mCrimes=CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager=getSupportFragmentManager();

        //设置adapter为FragmentStatePagerAdapter的一个匿名实例
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {

                Crime crime= mCrimes.get(position);
                //将返回的Fragment添加给托管activity
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                //返回数组内的列表项
                return mCrimes.size();
            }
        });
        for (int i=0;i<mCrimes.size();i++){
            if(mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;

            }

        }
    }
}
