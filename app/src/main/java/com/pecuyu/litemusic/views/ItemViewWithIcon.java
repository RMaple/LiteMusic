package com.pecuyu.litemusic.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pecuyu.litemusic.R;

/**
 * Created by pecuyu on 2017/12/28.
 */

public class ItemViewWithIcon extends LinearLayout implements View.OnClickListener, View.OnLongClickListener {

    private ImageView mIcon;
    private TextView mName;
    private TextView mDetail;
    private OnClickListener mItemClickListener;
    private OnLongClickListener mItemLongClickListener;
    private String item_text;

    public ItemViewWithIcon(Context context) {
        this(context, null);
    }

    public ItemViewWithIcon(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public ItemViewWithIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setup(context, attrs);
    }

    private void setup(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ItemViewWithIcon);
        int item_icon = ta.getResourceId(R.styleable.ItemViewWithIcon_item_icon, R.mipmap.ic_launcher);
        item_text = ta.getString(R.styleable.ItemViewWithIcon_item_text);
        String item_text_detail = ta.getString(R.styleable.ItemViewWithIcon_item_text_detail);
        ta.recycle();

        LayoutInflater.from(context).inflate(R.layout.item_layout_style, this, true);
        mIcon = (ImageView) findViewById(R.id.item_icon);
        mName = (TextView) findViewById(R.id.item_name);
        mDetail = (TextView) findViewById(R.id.item_detail);

        mIcon.setImageResource(item_icon);
        mName.setText(item_text);
        mDetail.setText(item_text_detail);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AppCompatTheme);
        Drawable drawable = typedArray.getDrawable(R.styleable.AppCompatTheme_selectableItemBackground);
        typedArray.recycle();

        setClickable(true);

        if (drawable != null) {
            setForeground(drawable);
        }

        setOnClickListener(this);
        setOnLongClickListener(this);
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

    public void setOnItemClickListener(OnClickListener listener) {
        mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnLongClickListener listener) {
        mItemLongClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), item_text, Toast.LENGTH_SHORT).show();
        if (v == this && mItemClickListener != null) {
            mItemClickListener.onClick(v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v == this && mItemLongClickListener != null) {
            mItemLongClickListener.onLongClick(v);
        }
        return false;
    }
}
