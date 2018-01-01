package com.pecuyu.litemusic.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pecuyu.litemusic.R;
import com.pecuyu.litemusic.adapters.OnGestureListenerAdapter;

/**
 * <br/>Author: pecuyu
 * <br/>Date: 2017/12/29
 * <br/>TODO: 可折叠式条目容器
 */

public class CollapseItemViewContainer extends LinearLayout implements View.OnClickListener {

    private LinearLayout mItemViewContainer;
    private boolean isCollapsed = false;
    private ImageView mItemIndicator;
    private ImageView mItemManager;

    private ContainerCollapseChangeListener mCollapseChangeListener;
    private OnClickListener mItemTitleClickListener;
    private Context mContext;
    private RelativeLayout mTitleContainer;
    private OnClickListener mItemManagerClickListener;
    private int mIconId;
    private String mTitleNameStr;
    private int mCount;
    private ImageView mIvIndicator;
    private TextView mTvTitleName;
    private TextView mTvCount;

    public CollapseItemViewContainer(Context context) {
        this(context, null);
    }

    public CollapseItemViewContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CollapseItemViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        // 获取属性值
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CollapseItemViewContainer);
        mIconId = ta.getResourceId(R.styleable.CollapseItemViewContainer_item_title_icon, R.mipmap.item_indicator);
        mTitleNameStr = ta.getString(R.styleable.CollapseItemViewContainer_item_title_name);
        mCount = ta.getInt(R.styleable.CollapseItemViewContainer_item_detail, 0);
        ta.recycle();

        // 初始化布局
        View root = LayoutInflater.from(context).inflate(R.layout.item_collapse_view_container, this, true);
        mItemViewContainer = (LinearLayout) root.findViewById(R.id.ll_item_view_container);
        mItemIndicator = (ImageView) root.findViewById(R.id.iv_item_indicator);
        mItemManager = (ImageView) root.findViewById(R.id.iv_item_manager);
        mTitleContainer = (RelativeLayout) root.findViewById(R.id.item_container_title);
        mIvIndicator = (ImageView) mTitleContainer.findViewById(R.id.iv_item_indicator);
        mTvTitleName = (TextView) mTitleContainer.findViewById(R.id.tv_item_title_name);
        mTvCount = (TextView) mTitleContainer.findViewById(R.id.tv_item_count);

        mIvIndicator.setImageResource(mIconId);
        mTvTitleName.setText(mTitleNameStr);
        mTvCount.setText("(" + mCount + ")");

        // 设置监听
        mTitleContainer.setOnClickListener(this);
        mItemManager.setOnClickListener(this);

        setClickable(true);


        // 默认折叠
        collapseItemViews();
    }

    public void addItemView(CollapseItemView itemView) {
        mItemViewContainer.addView(itemView);
        mTvCount.setText("(" + mItemViewContainer.getChildCount() + ")");
    }

    public void addItemView(View itemView) {
        mItemViewContainer.addView(itemView);
        mTvCount.setText("(" + mItemViewContainer.getChildCount() + ")");
    }

    public void switchCollapseState() {
        if (isCollapsed) {
            showItemViews();
        } else {
            collapseItemViews();
        }
    }

    /**
     * 展开
     */
    private void showItemViews() {
        mItemViewContainer.setVisibility(View.VISIBLE);
        isCollapsed = false;
        startItemIndicatorRotationAnimation(0, 90);

        if (mCollapseChangeListener != null)
            mCollapseChangeListener.onItemViewShow(mItemViewContainer);
    }

    /**
     * 折叠
     */
    private void collapseItemViews() {
        mItemViewContainer.setVisibility(View.GONE);
        isCollapsed = true;
        startItemIndicatorRotationAnimation(90, 0);

        if (mCollapseChangeListener != null)
            mCollapseChangeListener.onItemViewCollapse(mItemViewContainer);
    }

    /**
     * 指示器旋转动画
     *
     * @param fromDegree 开始degree
     * @param toDegree   结束degree
     */
    private void startItemIndicatorRotationAnimation(int fromDegree, int toDegree) {
        RotateAnimation ra = new RotateAnimation(fromDegree, toDegree, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(300);
        ra.setInterpolator(new AccelerateDecelerateInterpolator());
        ra.setFillAfter(true);
        mItemIndicator.startAnimation(ra);
    }

    // 设置折叠事件
    public void setOnCollapseChangeListener(@Nullable ContainerCollapseChangeListener listener) {
        this.mCollapseChangeListener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_container_title:
                if (mItemViewContainer.getChildCount() == 0) {
                    Toast.makeText(mContext, "当前条目无内容!", Toast.LENGTH_SHORT).show();
                } else {
                    switchCollapseState();
                }
                if (mItemTitleClickListener != null) {
                    mItemTitleClickListener.onClick(v);
                }
                break;

            case R.id.iv_item_manager:
                if (mItemManagerClickListener != null) {
                    mItemManagerClickListener.onClick(v);
                }
                break;
        }
    }

    /**
     * 折叠状态改变监听
     */
    public interface ContainerCollapseChangeListener {
        void onItemViewCollapse(ViewGroup parent);

        void onItemViewShow(ViewGroup parent);
    }

    private class ItemGestureListener extends OnGestureListenerAdapter {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (mItemViewContainer.getChildCount() != 0)
                switchCollapseState();
            return true;
        }
    }

    public void setItemManagerClickListener(OnClickListener listener) {
        mItemManagerClickListener = listener;
    }

    public void setOnItemTitleClickListener(OnClickListener listener) {
        this.mItemTitleClickListener = listener;
    }

}
