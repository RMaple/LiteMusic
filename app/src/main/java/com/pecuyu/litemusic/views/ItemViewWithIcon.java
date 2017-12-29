package com.pecuyu.litemusic.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pecuyu.litemusic.R;

/**
 * Created by pecuyu on 2017/12/28.
 */

public class ItemViewWithIcon extends LinearLayout {

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

//        int item_icon = attrs.getAttributeResourceValue(R.styleable.ItemViewWithIcon_item_icon, R.mipmap.ic_launcher);
//        String item_text = attrs.getAttributeValue(R.styleable.ItemViewWithIcon_item_text);
//        String item_text_detail = attrs.getAttributeValue(R.styleable.ItemViewWithIcon_item_text_detail);

        setup(context, attrs);
    }

    private void setup(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ItemViewWithIcon);
        int item_icon = ta.getResourceId(R.styleable.ItemViewWithIcon_item_icon, R.mipmap.ic_launcher);
        String item_text = ta.getString(R.styleable.ItemViewWithIcon_item_text);
        String item_text_detail = ta.getString(R.styleable.ItemViewWithIcon_item_text_detail);
        ta.recycle();

        LayoutInflater.from(context).inflate(R.layout.item_layout_style, this, true);
        mIcon = (ImageView) findViewById(R.id.item_icon);
        mName = (TextView) findViewById(R.id.item_name);
        mDetail = (TextView) findViewById(R.id.item_detail);

        mIcon.setImageResource(item_icon);
        mName.setText(item_text);
        mDetail.setText(item_text_detail);

        setClickable(true);
    }

    public ImageView getIcon() {
        return mIcon;
    }

    public TextView getName() {
        return mName;
    }

    public TextView getDetail() {
        return mDetail;
    }

    public void setIcon(ImageView mIcon) {
        this.mIcon = mIcon;
    }

    public void setName(TextView mName) {
        this.mName = mName;
    }

    public void setDetail(TextView mDetail) {
        this.mDetail = mDetail;
    }

    public void setItemOnClickListener(OnClickListener listener) {
        setOnClickListener(listener);
    }

    public void setOnItemLongClickListener(OnLongClickListener listener) {
        setOnLongClickListener(listener);
    }
}
