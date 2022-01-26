package moe.lz233.mitranslator.module

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import moe.lz233.mitranslator.BuildConfig
import moe.lz233.mitranslator.Config
import moe.lz233.mitranslator.ViewFields
import moe.lz233.mitranslator.util.dp2px
import moe.lz233.mitranslator.util.ktx.hookAfterMethod
import moe.lz233.mitranslator.view.FuckEditText
import moe.lz233.mitranslator.view.FuckSwitch
import moe.lz233.mitranslator.view.FuckTextView
import kotlin.system.exitProcess

class HookSetting {
    @SuppressLint("ResourceType")
    fun init() {
        "com.cleargrass.app.babel.launcher.launch.main.MainFragment".hookAfterMethod("a", View::class.java) {
            ViewFields.rootView = it.args[0] as ScrollView
            ViewFields.textClock.setOnLongClickListener {
                showSettingsDialog()
                true
            }
        }
    }

    private fun showSettingsDialog() {
        val dialogBuilder = AlertDialog.Builder(Config.activity)
        dialogBuilder.setView(ScrollView(Config.context).apply {
            overScrollMode = 2
            addView(LinearLayout(Config.context).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(dp2px(20f), dp2px(10f), dp2px(20f), dp2px(5f))
                addView(FuckTextView.FastBuilder(mText = "Mi Translator +", mSize = FuckTextView.titleSize).build())
                addView(FuckTextView.FastBuilder(mText = "${BuildConfig.VERSION_NAME} ${BuildConfig.VERSION_CODE} ${BuildConfig.BUILD_TYPE}").build())
                addView(FuckTextView.Builder {
                    text = "重启应用"
                    setOnClickListener { exitProcess(0) }
                }.build())
                addView(FuckTextView.FastBuilder(mText = "界面", mSize = FuckTextView.title2Size).build())
                addView(FuckSwitch.FastBuilder(mText = "壁纸", mKey = "showWallpaper").build())
                addView(FuckSwitch.FastBuilder(mText = "翻译", mKey = "showTranslateButton", mDefaultState = true).build())
                addView(FuckSwitch.FastBuilder(mText = "翻译记录", mKey = "showTranslateHistoryButton", mDefaultState = true).build())
                addView(FuckSwitch.FastBuilder(mText = "汇率转换", mKey = "showExchangeCurrencyButton", mDefaultState = true).build())
                addView(FuckSwitch.FastBuilder(mText = "无卡上网", mKey = "showESimButton", mDefaultState = true).build())
                addView(FuckSwitch.FastBuilder(mText = "短信", mKey = "showMessageButton", mDefaultState = true).build())
                addView(FuckTextView.FastBuilder(mText = "应用", mSize = FuckTextView.title2Size).build())
                addView(FuckSwitch.FastBuilder(mText = "白名单模式", mKey = "whileListMode", mDefaultState = true).build())
                addView(FuckEditText.FastBuilder(mHint = "为 JSON 数组，如 [\"moe.lz233.mitranslator\"]，白名单模式下生效。", mKey = "appList").build())
                addView(FuckTextView.FastBuilder(mText = "信息", mSize = FuckTextView.title2Size).build())
                addView(FuckTextView.FastBuilder(mText = "Github", mUrl = "https://github.com/lz233/MiTranslatorPlus").build())
                addView(FuckTextView.FastBuilder(mText = "Document", mUrl = "https://mitranslator.project.ac.cn").build())
                addView(FuckTextView.FastBuilder(mText = "Release", mUrl = "https://modules.lsposed.org/module/moe.lz233.mitranslator").build())
            })
        })
        dialogBuilder.setPositiveButton("重启应用") { _: DialogInterface, _: Int -> exitProcess(0) }
        dialogBuilder.show().apply {
            getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.BLACK)
        }
    }
}