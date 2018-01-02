package com.pecuyu.litemusic;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.pecuyu.litemusic.adapters.MainPageAdapter;
import com.pecuyu.litemusic.fragments.DiscoverFragment;
import com.pecuyu.litemusic.fragments.FriendsFragment;
import com.pecuyu.litemusic.fragments.MusicFragment;

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
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            //getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.main);
        Toolbar toolBar = (Toolbar) findViewById(R.id.id_title);
        setSupportActionBar(toolBar);
        setTitle("");
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
        fragmentList.add(new MusicFragment());
        fragmentList.add(new DiscoverFragment());
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
        ImageView imageView;
        for (int i = 0; i < mTitleNormalIcons.length; i++) {
            TabLayout.Tab tab = mTabs.getTabAt(i);
            View customView = tab.getCustomView();
            if (i == selected) {
                if (customView == null) {
                    imageView = new ImageView(MainActivity.this);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    imageView.setImageResource(mTitleSelectedIcons[i]);
                    tab.setCustomView(imageView);
                } else {
                    ((ImageView) customView).setImageResource(mTitleSelectedIcons[i]);
                }
                //tab.setIcon(mTitleSelectedIcons[i]);

            } else {
                if (customView == null) {
                    imageView = new ImageView(MainActivity.this);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    imageView.setImageResource(mTitleNormalIcons[i]);
                    tab.setCustomView(imageView);
                } else {
                    ((ImageView) customView).setImageResource(mTitleNormalIcons[i]);
                }
                //tab.setIcon(mTitleNormalIcons[i]);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tool_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_search:
                Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                Toast.makeText(getApplicationContext(), "settings", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
