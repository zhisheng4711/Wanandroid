package com.example.myapplication.ui.slideshow

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class StaggeredGridItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val layoutParams = view.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val spanIndex = layoutParams.spanIndex

        // 设置上下间距，这里使用spacing的值
        outRect.top = spacing
        outRect.bottom = spacing

        // 设置左右间距，根据spanIndex来确定
        if (spanIndex % 2 == 0) {
            outRect.left = spacing
            outRect.right = spacing / 2
        } else {
            outRect.left = spacing / 2
            outRect.right = spacing
        }
    }
}
