package kuy.belajar.exampleoftablayoutactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kuy.belajar.exampleoftablayoutactivity.adapters.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {

    // contoh saya menggunakan username sidiqpermana
    // username ini seharusnya didapatkan melalui intent
    private val username = "sidiqpermana"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //     Support Fragment Manager dengan tambahan parameter username
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, username)

        //      View Pager
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        //      Tab Layout
        val tabs: TabLayout = findViewById(R.id.tab_layout)
        tabs.setupWithViewPager(viewPager)
        supportActionBar?.elevation = 0f
    }
}