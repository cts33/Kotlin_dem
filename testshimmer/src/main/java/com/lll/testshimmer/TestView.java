package com.lll.testshimmer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @description
 * @mail chentaishan@aliyun.com
 * @date 2022/5/16
 */
public class TestView extends View {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int edge_color = Color.parseColor("#00FFFFFF");
    private int center_color = Color.parseColor("#33FFFFFF");
    //    private int edge_color = Color.parseColor("#36EFD707");
//    private int center_color = Color.parseColor("#E8EFD707");
    int[] mColors = {edge_color, center_color, edge_color};
    private Paint mPaint = new Paint();
    private int mViewWidth = 0;
    private int mTranslate = 100;
    private int shimer_width = 100;

    private static final String TAG = "TestView";


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mViewWidth = getMeasuredWidth();
        if (mViewWidth > 0) {
            mTranslate += mViewWidth / 10;

            Log.d(TAG, "onSizeChanged() mViewWidth= [" + mViewWidth + "], mTranslate = [" + mTranslate + "]");
            if (mTranslate > mViewWidth + shimer_width) {
                mTranslate = 0;
            }
        }
        Log.d(TAG, "onDraw: mTranslate=" + mTranslate);
        LinearGradient linearGradient =
                new LinearGradient(mTranslate, 0, mTranslate + shimer_width, 0, mColors, new float[]{0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);

        canvas.drawRect(mTranslate, 0, mTranslate + shimer_width, getHeight(), mPaint);

        postInvalidateDelayed(50);
    }


}
