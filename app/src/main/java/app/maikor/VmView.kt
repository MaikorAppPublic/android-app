package app.maikor

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import app.maikor.adapter.VmInterface
import kotlin.system.measureTimeMillis

class VmView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    private val vmWidth = VmInterface.getScreenWidth()
    private val vmHeight = VmInterface.getScreenHeight()

    private val bitmap = Bitmap.createBitmap(vmWidth, vmHeight, Bitmap.Config.ARGB_8888)

    var src = Rect(0,0,vmWidth, vmHeight)
    var dest = Rect(0,0,vmWidth, vmHeight)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val scale = measuredWidth / src.width()
        dest.left = 0
        dest.top = 0
        dest.right = src.right * scale
        dest.bottom = src.bottom * scale
        dest.offsetTo((measuredWidth * 0.5 - dest.width() * 0.5).toInt(), (measuredHeight * 0.5 - dest.height() * 0.5).toInt())
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        bitmap.eraseColor(Color.BLACK)
        val time = measureTimeMillis { VmInterface.updateBitmap(bitmap) }
        Log.d("###","duration: ${time}ms")
        canvas.drawBitmap(bitmap, src, dest, null)
    }
}