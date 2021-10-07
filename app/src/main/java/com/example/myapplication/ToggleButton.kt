package com.example.myapplication

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Scroller

class ToggleButton : ViewGroup {
    var mScroller: Scroller
    var mScrollerWidth: Int = 0
    var isOpen: Boolean = false
    var H = 0
    var W = 0
    var mSliderWidth = 0
    var mLastX = 0

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.mipmap.background, options)
        W = options.outWidth
        H = options.outHeight
        Log.d("zhouzheng", "${W} + ${H}")
        mScroller = Scroller(context)
        setBackgroundResource(R.mipmap.background)
        val imageView = ImageView(context)
        imageView.setBackgroundResource(R.mipmap.slide)
        imageView.setOnClickListener {
            if (isOpen) {
                mScroller.startScroll(/* startX */ -mScrollerWidth,/* startY */0,/* dx */mScrollerWidth,/* dy */0,/* duration */500)
            } else {
                mScroller.startScroll(/* startX */ 0,/* startY */0,/* dx */-mScrollerWidth,/* dy */0,/* duration */500)
            }
            isOpen = !isOpen
            invalidate()
        }
        addView(imageView)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(W, H)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        mSliderWidth = measuredWidth / 2
        mScrollerWidth = measuredWidth - mSliderWidth
        getChildAt(0).layout(0, 0, mSliderWidth, measuredHeight)
    }

    override fun computeScroll() {
        super.computeScroll()
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.currX, mScroller.currY)
            invalidate()
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        mLastX = ev.getX().toInt()
        return ev.action == MotionEvent.ACTION_MOVE
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        val x = ev.getX().toInt()
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                if (!mScroller.isFinished) {
                    mScroller.abortAnimation()
                }
                // 由于不是 viewgroup 不是 clickable。 此处true会直接消费该事件。接下来的背景move事件都会来这里消费
                // return true
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = mLastX - x
                if (deltaX + scrollX < -mSliderWidth) {
                    scrollTo(-mScrollerWidth, 0)
                    return true
                } else if (deltaX + scrollX > 0) {
                    scrollTo(0, 0)
                    return true
                }
                scrollBy(deltaX, 0)
                mLastX = x
                return true
            }
            MotionEvent.ACTION_UP -> {
                //autoScroll()
                val bound = -measuredWidth / 4
                var deltaX = 0
                if (scrollX < bound) {
                    deltaX = -mScrollerWidth - scrollX
                    if (!isOpen) {
                        isOpen = true
                    }
                }
                if (scrollX >= bound) {
                    deltaX = -scrollX
                    if (isOpen) {
                        isOpen = false
                    }
                }
                mScroller.startScroll(/* startX */ scrollX,/* startY */0,/* dx */deltaX,/* dy */0,/* duration */500)
                invalidate()
                mLastX = x
                return true
            }
        }
        return false
    }
}