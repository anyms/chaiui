package app.spidy.chaiui

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class ChaiButton: AppCompatButton {

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        val customAttrs = context.theme.obtainStyledAttributes(attrs, R.styleable.ChaiButton, 0, 0)
        try {
            setAttrs(customAttrs)
        } finally {
            customAttrs.recycle()
        }
    }

    private fun setAttrs(attrs: TypedArray) {
        val dp20 = resources.getDimension(R.dimen.dp20).toInt()
        val dp10 = resources.getDimension(R.dimen.dp10)


        val bgColor = attrs.getInteger(R.styleable.ChaiButton_chai_background, Color.TRANSPARENT)
        setBackgroundResource(R.drawable.chai_rounded_corners)
        val drawable = background as GradientDrawable
        drawable.setColor(bgColor)

        val radius = attrs.getDimension(R.styleable.ChaiButton_chai_radius, dp10)
        drawable.cornerRadius = radius

        val borderSize = attrs.getDimension(R.styleable.ChaiButton_chai_borderSize, 0f).toInt()
        if (borderSize != 0) {
            val borderColor = attrs.getColor(R.styleable.ChaiButton_chai_borderColor, Color.TRANSPARENT)
            drawable.setStroke(borderSize, borderColor)
        }


        val pad = attrs.getDimension(R.styleable.ChaiButton_chai_padding, -1f).toInt()
        if (pad == -1) setPadding(dp20, 0, dp20, 0) else setPadding(pad, pad, pad, pad)

        if (pad == -1) {
            val padBottom = attrs.getDimension(R.styleable.ChaiButton_chai_paddingBottom, 0f).toInt()
            val padTop = attrs.getDimension(R.styleable.ChaiButton_chai_paddingTop, 0f).toInt()
            val padStart = attrs.getDimension(R.styleable.ChaiButton_chai_paddingStart, dp20.toFloat()).toInt()
            val padEnd = attrs.getDimension(R.styleable.ChaiButton_chai_paddingEnd, dp20.toFloat()).toInt()

            setPadding(padStart, padTop, padEnd, padBottom)
        }
    }
}