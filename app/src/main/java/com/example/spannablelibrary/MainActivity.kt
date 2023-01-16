package com.example.spannablelibrary

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var txt: MaterialTextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt = findViewById(R.id.txt);

        val month = "February"

        var str = "your summary:"
        str += month
        str += "\n";
        str += "gross salary: ";
        str += " 50,000";
        str += "₪";
        str += "\n";
        str += "pension found: ";
        str += " -3,000";
        str += "₪";


        val spannable = SpannableString(str)
        spannable.setSpan(RelativeSizeSpan(1.2f), "your summary:".length, "your summary:".length + month.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(StyleSpan(Typeface.BOLD), month.length + 1, month.length + 1 + "gross salary:".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(UnderlineSpan(), str.indexOf("pension found:"), str.indexOf("pension found:") + "pension found:".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)


        var mst = MST()
        mst.add(month, MST.TYPE.SIZE_1_2)
        mst.add("\n")
        mst.add("gross salary: ", MST.TYPE.BOLD, MST.TYPE.UNDERLINE)
        mst.add("50,000", MST.TYPE.ITALIC)
        mst.addImage(resources, R.drawable.ic_money, 48)
        //mst.add("₪", MST.TYPE.ITALIC)
        txt.text = mst.spannable
    }
}