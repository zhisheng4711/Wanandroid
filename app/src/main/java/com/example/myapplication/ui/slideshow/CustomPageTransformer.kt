package com.example.myapplication.ui.slideshow

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class CustomPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        val absPosition = Math.abs(position)

        // 在这里根据需要对页面进行自定义动画和过渡效果的操作

        // 例如，可以对页面进行缩放和透明度变化
        page.scaleY = 0.85f + (1f - 0.85f) * (1f - absPosition)
        page.alpha = 0.5f + (1f - 0.5f) * (1f - absPosition)
    }
}
