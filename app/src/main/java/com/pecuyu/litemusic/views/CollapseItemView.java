package com.pecuyu.litemusic.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.pecuyu.litemusic.R;

/**
 * <br/>Author: pecuyu
 * <br/>Date: 2017/12/29
 * <br/>TODO:用于折叠的itemview
 */

public class CollapseItemView extends RelativeLayout {
    public CollapseItemView(Context context) {
        this(context,null);
    }

    public CollapseItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CollapseItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.item_collapse_view, this);


    }
}
