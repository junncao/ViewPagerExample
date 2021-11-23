package com.example.viewpager2example

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager = findViewById<ViewPager2>(R.id.vpViewsWithItem)

        viewPager.adapter = ViewPager2Adapter(
            context = this,
            listItem = listOf(
                "EXAMPLE1",
                "EXAMPLE2",
                "EXAMPLE3",
                "EXAMPLE4"
            )
        )
        viewPager.offscreenPageLimit = 1
        val nextItemVisiblePx = 100   //展现出来的下一个view的水平长度
        val currentItemHorizontalMarginPx = 220  //到view边缘的margin大小
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            // Next line scales the item's height. You can remove it if you don't want this effect
            page.scaleY = 1 - (0.25f * abs(position))
            // If you want a fading effect uncomment the next line:
            // page.alpha = 0.25f + (1 - abs(position))
            Log.d("transform", "position: $position translationX: ${page.translationX} scaleY: ${page.scaleY}")
        }
        viewPager.setPageTransformer(pageTransformer)

// The ItemDecoration gives the current (centered) item horizontal margin so that
// it doesn't occupy the whole screen width. Without it the items overlap
        val itemDecoration = HorizontalMarginItemDecoration(currentItemHorizontalMarginPx)
        viewPager.addItemDecoration(itemDecoration)
    }
}

class HorizontalMarginItemDecoration(h: Int) :
    RecyclerView.ItemDecoration() {

    private val horizontalMarginInPx: Int = h

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.right = horizontalMarginInPx
        outRect.left = horizontalMarginInPx
    }

}