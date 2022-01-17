package moe.lz233.mitranslator

import android.app.Activity
import android.app.Application
import android.content.Context
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage
import moe.lz233.mitranslator.util.LogUtil
import moe.lz233.mitranslator.util.ktx.hookAfterAllMethods
import moe.lz233.mitranslator.util.ktx.hookAfterMethod

class InitHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName == PACKAGE_NAME) {
            try {
                Application::class.java.hookAfterMethod("attach", Context::class.java) {
                    Config.context = it.args[0] as Context
                    Config.classLoader = Config.context.classLoader
                }
                "android.app.Instrumentation".hookAfterAllMethods("newActivity") {
                    Config.activity = it.result as Activity
                    LogUtil.d("Current activity: ${Config.activity.javaClass}")
                }
                init()
            } catch (e: Throwable) {
                LogUtil.e(e)
            }
        }
    }

    fun init() {

    }
}