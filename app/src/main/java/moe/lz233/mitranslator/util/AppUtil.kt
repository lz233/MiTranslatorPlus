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
import android.content.pm.PackageManager
import android.content.res.Configuration
import moe.lz233.mitranslator.Config
import moe.lz233.mitranslator.meta.App


fun dp2px(dpValue: Float, context: Context = Config.context): Int = (dpValue * context.resources.displayMetrics.density + 0.5f).toInt()

fun sp2px(spValue: Float, context: Context = Config.context): Int = (spValue * context.resources.displayMetrics.scaledDensity + 0.5f).toInt()

fun isNightMode(context: Context): Boolean = (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

fun getAppList(packageManager: PackageManager = Config.context.packageManager) = mutableListOf<App>().apply {
    packageManager.queryIntentActivities(Intent(Intent.ACTION_MAIN, null).apply { addCategory(Intent.CATEGORY_LAUNCHER) }, 0).forEach {
        add(App(it.loadLabel(packageManager).toString(), Intent(Intent.ACTION_MAIN).apply {
            setClassName(it.activityInfo.packageName,it.activityInfo.name)
        },it.loadIcon(packageManager)))
    }
}.toList()