package com.james.myapplication.presentation.ui.first

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import com.james.myapplication.R
import com.james.myapplication.base.BaseActivity
import com.james.myapplication.databinding.ActivityFirstScreenBinding
import com.james.myapplication.presentation.common.buildAestheticDialog
import com.james.myapplication.presentation.ui.second.SecondScreenActivity
import com.james.myapplication.presentation.validator.ConstraintValidator
import com.james.myapplication.presentation.validator.FirstScreenValidator
import com.james.myapplication.util.Constant.EXTRA_NAME
import com.james.myapplication.util.ScreenOrientation

import com.thecode.aestheticdialogs.DialogType
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstScreenActivity : BaseActivity<ActivityFirstScreenBinding>() {
    
    private val viewModel by viewModel<FirstScreenViewModel>()
    
    override fun inflateViewBinding(): ActivityFirstScreenBinding = ActivityFirstScreenBinding.inflate(layoutInflater)
    
    override fun determineScreenOrientation(): ScreenOrientation = ScreenOrientation.PORTRAIT
    
    override fun onCreateBehaviour(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
    
    override fun ActivityFirstScreenBinding.binder() {
        btnCheck.setOnClickListener {
            val palindromeText = edtPalindrome.text.toString()
            viewModel.isPalindrome(palindromeText)
            lifecycleScope.launchWhenStarted {
                viewModel.isPalindrome.collect { isPalindrome ->
                    if(isPalindrome)
                        buildAestheticDialog(
                            DialogType.SUCCESS,
                            "Palindrome Check",
                            resources.getString(R.string.palindrome_true)
                        ) {
                            it.dismiss()
                        }
            
                    else
                        buildAestheticDialog(
                            DialogType.ERROR,
                            "Palindrome Check",
                            resources.getString(R.string.palindrome_false)
                        ) {
                            it.dismiss()
                        }
                }
            }
        }
        
        btnNext.setOnClickListener {
            val name = edtName.text.toString()
            val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
            intent.putExtra(EXTRA_NAME, name)
            startActivity(intent)
        }
    }
    
    override fun validateConstraint(): ConstraintValidator {
        return FirstScreenValidator(binding)
    }
}