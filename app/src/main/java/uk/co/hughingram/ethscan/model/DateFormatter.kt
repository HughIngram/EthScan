package uk.co.hughingram.ethscan.model

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {

    @SuppressLint("SimpleDateFormat")
    fun formatDate(unixTimeStamp: Long): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val utc = TimeZone.getTimeZone("UTC")
        dateFormat.timeZone = utc
        val date = Date(unixTimeStamp * 1000)
        return dateFormat.format(date)
    }

}