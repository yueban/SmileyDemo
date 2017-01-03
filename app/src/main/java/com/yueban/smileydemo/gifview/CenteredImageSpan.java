package com.yueban.smileydemo.gifview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.style.ImageSpan;
import java.lang.ref.WeakReference;

/**
 * 参考自:
 * http://stackoverflow.com/questions/25628258/align-text-around-imagespan-center-vertical
 *
 * 修改了 transY 的值计算公式
 */
public class CenteredImageSpan extends ImageSpan {
    public static final String TAG = "CenteredImageSpan";
    private WeakReference<Drawable> mDrawableRef;

    public CenteredImageSpan(Context context, final int drawableRes) {
        super(context, drawableRes);
    }

    public CenteredImageSpan(Drawable d) {
        super(d);
    }

    public CenteredImageSpan(Drawable d, int verticalAlignment) {
        super(d, verticalAlignment);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        Drawable d = getCachedDrawable();
        Rect rect = d.getBounds();

        if (fm != null) {
            Paint.FontMetricsInt pfm = paint.getFontMetricsInt();
            // keep it the same as paint's fm
            fm.ascent = pfm.ascent;
            fm.descent = pfm.descent;
            fm.top = pfm.top;
            fm.bottom = pfm.bottom;
        }

        return rect.right;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom,
        @NonNull Paint paint) {
        Drawable b = getCachedDrawable();
        canvas.save();

        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int fontAscent = fontMetrics.ascent;
        int fontDescent = fontMetrics.descent;
        int fontBottom = fontMetrics.bottom;
        int fontTop = fontMetrics.top;

        float transY = (fontDescent - fontAscent + fontBottom - b.getBounds().bottom) / 2.0f + (y + fontTop);
        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }

    // Redefined locally because it is a private member from DynamicDrawableSpan
    private Drawable getCachedDrawable() {
        WeakReference<Drawable> wr = mDrawableRef;
        Drawable d = null;

        if (wr != null) {
            d = wr.get();
        }

        if (d == null) {
            d = getDrawable();
            mDrawableRef = new WeakReference<>(d);
        }

        return d;
    }
}