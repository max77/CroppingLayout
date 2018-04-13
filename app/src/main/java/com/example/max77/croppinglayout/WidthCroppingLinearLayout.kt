package com.example.max77.croppinglayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

/**
 * A LinearLayout that crops out child views that do not fit the width of the parent
 */
class WidthCroppingLinearLayout(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
        LinearLayout(context, attrs, defStyleAttr) {

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var totalWidth = 0
        var childrenChanged = false

        for (i in 0 until childCount) {
            val child = getChildAt(i)

            // we want children to measure themselves in an unconstrained state
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
            totalWidth += getChildAt(i).measuredWidth

            // just hide the children that do not fit the layout
            if (totalWidth > measuredWidth) {
                child.visibility = View.GONE
                childrenChanged = true
            }
        }

        // make the layout re-measure itself in case of a child has been changed
        if (childrenChanged) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}