package moe.lz233.mitranslator.module

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextClock
import moe.lz233.mitranslator.TEXT_CLOCK
import moe.lz233.mitranslator.util.LogUtil
import moe.lz233.mitranslator.util.ktx.hookAfterMethod

class HookSetting {
    @SuppressLint("ResourceType")
    fun init() {
        "com.cleargrass.app.babel.launcher.launch.main.MainFragment".hookAfterMethod("a",View::class.java){
            LogUtil.d("!!")
            val textClock = (it.args[0] as View).findViewById<TextClock>(TEXT_CLOCK)
            textClock.setOnClickListener {
                LogUtil.toast("?!")
            }
        }
    }
}