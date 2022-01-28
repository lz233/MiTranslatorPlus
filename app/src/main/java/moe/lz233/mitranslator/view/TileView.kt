package moe.lz233.mitranslator.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.view.View
import androidx.core.graphics.ColorUtils
import moe.lz233.mitranslator.Config

//                                           f2639a
class TileView(context: Context, private val icon: Drawable, private val title: String, private val packageName: String) : View(context) {
    var color1 = 0
    var color2 = 0
    val g = Paint().apply { color = -1 } //f2645g for title?
    val h = Paint().apply { color = -1 }
    var i = Paint()
    lateinit var j: StaticLayout
    var k = false
    var l = 1.0f

    @SuppressLint("ResourceType")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val i = if (k) 178 else 255 //val measuredWidth = measuredWidth
        canvas.save()
        val path = Path()
        path.addRoundRect(RectF(0f, 0f, width - 0f, height - 0f), 5.0f, 5.0f, Path.Direction.CCW)
        canvas.clipPath(path)
        this.i.shader = LinearGradient(0f, 0f, width.toFloat(), height.toFloat(), if (k) ColorUtils.setAlphaComponent(color1, 178) else color1, if (k) ColorUtils.setAlphaComponent(color2, 178) else color2, Shader.TileMode.CLAMP)
        canvas.drawPaint(this.i)
        this.i.setShader(null)
        canvas.restore()
        val i2 = 38
        val rect = Rect()
        val rect2 = Rect()
        this.g.textSize = 38f //ignore sub title
        //this.h.textSize = 26f
        this.g.getTextBounds(title, 0, title.length, rect) //no result
        //Math.max()
        val intrinsicWidth = icon.intrinsicWidth
        val intrinsicHeight = icon.intrinsicHeight //type 2
        rect.height()
        rect2.height()
        val f2 = 60f
        val f4 = ((measuredHeight - intrinsicHeight) / 2.0f) //+f6
        val f3 = intrinsicWidth + f2 + 22.0f
        val f5 = (measuredHeight / 2).toFloat() + (rect.height() / 2)
        this.g.textAlign = Paint.Align.LEFT
        this.g.style = Paint.Style.FILL
        canvas.save()
        canvas.translate(f2, f4)
        icon.draw(canvas)
        canvas.restore()
        /*val drawable = resources.getDrawable(2131165304, context.theme)
        drawable.alpha = i
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        canvas.save()
        canvas.translate((measuredWidth - 40 - drawable.intrinsicWidth).toFloat(), ((measuredHeight - drawable.intrinsicHeight) / 2).toFloat())
        drawable.draw(canvas)
        canvas.restore()*/
        canvas.scale(this.l, this.l, f3, f5)
        this.j = StaticLayout(title, TextPaint(this.g), width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0f, true)
        this.j.paint.alpha = i
        canvas.save()
        val f7 = 7.0f //l?!
        canvas.translate(f3, (f5 - rect.height().toFloat() - f7))
        this.j.draw(canvas)
        canvas.restore()
    }

    class FastBuilder(private val context: Context = Config.context, private val icon: Drawable, private val title: String, private val packageName: String) {
        fun build() = TileView(context, icon, title, packageName)
    }
}