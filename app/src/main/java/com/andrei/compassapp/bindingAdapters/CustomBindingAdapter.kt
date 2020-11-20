package com.andrei.compassapp.bindingAdapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("dateFromUnix")
fun getDateFromUnix(textView: TextView, unixTime: Long) {
    if (unixTime > 0) {
        val compareDate = SimpleDateFormat("yyyy-MM-dd", Locale.UK)

        val nowDate = Date(Calendar.getInstance().timeInMillis)
        val messageDate = Date(unixTime * 1000)

        if (compareDate.format(nowDate) == compareDate.format(messageDate)) {
            val dateFormat = SimpleDateFormat("HH:mm", Locale.UK);
            textView.text = dateFormat.format(messageDate)
        } else {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.UK);
            textView.text = dateFormat.format(messageDate)
        }
    }
}