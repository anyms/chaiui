package app.spidy.chaiui

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class ChaiIcon : AppCompatImageView {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        val customAttrs = context.theme.obtainStyledAttributes(attrs, R.styleable.ChaiIcon, 0, 0)
        try {
            setAttrs(customAttrs)
        } finally {
            customAttrs.recycle()
        }
    }

    private fun setAttrs(attrs: TypedArray) {
        val bgColor = attrs.getInteger(R.styleable.ChaiIcon_chai_background, Color.TRANSPARENT)
        setBackgroundResource(R.drawable.chai_icon_rounded_corners)
        val drawable = background as GradientDrawable
        drawable.setColor(bgColor)

        val radius = attrs.getDimension(R.styleable.ChaiIcon_chai_radius, 0f)
        drawable.cornerRadius = radius

        val borderSize = attrs.getDimension(R.styleable.ChaiIcon_chai_borderSize, 0f).toInt()
        if (borderSize != 0) {
            val borderColor = attrs.getColor(R.styleable.ChaiIcon_chai_borderColor, Color.TRANSPARENT)
            drawable.setStroke(borderSize, borderColor)
        }


        val iconColor = attrs.getColor(R.styleable.ChaiIcon_chai_icon_color, -1)
        if (iconColor != -1) {
            getDrawable().colorFilter = PorterDuffColorFilter(iconColor, PorterDuff.Mode.SRC_IN)
//            setColorFilter(iconColor)
        }

        val pad = attrs.getDimension(R.styleable.ChaiIcon_chai_padding, -1f).toInt()
        if (pad == -1) setPadding(0, 0, 0, 0) else setPadding(pad, pad, pad, pad)

        if (pad == -1) {
            val padBottom = attrs.getDimension(R.styleable.ChaiIcon_chai_paddingBottom, 0f).toInt()
            val padTop = attrs.getDimension(R.styleable.ChaiIcon_chai_paddingTop, 0f).toInt()
            val padStart = attrs.getDimension(R.styleable.ChaiIcon_chai_paddingStart, 0f).toInt()
            val padEnd = attrs.getDimension(R.styleable.ChaiIcon_chai_paddingEnd, 0f).toInt()

            setPadding(padStart, padTop, padEnd, padBottom)
        }
    }
}