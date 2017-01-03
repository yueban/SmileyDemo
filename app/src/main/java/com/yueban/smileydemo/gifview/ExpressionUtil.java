package com.yueban.smileydemo.gifview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.style.ImageSpan;
import com.yueban.smileydemo.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pl.droidsonroids.gif.GifDrawable;

public class ExpressionUtil {
    /**
     * 根据字符串来解析成对应的包含gif的图片
     */
    public static Spannable getExpressionString(Context context, Spannable spannable, int lineHeight,
        Drawable.Callback callback) {
        try {
            dealExpression(context, spannable, 0, lineHeight, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spannable;
    }

    /**
     * 解析表情符
     *
     * @throws Exception
     */
    @SuppressWarnings("SuspiciousNameCombination")
    public static void dealExpression(Context context, Spannable spannable, int start, int height, Drawable.Callback callback)
        throws Exception {
        Matcher matcher = Pattern.compile("wx_thumb").matcher(spannable.toString());
        Drawable drawable;
        while (matcher.find()) {
            if (matcher.start() < start) {
                continue;
            }
            int resID = R.drawable.wx_thumb;
            try {
                drawable = new GifDrawable(context.getResources(), resID);
                drawable.setCallback(callback);
            } catch (Exception ignored) {
                drawable = context.getResources().getDrawable(resID);
            }
            if (drawable == null) {
                return;
            }

            drawable.setBounds(0, 0, height, height);

            ImageSpan span = new CenteredImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
            int mStart = matcher.start();
            int end = matcher.end();
            spannable.setSpan(span, mStart, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
}