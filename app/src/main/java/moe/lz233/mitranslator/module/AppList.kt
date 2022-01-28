package moe.lz233.mitranslator.module

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Xml
import android.view.View
import android.widget.LinearLayout
import moe.lz233.mitranslator.Config
import moe.lz233.mitranslator.ViewFields
import moe.lz233.mitranslator.util.LogUtil
import moe.lz233.mitranslator.util.getAppList
import moe.lz233.mitranslator.util.getMatureTileView
import moe.lz233.mitranslator.util.ktx.*
import moe.lz233.mitranslator.view.FuckSwitch
import moe.lz233.mitranslator.view.FuckTextView
import moe.lz233.mitranslator.view.TileView
import org.xmlpull.v1.XmlPullParserFactory

class AppList {
    fun init() {
        "com.cleargrass.app.babel.launcher.launch.main.MainFragment".hookAfterMethod("onViewCreated", View::class.java, Bundle::class.java) {
            val linearLayout = ViewFields.rootView.getChildAt(0) as LinearLayout
            getAppList().forEach {
                linearLayout.addView(getMatureTileView(it))
            }
            //linearLayout.addView(TileView.FastBuilder(icon=ColorDrawable(-12293493),title="233",packageName="233").build())
        }
        "com.cleargrass.app.babel.launcher.TileView".hookAfterMethod("onDraw",Canvas::class.java){
            LogUtil.d("${it.thisObject.getObjectField("a")}\n${it.thisObject.getObjectField("b")}\n${it.thisObject.getObjectField("c")}\n${it.thisObject.getObjectField("d")}\n${it.thisObject.getObjectField("e")}\n${it.thisObject.getObjectField("f")}\n${it.thisObject.getObjectField("l")}")
        }
    }
}