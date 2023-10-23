package com.example.myapplication.ui.slideshow

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.ui.slideshow.fragments.FirstFragment
import com.example.myapplication.ui.slideshow.fragments.morefragment.MoreFragment
import com.example.myapplication.ui.slideshow.fragments.ThirdFragment
import com.example.myapplication.ui.slideshow.fragments.SecondFragment


class MyFragmentStateAdapter(fragmentActivity: SlideshowFragment, fragments: List<Fragment>) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = listOf(
        FirstFragment(),
        SecondFragment(),
        ThirdFragment(),
        MoreFragment()
    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}


