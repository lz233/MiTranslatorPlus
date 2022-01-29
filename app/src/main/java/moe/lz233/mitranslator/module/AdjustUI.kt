package moe.lz233.mitranslator.module

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import moe.lz233.mitranslator.Config
import moe.lz233.mitranslator.ModuleSP
import moe.lz233.mitranslator.ViewFields
import moe.lz233.mitranslator.util.ktx.getObjectField
import moe.lz233.mitranslator.util.ktx.hookAfterMethod
import moe.lz233.mitranslator.util.ktx.hookBeforeMethod
import moe.lz233.mitranslator.util.ktx.setObjectField

class AdjustUI {
    @SuppressLint("MissingPermission")
    fun init() {
        "com.cleargrass.app.babel.launcher.launch.main.MainFragment".hookAfterMethod("onViewCreated", View::class.java, Bundle::class.java) {
            // hide scrollbar
            ViewFields.rootView.isVerticalScrollBarEnabled = false
            if (ModuleSP.sp.getBoolean("showWallpaper", false)) {
                // hide status bar
                Config.activity.window.attributes = Config.activity.window.attributes.apply { flags += WindowManager.LayoutParams.FLAG_FULLSCREEN }
                // reset text clock padding
                ViewFields.textClock.setPadding(ViewFields.textClock.paddingLeft, ViewFields.textClock.paddingTop + 150, ViewFields.textClock.paddingRight, ViewFields.textClock.paddingBottom)
                ViewFields.rootView.background = WallpaperManager.getInstance(Config.context).drawable
            }
            if (!ModuleSP.sp.getBoolean("showTranslateButton", true)) {
                ViewFields.chatTranslateButton.visibility = View.GONE
                ViewFields.faceToFaceTranslateButton.visibility = View.GONE
                ViewFields.ocrTranslateButton.visibility = View.GONE
            }
            if (!ModuleSP.sp.getBoolean("showTranslateHistoryButton", true)) {
                ViewFields.translateHistoryButton.visibility = View.GONE
            }
            if (!ModuleSP.sp.getBoolean("showExchangeCurrencyButton", true)) {
                ViewFields.exchangeCurrencyButton.visibility = View.GONE
            }
            if (!ModuleSP.sp.getBoolean("showESimButton", true)) {
                ViewFields.eSimButton.visibility = View.GONE
            }
            if (!ModuleSP.sp.getBoolean("showMessageButton", true)) {
                ViewFields.messageButton.visibility = View.GONE
            }
        }
        "com.cleargrass.app.babel.launcher.TileView".hookBeforeMethod("onDraw", Canvas::class.java) {
            // set background alpha to 0
            if (ModuleSP.sp.getBoolean("showWallpaper", false)) (it.thisObject.getObjectField("i") as Paint).alpha = 0
            // remove mi font
            it.thisObject.setObjectField("g", Paint().apply { color = -1;isAntiAlias = true })
            it.thisObject.setObjectField("h", Paint().apply { color = -2130772226;isAntiAlias = true })
        }
    }
}