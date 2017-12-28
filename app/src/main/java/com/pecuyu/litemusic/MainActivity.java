package com.pecuyu.litemusic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.pecuyu.litemusic.adapters.MainPageAdapter;
import com.pecuyu.litemusic.fragments.CloudMusicFragment;
import com.pecuyu.litemusic.fragments.FriendsFragment;
import com.pecuyu.litemusic.fragments.LocalMusicFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private int[] mTitleSelectedIcons = {R.mipmap.t_actionbar_music_selected, R.mipmap.t_actionbar_discover_selected, R.mipmap.t_actionbar_friends_selected};
    private int[] mTitleNormalIcons = {R.mipmap.t_actionbar_music_normal, R.mipmap.t_actionbar_discover_normal, R.mipmap.t_actionbar_friends_normal};
    private TabLayout mTabs;
    private MainPageAdapter mMainPageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabs = (TabLayout) findViewById(R.id.tab_titles);
        ViewPager pager = (ViewPager) findViewById(R.id.main_view_pager);
        List<Fragment> fragmentList = getFragments();
        mMainPageAdapter = new MainPageAdapter(this, getSupportFragmentManager(), fragmentList, getTabTitles());
        pager.setAdapter(mMainPageAdapter);
        mTabs.setupWithViewPager(pager);
        mTabs.addOnTabSelectedListener(new TabSelectedListener());

        setupTitleIcons(0);
    }

    @NonNull
    private List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new LocalMusicFragment());
        fragmentList.add(new CloudMusicFragment());
        fragmentList.add(new FriendsFragment());
        return fragmentList;
    }

    public String[] getTabTitles() {
        return getResources().getStringArray(R.array.tab_titles);
    }


    class TabSelectedListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            setupTitleIcons(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

    private void setupTitleIcons(int selected) {
        Log.e(TAG, "selected:" + selected);
        ImageView imageView;
        for (int i = 0; i < mTitleNormalIcons.length; i++) {
            TabLayout.Tab tab = mTabs.getTabAt(i);
            View customView = tab.getCustomView();
            if (i == selected) {
                if (customView == null) {
                    imageView = new ImageView(MainActivity.this);
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageView.setImageResource(mTitleSelectedIcons[i]);
                    tab.setCustomView(imageView);
                } else {
                    ((ImageView) customView).setImageResource(mTitleSelectedIcons[i]);
                }
                //                tab.setIcon(mTitleSelectedIcons[i]);

            } else {
                if (customView == null) {
                    imageView = new ImageView(MainActivity.this);
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageView.setImageResource(mTitleNormalIcons[i]);
                    tab.setCustomView(imageView);
                } else {
                    ((ImageView) customView).setImageResource(mTitleNormalIcons[i]);
                }
//                tab.setIcon(mTitleNormalIcons[i]);
            }
        }
    }
}
