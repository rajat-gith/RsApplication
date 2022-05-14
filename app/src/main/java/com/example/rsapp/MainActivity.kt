package com.example.rsapp

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.rsapp.databinding.ActivityMainBinding
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerlayout:DrawerLayout
    val home_fragment= Home_fragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerlayout=findViewById(R.id.drawerlayout)
        val imageSlider = binding.imageSlider
        val imageList: ArrayList<String> = ArrayList()

//        val home_fragment= Home_fragment()
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.flfragment,projects_fragment())
//            commit()
//        }


        imageList.add("https://www.vssutrobotics.com/images/gallery/gallery_7.webp")
        imageList.add("https://www.vssutrobotics.com/images/gallery/gallery_16.webp")
        imageList.add("https://www.vssutrobotics.com/images/gallery/gallery_8.webp")
        imageList.add("https://www.vssutrobotics.com/images/gallery/gallery_9.webp")
        imageList.add("https://www.vssutrobotics.com/images/gallery/gallery_10.webp")
        imageList.add("https://www.vssutrobotics.com/images/gallery/gallery_11.webp")
        setImageInSlider(imageList, imageSlider)

        drawerlayout=findViewById(R.id.drawerlayout)
        toggle= ActionBarDrawerToggle(this,drawerlayout,R.string.open,R.string.close)
        toggle.isDrawerIndicatorEnabled=true
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nav_view.setNavigationItemSelectedListener {
            it.isChecked=true
            when(it.itemId){
                R.id.Item1 -> replacefragment(Profile_fragment(),it.title.toString())
                R.id.Item2 -> replacefragment(Achievments_fragment(),it.title.toString())
                R.id.Item3 -> replacefragment(projects_fragment(),it.title.toString())
                R.id.Item4 -> replacefragment(notification_fragment(),it.title.toString())
            }
            true
        }
    }

    private fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView) {
        val adapter = Adapter_for_imageslide_for_homescreen()
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.isAutoCycle = true
        imageSlider.startAutoCycle()
    }
    private fun replacefragment(fragment:Fragment,title:String)
    {
        val fragmentmanager = supportFragmentManager
        val fragmentransaction=fragmentmanager.beginTransaction()
        fragmentransaction.replace(R.id.flfragment,fragment,fragment::class.java.simpleName)
        Log.d("fun","functionCalled")
        fragmentransaction.commit()
        drawerlayout.closeDrawers()
        setTitle(title)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
