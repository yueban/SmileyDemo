package com.yueban.smileydemo.gifview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spannable;
import android.util.AttributeSet;
import android.widget.TextView;
import com.yueban.smileydemo.R;

public class MyTextViewEx extends TextView {
    private final int mEmojiconSize;
    private final boolean isAnimationEnable;
    private final boolean isShowEmojicon;
    private DrawableCallback mDrawableCallback;

    public MyTextViewEx(Context context) {
        this(context, null);
    }

    public MyTextViewEx(Context context, AttributeSet attr) {
        super(context, attr);

        TypedArray a = getContext().obtainStyledAttributes(attr, R.styleable.MyEditTextEx);
        mEmojiconSize = a.getDimensionPixelSize(R.styleable.MyEditTextEx_emojiconSize, (int) (getTextSize() * 1.25f));
        isAnimationEnable = a.getBoolean(R.styleable.MyEditTextEx_isAnimationEnable, true);
        isShowEmojicon = a.getBoolean(R.styleable.MyEditTextEx_isShowEmojicon, true);
        a.recycle();

        setHighlightColor(0x000000);
    }

    public void insertGif(Spannable spannable) {
        if (spannable == null || "".equals(spannable.toString())) {
            setText("");
            return;
        }
        if (isShowEmojicon) {
            if (mDrawableCallback == null) {
                mDrawableCallback = new DrawableCallback(this);
            }
            Spannable spannableString = ExpressionUtil.getExpressionString(getContext(), spannable, mEmojiconSize,
                isAnimationEnable ? mDrawableCallback : null);
            setText(spannableString);
        } else {
            setText(spannable);
        }
    }
}