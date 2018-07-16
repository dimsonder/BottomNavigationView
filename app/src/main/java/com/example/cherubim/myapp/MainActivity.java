package com.example.cherubim.myapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Fragment> fragments;
    private int lastShowFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   switchFrament(lastShowFragment,0);
                    lastShowFragment = 0;
                    return true;
                case R.id.navigation_dashboard:
                    switchFrament(lastShowFragment,1);
                    lastShowFragment = 1;

                    return true;
                case R.id.navigation_notifications:
                    switchFrament(lastShowFragment,2);
                    lastShowFragment=2;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * 切换Fragment
     *
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    public void switchFrament(int lastIndex, int index) {
        Log.e(TAG,"last--"+lastIndex+"----index--"+index);
        if (lastIndex!=index) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(fragments.get(lastIndex));
            if (!fragments.get(index).isAdded()) {
                transaction.add(R.id.container, fragments.get(index));

            }
            transaction.show(fragments.get(index)).commitAllowingStateLoss();
        }

    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        lastShowFragment = 0;
      getSupportFragmentManager().beginTransaction().add(R.id.container, fragments.get(0))
                .show(fragments.get(0))
                .commit();
    }


}
