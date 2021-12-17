package com.example.library;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoadingView extends LinearLayout implements ILoadingView {
    private ImageView mImage;
    private TextView mDes;
    private boolean mShow = true;
    private String mCurrMsg;
    private int mCurrImage = R.drawable.icon_loading;
    private String mErrorMsg;
    private String mEmptyMsg;
    private String mLoadingMsg;
    private ObjectAnimator mRotation;

    public LoadingView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        Context context = getContext();
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.item_loading_view, this, true);
        mImage = findViewById(R.id.image);
        mDes = findViewById(R.id.text);
        int bgColor = getResources().getColor(android.R.color.white);

        mErrorMsg = getResources().getString(R.string.load_failed);
        mEmptyMsg = getResources().getString(R.string.load_empty);
        mLoadingMsg = getResources().getString(R.string.loading);

        setBackgroundColor(bgColor);
    }

    public void setVisibleByStatus(int status, String msg) {
        switch (status) {
            case STATUS_LOAD_SUCCESS:
                showLoadSuccess();
                break;
            case STATUS_LOADING:
                showLoading(msg);
                break;
            case STATUS_LOAD_FAILED:
                showLoadFailed(msg);
                break;
            case STATUS_EMPTY_DATA:
                showLoadEmpty(msg);
                break;
            default:
                break;
        }

        setVisibility(mShow ? View.VISIBLE : View.GONE);
    }


    @Override
    public void showLoading(String msg) {
        mCurrMsg = TextUtils.isEmpty(msg) ? mLoadingMsg : msg;
        mCurrImage = R.drawable.widgets_ic_loading;
        mShow = true;
        mImage.setImageResource(mCurrImage);
        startAnim(mImage);
        mDes.setText(mCurrMsg);
    }

    @Override
    public void showLoadSuccess() {
        mShow = false;
        clearAnim();
    }

    public void showLoadFailed(String msg) {
        clearAnim();
        mCurrMsg = TextUtils.isEmpty(msg) ? mErrorMsg : msg;
        mCurrImage = R.drawable.widgets_ic_failed;
        mShow = true;
        mImage.setImageResource(mCurrImage);
        mDes.setText(mCurrMsg);
    }


    @Override
    public void showLoadEmpty(String msg) {
        clearAnim();//clear animation to prevent infinite loop.
        mCurrMsg = TextUtils.isEmpty(msg) ? mEmptyMsg : msg;
        mCurrImage = R.drawable.widgets_ic_empty;
        mShow = true;
        mImage.setImageResource(mCurrImage);
        mDes.setText(mCurrMsg);
    }

    /**
     * 执行旋转属性动画
     *
     */
    private void startAnim(ImageView mImage) {
        clearAnim();
        mRotation = ObjectAnimator.ofFloat(mImage, "rotation", 0f, 360f);
        mRotation.setDuration(1500);
        mRotation.setInterpolator(new LinearInterpolator());
        mRotation.setTarget(mImage);
        mRotation.setRepeatMode(ValueAnimator.RESTART);
        mRotation.setRepeatCount(ValueAnimator.INFINITE);
        mRotation.start();
    }

    /**
     * 停止动画
     */
    public void clearAnim() {
        if (mRotation != null) {
            mRotation.removeAllListeners();
            mRotation.end();
            mRotation.cancel();
            mRotation = null;
        }
    }

    public void setColor(@ColorInt int resColor) {
        mDes.setTextColor(resColor);
    }

    public void setTextSize(float textSize) {
        mDes.setTextSize(textSize);
    }
}
