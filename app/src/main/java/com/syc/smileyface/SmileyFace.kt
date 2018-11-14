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
    private val hasClick: Boolean = false
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


    fun setOnMenuClickListener(onMenuClickListener: OnMenuClickListener){
        this.onMenuClickListener = onMenuClickListener
    }

    interface OnMenuClickListener {
        fun onItemClick(view: View)
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

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
}