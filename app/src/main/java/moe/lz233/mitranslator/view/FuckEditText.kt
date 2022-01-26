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

package moe.lz233.mitranslator.view

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import moe.lz233.mitranslator.Config
import moe.lz233.mitranslator.ModuleSP
import moe.lz233.mitranslator.util.dp2px
import moe.lz233.mitranslator.util.isNightMode

class FuckEditText(context: Context) : EditText(context) {
    var color = if (isNightMode(getContext())) "#ffffff" else "#000000"
        set(value) = setTextColor(Color.parseColor(value))
    var sharedPreferences = ModuleSP.sp
    private val editor by lazy { sharedPreferences.edit() }
    var key = ""
        set(value) {
            if (sharedPreferences.getString(value, "") != "") setText(sharedPreferences.getString(value, ""))
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    if (s.toString() == "") editor.remove(value) else editor.putString(value, s.toString())
                    editor.apply()
                }
            })
        }

    init {
        setPadding(dp2px(10f), dp2px(5f), dp2px(10f), dp2px(5f))
        setTextColor(Color.parseColor(color))
        textSize = FuckTextView.textSize
    }

    class Builder(private val mContext: Context = Config.context, private val block: FuckEditText.() -> Unit) {
        fun build() = FuckEditText(mContext).apply(block)
    }

    class FastBuilder(private val mContext: Context = Config.context, private val mHint: String? = null, private val mKey: String) {
        fun build() = FuckEditText(mContext).apply {
            mHint?.let { hint = it }
            key = mKey
        }
    }
}