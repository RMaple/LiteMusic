package com.pecuyu.litemusic.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pecuyu.litemusic.R;
import com.pecuyu.litemusic.adapters.OnGestureListenerAdapter;

/**
 * <br/>Author: pecuyu
 * <br/>Date: 2017/12/29
 * <br/>TODO: 可折叠式条目容器
 */

public class CollapseItemViewContainer extends LinearLayout implements View.OnClickListener {

    private LinearLayout mItemContainer;
    private boolean isCollapsed = false;
    private GestureDetector mGestureDetector;
    private ImageView mItemIndicator;
    private ImageView mItemManager;

    private ContainerCollapseChangeListener mCollapseChangeListener;
    private OnClickListener mItemClickListener;
    private Context mContext;

    public CollapseItemViewContainer(Context context) {
        this(context, null);
    }

    public CollapseItemViewContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CollapseItemViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        View root = LayoutInflater.from(context).inflate(R.layout.item_collapse_view_container, this, true);
        root.setOnClickListener(this);
        mItemContainer = (LinearLayout) root.findViewById(R.id.ll_item_view_container);
        mItemIndicator = (ImageView) root.findViewById(R.id.iv_item_indicator);
        mItemManager = (ImageView) root.findViewById(R.id.iv_item_manager);
        mItemContainer.clearAnimation();
        setClickable(true);

        // 手势识别
        mGestureDetector = new GestureDetector(context, new ItemGestureListener());

        // 默认折叠
        collapseItemViews();
    }

    public void addItemView(CollapseItemView itemView) {
        mItemContainer.addView(itemView);
    }

    public void addItemView(View itemView) {
        mItemContainer.addView(itemView);
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
        mItemContainer.setVisibility(View.VISIBLE);
        isCollapsed = false;
        startItemIndicatorRotationAnimation(0, 90);

        if (mCollapseChangeListener != null) mCollapseChangeListener.onItemViewShow(mItemContainer);
    }

    /**
     * 折叠
     */
    private void collapseItemViews() {
        mItemContainer.setVisibility(View.GONE);
        isCollapsed = true;
        startItemIndicatorRotationAnimation(90, 0);

        if (mCollapseChangeListener != null)
            mCollapseChangeListener.onItemViewCollapse(mItemContainer);
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
        if (mItemContainer.getChildCount() == 0) {
            Toast.makeText(mContext, "当前条目无内容!", Toast.LENGTH_SHORT).show();
        }
        if (mItemClickListener != null) {
            mItemClickListener.onClick(v);
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
            if (mItemContainer.getChildCount() != 0)
                switchCollapseState();
            return true;
        }
    }

    public void setItemManagerClickListener(OnClickListener listener) {
        mItemManager.setOnClickListener(listener);
    }

    public void setOnItemClickListener(OnClickListener listener) {
        this.mItemClickListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }
}
