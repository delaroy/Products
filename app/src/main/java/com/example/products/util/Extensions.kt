package com.example.products.util

import android.text.TextUtils

fun camelCase(stringToConvert: String): String {
    if (TextUtils.isEmpty(stringToConvert)) {
        return "";
    }
    return Character.toUpperCase(stringToConvert[0]) +
            stringToConvert.substring(1).lowercase()
}