package com.pecuyu.litemusic.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pecuyu.litemusic.R;

/**
 * <br/>Author: pecuyu
 * <br/>Date: 2017/12/29
 * <br/>TODO:用于折叠的itemview
 */

public class CollapseItemView extends RelativeLayout implements View.OnClickListener {

    private ImageView mIvIcon;
    private TextView mTvName;
    private TextView mTvDetail;
    private OnClickListener mItemClickListener;
    private OnClickListener mMoreClickListener;
    private ImageView mIvMore;

    public CollapseItemView(Context context) {
        this(context, null);
    }

    public CollapseItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CollapseItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CollapseItemView);
        int iconId = ta.getResourceId(R.styleable.CollapseItemView_collapse_item_icon, R.mipmap.icon_cd);
        String itemName = ta.getString(R.styleable.CollapseItemView_collapse_item_name);
        String itemDetail = ta.getString(R.styleable.CollapseItemView_collapse_item_detail);
        ta.recycle();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AppCompatTheme);
        Drawable drawable = typedArray.getDrawable(R.styleable.AppCompatTheme_selectableItemBackground);
        typedArray.recycle();
        if (drawable != null) {
            setForeground(drawable);
        }

        LayoutInflater.from(context).inflate(R.layout.item_collapse_view, this);
        mIvIcon = (ImageView) findViewById(R.id.iv_item_icon);
        mTvName = (TextView) findViewById(R.id.tv_item_name);
        mTvDetail = (TextView) findViewById(R.id.tv_item_detail);
        mIvMore = (ImageView) findViewById(R.id.iv_item_more);

        mIvIcon.setImageResource(iconId);
        mTvName.setText(itemName);
        mTvDetail.setText(itemDetail);

        setOnClickListener(this);
        mIvMore.setOnClickListener(this);
    }

    public CollapseItemView setItemIcon(int iconId) {
        mIvIcon.setImageResource(iconId);
        return this;
    }

    public CollapseItemView setItemName(String name) {
        mTvName.setText(name);
        return this;
    }

    public CollapseItemView setItemDetail(String detail) {
        mTvDetail.setText(detail);
        return this;
    }

    public String getItemName() {
        return mTvName.getText().toString();
    }

    public String getItemDetail() {
        return mTvDetail.getText().toString();
    }

    //  条目
    public CollapseItemView setOnItemClickListener(OnClickListener listener) {
        mItemClickListener = listener;
        return this;
    }

    //  更多
    public CollapseItemView setOnMoreClickListener(OnClickListener listener) {
        mMoreClickListener = listener;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v == this && mItemClickListener != null) {
            mItemClickListener.onClick(v);
        }

        if (v == mIvMore && mMoreClickListener != null) {
            mMoreClickListener.onClick(v);
        }
    }
}
