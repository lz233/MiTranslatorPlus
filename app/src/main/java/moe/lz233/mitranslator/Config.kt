package moe.lz233.mitranslator

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.content.res.XModuleResources
import android.view.View

const val PACKAGE_NAME = "com.cleargrass.app.babel.launcher"
const val SP_NAME = "translator_plus"
const val TAG = "MITRANSLATOR+"

object Config {
    lateinit var modulePath:String
    lateinit var moduleRes:XModuleResources
    lateinit var context: Context
    lateinit var classLoader: ClassLoader
    lateinit var activity: Activity
}
