package moe.lz233.mitranslator

import android.app.Activity
import android.content.Context

const val PACKAGE_NAME = "com.cleargrass.app.babel.launcher"
const val SP_NAME = "translator_plus"
const val TAG = "MI TRANSLATOR +"

object Config {
    lateinit var context: Context
    lateinit var classLoader: ClassLoader
    lateinit var activity: Activity
}
