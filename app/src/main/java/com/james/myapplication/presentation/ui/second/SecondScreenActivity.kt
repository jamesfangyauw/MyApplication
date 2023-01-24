package com.james.myapplication.presentation.ui.second

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.james.myapplication.R
import com.james.myapplication.base.BaseActivity
import com.james.myapplication.databinding.ActivitySecondScreenBinding
import com.james.myapplication.presentation.ui.third.ThirdScreenActivity
import com.james.myapplication.util.Constant.EXTRA_NAME
import com.james.myapplication.util.Constant.USER_NAME
import com.james.myapplication.util.ScreenOrientation

import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondScreenActivity : BaseActivity<ActivitySecondScreenBinding>() {
    
    private val viewModel by viewModel<SecondScreenViewModel>()
    
    override fun inflateViewBinding(): ActivitySecondScreenBinding = ActivitySecondScreenBinding.inflate(layoutInflater)
    
    override fun determineScreenOrientation(): ScreenOrientation = ScreenOrientation.PORTRAIT
    
    override fun onCreateBehaviour(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.white)
        }
    }
    
    override fun ActivitySecondScreenBinding.binder() {
        val nameArg = intent.getStringExtra(EXTRA_NAME) ?: ""
        
        secondScreenAppBar.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.hide()
            ivBack.setOnClickListener { finish() }
            tvTitle.text = resources.getString(R.string.second_screen)
        }
        
        viewModel.setName(nameArg)
        
        lifecycleScope.launchWhenStarted {
            viewModel.name.collect {
                tvName.text = it
            }
        }
    
        tvSelectedUser.text = USER_NAME ?: ""
        
        btnChooseUser.setOnClickListener {
            val intent = Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java)
            startActivity(intent)
        }
    }
    
    override fun onResume() {
        super.onResume()
        binding.tvSelectedUser.text = USER_NAME ?: ""
    }
    
}