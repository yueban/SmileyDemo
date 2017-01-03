package com.yueban.smileydemo.gifview;

import android.graphics.drawable.Drawable;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/**
 * @author yueban
 * @date 2016/12/26
 * @email fbzhh007@gmail.com
 */
class DrawableCallback implements Drawable.Callback {
    private WeakReference<TextView> mViewWeakReference;

    DrawableCallback(TextView textView) {
        mViewWeakReference = new WeakReference<>(textView);
    }

    @Override
    public void invalidateDrawable(Drawable who) {
        if (mViewWeakReference.get() != null) {
            mViewWeakReference.get().invalidate();
        }
    }

    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        if (mViewWeakReference.get() != null) {
            mViewWeakReference.get().postDelayed(what, when);
        }
    }

    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {
        if (mViewWeakReference.get() != null) {
            mViewWeakReference.get().removeCallbacks(what);
        }
    }
}
