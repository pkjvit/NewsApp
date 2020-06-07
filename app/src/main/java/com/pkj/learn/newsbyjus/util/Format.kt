package com.pkj.learn.newsbyjus.util

/**
 * @author Pankaj Jangid
 */

class Format {
    companion object {
        fun dateFormatFromDateTime(dateTime: String?): String {
            dateTime?.let {
                return dateTime.substringBefore('T')
            }
            return ""
        }
    }

}