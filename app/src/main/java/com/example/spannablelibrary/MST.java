package com.example.spannablelibrary;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MST {

    public enum TYPE {
        SIZE_1_2,
        UNDERLINE,
        BOLD,
        ITALIC,
        IMAGE,
    }

    private class Span {

        private Object data;
        private TYPE[] attrs;
        private int begin;
        private int end;

        public Span() {}
    }

    private ArrayList<Span> spans = new ArrayList<>();
    private StringBuilder sb = new StringBuilder();

    public MST() {}

    public void add(String str, TYPE... attrs) {
        Span span = new Span();
        span.attrs = attrs;
        span.begin = sb.length();
        span.end = sb.length() + str.length();
        spans.add(span);

        sb.append(str);
    }

    public void addImage(Resources resources, int drawableRes, int size) {
        String modifiedText = "%icon%";
        Drawable drawable = ResourcesCompat.getDrawable(resources, drawableRes, null);
        if (drawable != null) {
            drawable.setBounds(0, 0, size, size);
            Span span = new Span();
            span.attrs = new TYPE[]{TYPE.IMAGE};
            span.begin = sb.length();
            span.end = sb.length() + 6;
            span.data = drawable;
            spans.add(span);
            sb.append(modifiedText);
        }
    }

    public Spannable getSpannable() {
        Spannable spannable = new SpannableString(sb.toString());

        for (Span span : spans) {
            for (TYPE attr : span.attrs) {
                switch (attr) {
                    case SIZE_1_2:
                        spannable.setSpan(new RelativeSizeSpan(1.6f), span.begin, span.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        break;
                    case UNDERLINE:
                        spannable.setSpan(new UnderlineSpan(), span.begin, span.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        break;
                    case BOLD:
                        spannable.setSpan(new StyleSpan(Typeface.BOLD), span.begin, span.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        break;
                    case ITALIC:
                        spannable.setSpan(new StyleSpan(Typeface.ITALIC), span.begin, span.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        break;
                    case IMAGE:
                        Drawable drawable = (Drawable) span.data;
                        ImageSpan image = new ImageSpan(drawable, DynamicDrawableSpan.ALIGN_BASELINE);
                        spannable.setSpan(image, span.begin, span.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        break;
                }
            }

        }

        return spannable;
    }


}

















