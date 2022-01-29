package moe.lz233.mitranslator

import android.app.AndroidAppHelper
import android.app.Fragment
import android.content.res.XModuleResources
import android.os.Bundle
import de.robv.android.xposed.IXposedHookInitPackageResources
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.callbacks.XC_InitPackageResources
import de.robv.android.xposed.callbacks.XC_LoadPackage
import moe.lz233.mitranslator.module.AdjustUI
import moe.lz233.mitranslator.module.AppList
import moe.lz233.mitranslator.module.HookSetting
import moe.lz233.mitranslator.util.LogUtil
import moe.lz233.mitranslator.util.ktx.callMethod
import moe.lz233.mitranslator.util.ktx.hookAfterMethod

class InitHook : IXposedHookLoadPackage, IXposedHookZygoteInit, IXposedHookInitPackageResources {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName == PACKAGE_NAME) {
            try {
                Config.classLoader = lpparam.classLoader
                "com.cleargrass.app.babel.launcher.launch.main.MainFragment".hookAfterMethod("onCreate", Bundle::class.java) {
                    Config.context = AndroidAppHelper.currentApplication()
                    Config.activity = (it.thisObject as Fragment).activity
                    // inject res
                    Config.context.resources.assets.callMethod("addAssetPath", Config.modulePath)
                    init()
                }
            } catch (e: Throwable) {
                LogUtil.e(e)
            }
        }
    }

    private fun init() {
        //设置
        HookSetting().init()
        //界面
        AdjustUI().init()
        //应用
        AppList().init()
    }

    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam) {
        Config.modulePath = startupParam.modulePath
        Config.moduleRes = XModuleResources.createInstance(Config.modulePath, null)
    }

    override fun handleInitPackageResources(resparam: XC_InitPackageResources.InitPackageResourcesParam?) {

    }
}