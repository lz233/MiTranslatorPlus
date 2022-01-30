package moe.lz233.mitranslator.module

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import moe.lz233.mitranslator.Config
import moe.lz233.mitranslator.R
import moe.lz233.mitranslator.ViewFields
import moe.lz233.mitranslator.util.LogUtil
import moe.lz233.mitranslator.util.getAppList
import moe.lz233.mitranslator.util.ktx.callMethod
import moe.lz233.mitranslator.util.ktx.getObjectField
import moe.lz233.mitranslator.util.ktx.hookAfterMethod

class AppList {
    @SuppressLint("ResourceType")
    fun init() {
        "com.cleargrass.app.babel.launcher.launch.main.MainFragment".hookAfterMethod("onViewCreated", View::class.java, Bundle::class.java) {
            val linearLayout = ViewFields.rootView.getChildAt(0) as LinearLayout
            val packageManager = Config.context.packageManager
            val moreDrawable = Config.context.resources.getDrawable(2131165304, Config.context.theme)
            getAppList().forEach {
                linearLayout.addView(LayoutInflater.from(Config.context).inflate(R.layout.view_app, null).apply {
                    Glide.with(Config.context).load(it.icon).into(this.findViewById(R.id.icon))
                    //this.findViewById<ImageView>(R.id.icon).setImageDrawable(it.loadIcon(packageManager))
                    this.findViewById<TextView>(R.id.title).text = it.name
                    this.findViewById<ImageView>(R.id.more).setImageDrawable(moreDrawable)
                })
                //linearLayout.addView(getMatureTileView(it))
            }//linearLayout.addView(TileView.FastBuilder(icon=ColorDrawable(-12293493),title="233",packageName="233").build())
        }
        "com.cleargrass.app.babel.launcher.TileView".hookAfterMethod("onDraw", Canvas::class.java) {
            LogUtil.d("${it.thisObject.getObjectField("b")}\n${it.thisObject.getObjectField("a")!!.callMethod("getIntrinsicWidth")}\n${it.thisObject.getObjectField("a")!!.callMethod("getIntrinsicHeight")}\n${it.thisObject.callMethod("getMeasuredHeight")}")
        }
    }
}