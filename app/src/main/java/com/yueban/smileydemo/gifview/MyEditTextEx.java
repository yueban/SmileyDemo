package com.yueban.smileydemo.gifview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.Spannable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import com.yueban.smileydemo.R;

public class MyEditTextEx extends EditText {
    private final int mEmojiconSize;
    private final boolean isAnimationEnable;
    private final boolean isShowEmojicon;
    private DrawableCallback mDrawableCallback;

    public MyEditTextEx(Context context) {
        this(context, null);
    }

    public MyEditTextEx(Context context, AttributeSet attr) {
        super(context, attr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        TypedArray a = getContext().obtainStyledAttributes(attr, R.styleable.MyEditTextEx);
        mEmojiconSize = a.getDimensionPixelSize(R.styleable.MyEditTextEx_emojiconSize, getLineHeight());
        isAnimationEnable = a.getBoolean(R.styleable.MyEditTextEx_isAnimationEnable, false);
        isShowEmojicon = a.getBoolean(R.styleable.MyEditTextEx_isShowEmojicon, true);
        a.recycle();
    }

    public void insertGif(Spannable spannable) {
        //在光标处插入动态表情
        int index = getSelectionStart();
        Editable editable = getText();
        if (isShowEmojicon) {
            if (mDrawableCallback == null) {
                mDrawableCallback = new DrawableCallback(this);
            }
            Spannable spannableString = ExpressionUtil.getExpressionString(getContext(), spannable, mEmojiconSize,
                isAnimationEnable ? mDrawableCallback : null);
            editable.insert(index, spannableString);
        } else {
            editable.insert(index, spannable);
        }
        setSelection(index + spannable.length());
    }
}