package kuy.belajar.exampleoftablayoutactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //     Support Fragment Manager
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

        //      View Pager
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        //      Tab Layout
        val tabs: TabLayout = findViewById(R.id.tab_layout)
        tabs.setupWithViewPager(viewPager)
        supportActionBar?.elevation = 0f
    }
}