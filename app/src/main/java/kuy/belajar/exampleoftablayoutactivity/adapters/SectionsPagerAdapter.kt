package kuy.belajar.exampleoftablayoutactivity.adapters

import android.content.Context
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kuy.belajar.exampleoftablayoutactivity.R
import kuy.belajar.exampleoftablayoutactivity.fragments.FollowerFragment
import kuy.belajar.exampleoftablayoutactivity.fragments.FollowingFragment

/**
 * Created by Imam Fahrur Rofi on 29/12/2020.
 */

// tambahkan variabel constructor baru bernama username
class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager, private val username : String)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = 2

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_text_1, R.string.tab_text_2)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = FollowingFragment.newInstance(username)
            }
            1 -> {
                fragment = FollowerFragment()
                val bundle = Bundle()
                bundle.putString(FollowerFragment.USERNAME_FOLLOWER, username)
                fragment.arguments = bundle
            }
        }
        return fragment as Fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence {
        return mContext.resources.getString(TAB_TITLES[position])
    }
}