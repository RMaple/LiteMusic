package com.pecuyu.litemusic.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.pecuyu.litemusic.R;

/**
 * <br/>Author: pecuyu
 * <br/>Date: 2017/12/29
 * <br/>TODO: 可折叠式条目容器
 */

public class CollapseItemViewContainer extends LinearLayout {

    private LinearLayout mItemContainer;
    private boolean isCollapsed = false;

    public CollapseItemViewContainer(Context context) {
        this(context, null);
    }

    public CollapseItemViewContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CollapseItemViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View root = LayoutInflater.from(context).inflate(R.layout.item_collapse_view_container, this, true);
        mItemContainer = (LinearLayout) root.findViewById(R.id.ll_item_view_container);
        mItemContainer.clearAnimation();
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

    private void showItemViews() {
        mItemContainer.setVisibility(View.VISIBLE);
        isCollapsed = false;
        if (listener != null) listener.onContainerShow(mItemContainer);
    }

    private void collapseItemViews() {
        mItemContainer.setVisibility(View.GONE);
        isCollapsed = true;
        if (listener != null) listener.onContainerCollapse(mItemContainer);
    }

    // 设置折叠事件
    public void setOnContainerCollapseChangeListener(@Nullable ContainerCollapseChangeListener listener) {
        this.listener = listener;
    }

    private ContainerCollapseChangeListener listener;

    public interface ContainerCollapseChangeListener {
        void onContainerCollapse(View v);

        void onContainerShow(View v);
    }

}
