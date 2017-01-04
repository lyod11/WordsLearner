package com.example.liudmula.myapplication.dictionary;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liudmula.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class TabParentFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    private static final int NUM_PAGES = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_parent, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
       // tabLayout.setupWithViewPager(viewPager);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return view;
    }

    private void setupViewPager(ViewPager vp){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putString("word", getArguments().getString("word"));
        TranslationFragment translationFragment = new TranslationFragment();
        translationFragment.setArguments(bundle);
        ExamplesFragment examplesFragment = new ExamplesFragment();
        examplesFragment.setArguments(bundle);
        adapter.addFragment(translationFragment, "TRANSLATION");
        adapter.addFragment(examplesFragment, "EXAMPLES");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragList = new ArrayList<>();
        private final List<String> mFragListTitles = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragList.get(position);
        }

        @Override
        public int getCount() {
            return mFragList.size();
        }

        public void addFragment(Fragment frag, String title){
            mFragList.add(frag);
            mFragListTitles.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragListTitles.get(position);
        }
    }

}
