package edu.califer.wiznassignment.viewmodel

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import edu.califer.wiznassignment.R
import edu.califer.wiznassignment.fragments.HomeFragment

class ViewModel : ViewModel() {

    private val TAG :String = "MovieRepository"


    /**
     * Function to Launch Fragment
     * @param fragment Pass the fragment to be launched
     * @param tag Pass the tag of the fragment
     */
    fun launchFragment(fragment: Fragment, tag: String, fragmentManager: FragmentManager) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        if (fragment == HomeFragment()) {
            transaction.add(R.id.container, fragment, tag)
        } else {
            transaction.replace(R.id.container, fragment, tag)
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }

    /**For the Status Bar Icon Color.
     * 0-> Icon Color will be White
     * else->Icon Color will be Black.*/
    fun statusBarIconColor(colorCode: Int, activity: Activity) {
        activity.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }
        when (colorCode) {
            0 -> {
                activity.window.apply {
                    decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                }
            }
            else -> {
                activity.window.apply {
                    decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
            }
        }
    }

}