package com.yueban.smileydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.View;
import com.yueban.smileydemo.gifview.MyEditTextEx;
import com.yueban.smileydemo.gifview.MyTextViewEx;

public class MainActivity extends AppCompatActivity {

    private MyEditTextEx mEditText;
    private MyTextViewEx mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (MyEditTextEx) findViewById(R.id.edit_text);
        mTextView = (MyTextViewEx) findViewById(R.id.text_view);
    }

    public void addEmoji(View view) {
        mEditText.insertGif(new SpannableString("wx_thumb"));

        SpannableStringBuilder spannableString = new SpannableStringBuilder(mTextView.getText());
        spannableString.append("wx_thumb");
        mTextView.insertGif(spannableString);
    }
}
