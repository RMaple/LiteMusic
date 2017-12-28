package com.pecuyu.litemusic.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pecuyu.litemusic.R;

/**
 * Created by pecuyu on 2017/12/28.
 */

public class ItemViewWithIcon extends LinearLayout {

    private View mRoot;
    private ImageView mIcon;
    private TextView mName;
    private TextView mDetail;

    public ItemViewWithIcon(Context context) {
        this(context, null);
    }

    public ItemViewWithIcon(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public ItemViewWithIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRoot = LayoutInflater.from(context).inflate(R.layout.item_layout_style, this);
        int item_icon = attrs.getAttributeResourceValue(R.styleable.ItemViewWithIcon_item_icon, R.mipmap.ic_launcher);
        String item_text = attrs.getAttributeValue(R.styleable.ItemViewWithIcon_item_text);
        String item_text_detail = attrs.getAttributeValue(R.styleable.ItemViewWithIcon_item_text_detail);

        mIcon = (ImageView) mRoot.findViewById(R.id.item_icon);
        mName = (TextView) mRoot.findViewById(R.id.item_name);
        mDetail = (TextView) mRoot.findViewById(R.id.item_detail);

        mIcon.setImageResource(item_icon);
        mName.setText(item_text);
        mDetail.setText(item_text_detail);
    }


    public View getmRoot() {
        return mRoot;
    }

    public ImageView getmIcon() {
        return mIcon;
    }

    public TextView getmName() {
        return mName;
    }

    public TextView getmDetail() {
        return mDetail;
    }

    public void setmIcon(ImageView mIcon) {
        this.mIcon = mIcon;
    }

    public void setmName(TextView mName) {
        this.mName = mName;
    }

    public void setmDetail(TextView mDetail) {
        this.mDetail = mDetail;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
