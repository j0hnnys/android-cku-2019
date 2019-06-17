package com.cku.twitter.ui

import java.sql.Timestamp

/**
 * Converts a long to a date
 */
fun Long.convertToTimestamp(): String {
    val timestamp = Timestamp(this)
    return timestamp.toString()
}
