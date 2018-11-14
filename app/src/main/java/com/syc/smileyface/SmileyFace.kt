package com.syc.smileyface

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView

/**
 *
 *Created by suyichen on 2018/11/14.
 *
 */
class SmileyFace : FrameLayout {

    private var childView1: View? = null
    private var childView2: View? = null
    private var childView1X: Float = 0.toFloat()
    private var childView1Y: Float = 0.toFloat()
    private var childView2X: Float = 0.toFloat()
    private var childView2Y: Float = 0.toFloat()
    private var centerX: Float = 0.toFloat()
    private var centerY: Float = 0.toFloat()
    private var hasClick: Boolean = false
    private var normal1: Int? = null
    private var click1: Int? = null
    private var normal2: Int? = null
    private var click2: Int? = null
    private var onMenuClickListener: OnMenuClickListener? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun initView() {
        childView1 = ImageView(context)
        childView2 = ImageView(context)
        addView(childView1)
        addView(childView2)
    }

    fun isHasClick(): Boolean {
        return hasClick
    }


    fun setOnMenuClickListener(onMenuClickListener: OnMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener
    }

    fun setImage(normal1: Int, click1: Int, normal2: Int, click2: Int) {
        this.normal1 = normal1
        this.click1 = click1
        this.normal2 = normal2
        this.click2 = click2
        refreshDrawable()
    }

    fun setHasClick(hasClick: Boolean){
        this.hasClick = hasClick
        refreshDrawable()
    }

    private fun refreshDrawable() {
        if (hasClick) {
            if (click1 != 0)
                click1?.let { childView1?.setBackgroundResource(it) }
            if (click2 != 0)
                click2?.let { childView2?.setBackgroundResource(it) }
        } else {
            if (normal1 != 0)
                normal1?.let { childView1?.setBackgroundResource(it) }
            if (normal2 != 0)
                normal2?.let { childView2?.setBackgroundResource(it) }
        }
    }

    interface OnMenuClickListener {
        fun onItemClick(view: View)
    }

    private fun isContain(view: View, rawX: Float, rawY: Float): Boolean {
        val point = IntArray(2)
        view.getLocationOnScreen(point)
        return x >= point[0] && x <= point[0] + view.width && y >= point[1] && y <= point[1] + view.height
    }

    private fun changeWhenMove(x: Float, y: Float) {
        var x = x
        var y = y
        if (y + centerY < -12 * centerY) {
            y = -12 * centerY - centerY
        } else if (y - centerY > 12 * centerY) {
            y = 12 * centerY + centerY
        }
        if (x + centerX < -12 * centerX) {
            x = -12 * centerX - centerX
        } else if (x - centerX > 12 * centerX) {
            x = 12 * centerX + centerX
        }
        childView1?.x = childView1X + (x - centerX) / 23
        childView1?.y = childView1Y + (y - centerY) / 23
        if (childView2 != null) {
            childView2!!.x = childView2X + (x - centerX) / 10
            childView2!!.y = childView2Y + (y - centerY) / 10
        }

    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        centerX = (height / 5).toFloat()
        centerY = (width / 5).toFloat()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        childView1 = getChildAt(0)
        childView2 = getChildAt(1)
        childView1X = childView1?.x!!
        childView1Y = childView1?.y!!
        childView2X = childView2?.x!!
        childView2Y = childView2?.y!!
    }

    private fun restorePosition() {
        childView1?.x = childView1X
        childView1?.y = childView1Y
        childView2?.x = childView2X
        childView2?.y = childView2Y
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y
        val action = event?.action
        when (action) {
            MotionEvent.ACTION_MOVE -> {
                changeWhenMove(x!!, y!!)
                return true
            }
            MotionEvent.ACTION_UP -> {
                restorePosition()
                if (isContain(this, event.rawX, event.rawY)) {
                    setHasClick(!hasClick)
                    if (onMenuClickListener != null) {
                        onMenuClickListener!!.onItemClick(this)
                    }
                }
                return true
            }
        }
        return true
    }

}