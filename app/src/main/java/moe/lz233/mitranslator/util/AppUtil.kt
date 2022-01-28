/*
 * Fuck Coolapk - Best present for 316 and 423
 * Copyright (C) 2020-2021
 * https://github.com/ejiaogl/FuckCoolapk
 *
 * This software is non-free but opensource software: you can redistribute it
 * and/or modify it under the terms of the GNUGeneral Public License as
 * published by the Free Software Foundation; either version 3 of the License,
 * or any later version and our eula as published by ejiaogl.
 *
 * This software is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License and
 * eula along with this software.  If not, see
 * <https://www.gnu.org/licenses/>
 * <https://github.com/ejiaogl/FuckCoolapk/blob/master/LICENSE>.
 */

package moe.lz233.mitranslator.util

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import moe.lz233.mitranslator.Config
import moe.lz233.mitranslator.util.ktx.*


fun dp2px(dpValue: Float, context: Context = Config.context): Int = (dpValue * context.resources.displayMetrics.density + 0.5f).toInt()

fun sp2px(spValue: Float, context: Context = Config.context): Int = (spValue * context.resources.displayMetrics.scaledDensity + 0.5f).toInt()

fun isNightMode(context: Context): Boolean = (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

fun getAppList() = mutableListOf<ApplicationInfo>().apply {
    Config.context.packageManager.queryIntentActivities(Intent(Intent.ACTION_MAIN, null).apply { addCategory(Intent.CATEGORY_LAUNCHER) }, 0).forEach {
        add(Config.context.packageManager.getApplicationInfo(it.activityInfo.applicationInfo.packageName, 0))
    }
}.toList()

fun getMatureTileView(applicationInfo: ApplicationInfo) = "com.cleargrass.app.babel.launcher.TileView".findClass().new(Config.context).apply { //icon
    setObjectField("a",PackageItemInfoCompat.loadUnbadgedIcon(applicationInfo,Config.context.packageManager))
    val icon = getObjectField("a") as Drawable

    icon.setBounds(icon.intrinsicWidth/2, icon.intrinsicHeight/2, icon.intrinsicWidth/2+50,icon.intrinsicHeight/2+50)
    setObjectField("b", applicationInfo.loadLabel(Config.context.packageManager)) //title
    setObjectField("c", "") //sub title
    setIntField("d", 1) //type
    setIntField("e", -9136724) //color 1
    setIntField("f", -12293493) //color 2
    setObjectField("g", Paint().apply {
        color = -1
        isAntiAlias = true
    }) //paint

    setObjectField("h", Paint().apply {
        color = -2130772226
        isAntiAlias = true
    }) //paint
    setObjectField("i", Paint()) //background paint
    //click
    //setBooleanField("k",false)
    //??
    setFloatField("l", 1.0f) //callMethod("onConfigurationChanged",callMethod("getResources")!!.callMethod("getConfiguration"))
} as View