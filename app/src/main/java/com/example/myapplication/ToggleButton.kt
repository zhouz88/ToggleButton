package com.example.myapplication

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Scroller

class ToggleButton : ViewGroup {
    var mScroller: Scroller
    var mScrollerWidth: Int = 0
    var end = 0
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
            val start = end
            startScrollAnimation(start, getAnimationClick())
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
                // 由于不是 viewgroup 没设置 clickable。 此处true会直接消费该事件。接下来的背景move事件都会来这里消费
                // 默认如果不拦截 down 在哪消费 move 在哪消费 （此处false 会回到 上级 第一个true）
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
                startScrollAnimation(scrollX, getAnimationEnd())
                mLastX = x
                return true
            }
        }
//        mLastX = x
        return super.onTouchEvent(ev)
    }

    fun getAnimationEnd(): Int {
        end = 0
        if (scrollX < -measuredWidth / 4) {
            end = -mScrollerWidth
        }
        return end
    }

    fun getAnimationClick(): Int {
        return if (end == 0) {
            end = -mScrollerWidth
            end
        } else {
            end = 0
            end
        }
    }

    fun startScrollAnimation(start: Int, end: Int) {
        mScroller.startScroll(/* startX */ start,/* startY */0,/* dx */end - start,/* dy */0,/* duration */500)
        invalidate()
    }

    override fun invalidate() {
        super.invalidate()
    }
}
