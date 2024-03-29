package com.example.desinglogin

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View

open class OnSwipeTouchListener(context: Context): View.OnTouchListener {
    private val gestureDetector:GestureDetector = TODO()


    init {
        gestureDetector= GestureDetector(context,GestureListener())
    }
    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }


    private inner  class GestureListener:SimpleOnGestureListener(){
        private val swipe_Threshold=100
        private val swipe_velocity_threshold=100

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val distanceX=e2.x - e1!!.x
            val distanceY=e2.y - e1.y
            if(Math.abs(distanceX)>Math.abs(distanceY) && Math.abs(distanceX)> swipe_Threshold
                && Math.abs(velocityX)>swipe_velocity_threshold){
                if(distanceX>0){
                    onSwipeRight()
                }else{
                    onSwipeLeft()
                }
                return true
            }
            return false
        }

    }
    open fun onSwipeRight(){}
    open fun onSwipeLeft(){}
}