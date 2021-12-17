package com.example.library;

import static com.example.library.ILoadingView.*;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class LoadingLayout extends FrameLayout {
    private LoadingView loadingView;

    public LoadingLayout( Context context) {
        this(context, null);
    }

    public LoadingLayout( Context context,  AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingLayout( Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initConfig();
    }

    public static final int MAX_CHILD_LIMIT = 2 - 1;

    @Override
    public void addView(View child) {
        if (getChildCount() > MAX_CHILD_LIMIT) {
            throw new IllegalStateException("LoadingLayout can host only one direct child");
        }

        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        if (getChildCount() > MAX_CHILD_LIMIT) {
            throw new IllegalStateException("LoadingLayout can host only one direct child");
        }

        super.addView(child, index);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (getChildCount() > MAX_CHILD_LIMIT) {
            throw new IllegalStateException("LoadingLayout can host only one direct child");
        }

        super.addView(child, params);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (getChildCount() > MAX_CHILD_LIMIT) {
            throw new IllegalStateException("LoadingLayout can host only one direct child");
        }

        super.addView(child, index, params);
    }

    private void initConfig() {
        if (loadingView == null)
            loadingView = new LoadingView(getContext());
    }

    public void showLoading() {
        showLoading("");
    }

    public void showLoading(String msg) {
        loadingView.setVisibleByStatus(STATUS_LOADING, msg);
        int index = indexOfChild(loadingView);
        if (index == -1) {
            addView(loadingView);
        }
        loadingView.setOnClickListener(null);
    }

    public void showLoadSuccess() {
        showLoadSuccess("");
    }

    public void showLoadSuccess(String msg) {
        loadingView.setVisibleByStatus(STATUS_LOAD_SUCCESS, msg);
        loadingView.setOnClickListener(null);
    }

    public void showLoadFailed() {
        showLoadFailed("", null);
    }

    public void showLoadFailed(OnClickListener onClickListener) {
        showLoadFailed("", onClickListener);
    }

    public void showLoadFailed(String msg, OnClickListener onClickListener) {
        loadingView.setVisibleByStatus(STATUS_LOAD_FAILED, msg);
        if (onClickListener == null) {
            return;
        }
        loadingView.setOnClickListener(onClickListener);
    }

    public void showLoadEmpty() {
        showLoadEmpty("");
    }

    public void showLoadEmpty(String msg) {
        loadingView.setVisibleByStatus(STATUS_EMPTY_DATA, msg);
        loadingView.setOnClickListener(null);
    }

    /**
     * 设置 文本信息，文本color 文本size,
     * 如果想使用部分参数，传默认值即可（）
     *
     * @param loadingText null or ""
     * @param resColor    -1
     * @param textSize    -1f
     * @return
     */
    public LoadingLayout setLoadingText(String loadingText, int resColor, float textSize) {
        checkNotNUll();
        loadingView.setColor(resColor);
        loadingView.setTextSize(textSize);
        return this;
    }

    private void checkNotNUll() {
        if (loadingView == null) {
            throw new NullPointerException( "null");
        }
    }
}
