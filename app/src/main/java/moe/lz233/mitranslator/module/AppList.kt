package moe.lz233.mitranslator.module

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import moe.lz233.mitranslator.Config
import moe.lz233.mitranslator.ModuleSP
import moe.lz233.mitranslator.R
import moe.lz233.mitranslator.ViewFields
import moe.lz233.mitranslator.util.getAppList
import moe.lz233.mitranslator.util.ktx.hookAfterMethod
import moe.lz233.mitranslator.util.ktx.toStringList
import org.json.JSONArray

class AppList {
    @SuppressLint("ResourceType")
    fun init() {
        "com.cleargrass.app.babel.launcher.launch.main.MainFragment".hookAfterMethod("onViewCreated", View::class.java, Bundle::class.java) {
            val linearLayout = ViewFields.rootView.getChildAt(0) as LinearLayout
            linearLayout.addView(LayoutInflater.from(Config.context).inflate(R.layout.view_title, null))
            //LogUtil.d(linearLayout.childCount)
            val whileListMode = ModuleSP.sp.getBoolean("whileListMode", false)
            val whileAppList = JSONArray(ModuleSP.sp.getString("whileAppList", "[]")).toStringList()
            val moreDrawable = Config.context.resources.getDrawable(2131165304, Config.context.theme)
            var hasItem = false
            getAppList().forEach {
                if ((!whileListMode) or (it.packageName in whileAppList)) {
                    hasItem = true
                    linearLayout.addView(LayoutInflater.from(Config.context).inflate(R.layout.view_app, null).apply {
                        this.setOnClickListener { _ -> Config.activity.startActivity(it.launchIntent) }
                        this.findViewById<ImageView>(R.id.icon).setImageDrawable(it.icon)
                        this.findViewById<TextView>(R.id.title).text = it.name
                        this.findViewById<ImageView>(R.id.more).setImageDrawable(moreDrawable)
                    })
                }
            }
            if (!hasItem) linearLayout.getChildAt(13).visibility = View.GONE
            /*"com.cleargrass.app.babel.launcher.TileView".hookAfterMethod("onDraw", Canvas::class.java) {
                LogUtil.d("${it.thisObject.getObjectField("b")}\n${it.thisObject.getObjectField("a")!!.callMethod("getIntrinsicWidth")}\n${it.thisObject.getObjectField("a")!!.callMethod("getIntrinsicHeight")}\n${it.thisObject.callMethod("getMeasuredHeight")}")
            }*/
        }
    }
}