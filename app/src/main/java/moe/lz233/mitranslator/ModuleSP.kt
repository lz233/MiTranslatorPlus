package moe.lz233.mitranslator

import android.content.Context
import android.content.SharedPreferences

object ModuleSP {
    val sp: SharedPreferences by lazy { Config.context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE) }
    private val editor: SharedPreferences.Editor = sp.edit()
    fun set(key: String, any: Any) {
        when (any) {
            is Int -> editor.putInt(key, any)
            is Float -> editor.putFloat(key, any)
            is String -> editor.putString(key, any)
            is Boolean -> editor.putBoolean(key, any)
            is Long -> editor.putLong(key, any)
        }
        editor.apply()
    }

    fun remove(key: String)= editor.run {
        remove(key)
        apply()
    }
}