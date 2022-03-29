package com.vinayakstudios.happyplacesapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.vinayakstudios.happyplacesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private var shortAnimTime : Int = 0

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      val binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)

      replaceFragment(HomePageFragment())
      shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)

      binding.bnbBottomNavigationBar.setOnItemSelectedListener {
         when (it.itemId) {
            R.id.homePage -> {
               ViewEnterFadeAnimation(binding.appMainToolBar)
               replaceFragment(HomePageFragment())
               true
            }
            R.id.SearchPage -> {
               ViewExitFadeAnimation(binding.appMainToolBar)
               replaceFragment(SearchPageLayoutFragment())
               true
            }
            R.id.MapPage -> {
               true
            }
            else -> false
         }
      }
   }

   private fun replaceFragment(fragment : Fragment){
      if(fragment != null){
         supportFragmentManager.commit{
            setCustomAnimations(
               R.anim.enter_fade_in,
               R.anim.exit_fade_out
            )
            replace(R.id.fcContentViewArea, fragment)
            setReorderingAllowed(true)
         }
      }
   }

   private fun ViewExitFadeAnimation(view : View){
      view.apply {
         animate()
            .alpha(0f).duration = shortAnimTime.toLong()
         visibility = View.GONE
      }
   }

   private fun ViewEnterFadeAnimation(view : View){
      view.apply {
         alpha = 0f
         visibility = View.VISIBLE
         animate()
            .alpha(1f).duration = shortAnimTime.toLong()
      }
   }
}