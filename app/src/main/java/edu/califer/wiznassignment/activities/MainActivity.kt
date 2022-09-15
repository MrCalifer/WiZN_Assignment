package edu.califer.wiznassignment.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import edu.califer.wiznassignment.R
import edu.califer.wiznassignment.databinding.ActivityMainBinding
import edu.califer.wiznassignment.fragments.HomeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: edu.califer.wiznassignment.viewmodel.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** Setting Up the View Model. */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel =
            ViewModelProvider(this)[edu.califer.wiznassignment.viewmodel.ViewModel::class.java]

        /**Setting the Status Bar Icon Color.*/
        viewModel.statusBarIconColor(1, this)
    }

    override fun onResume() {
        super.onResume()

        //Launching HomeFragment
        viewModel.launchFragment(HomeFragment(), "HomeFragment", supportFragmentManager)
    }
}