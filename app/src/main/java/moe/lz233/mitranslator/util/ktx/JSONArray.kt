package moe.lz233.mitranslator.util.ktx

import org.json.JSONArray

fun JSONArray.toStringList() = mutableListOf<String>().apply {
    for (i in 0 until this@toStringList.length()) add(this@toStringList[i] as String)
}.toString()