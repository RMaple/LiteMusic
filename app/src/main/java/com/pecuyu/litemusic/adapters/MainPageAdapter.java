package com.pecuyu.litemusic.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * <br/>Author: pecuyu
 * <br/>Email: yu.qin@ck-telecom.com
 * <br/>Date: 2017/12/28
 * <br/>TODO:
 */

public class MainPageAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private final FragmentManager fm;
    private final List<Fragment> fragmentList;
    private String[] tabTitles;

    public MainPageAdapter(Context context, FragmentManager fm, List<Fragment> fragmentList, String[] tabTitles) {
        super(fm);
        this.mContext = context;
        this.fm = fm;
        this.fragmentList = fragmentList;
        this.tabTitles = tabTitles;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return tabTitles[position];
//    }

//    public CharSequence getPageTitle(int position) {
//        Drawable drawable = null;
//        switch (position) {
//            case 0:
//                drawable = ContextCompat.getDrawable(mContext, R.mipmap.t_actionbar_music_normal);
//                break;
//            case 1:
//                drawable = ContextCompat.getDrawable(mContext, R.mipmap.t_actionbar_discover_normal);
//                break;
//            case 2:
//                drawable = ContextCompat.getDrawable(mContext, R.mipmap.t_actionbar_friends_normal);
//                break;
//        }
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
//        SpannableString spannableString = new SpannableString(" ");
//        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        return spannableString;
//    }


}
