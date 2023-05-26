package com.example.dominictoretto.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.dominictoretto.databinding.LogoBinding
import com.example.dominictoretto.viewModel.LogoViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogoActivity : AppCompatActivity() {
    private lateinit var binding: LogoBinding
    private val logoViewModel: LogoViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LogoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        logoViewModel.loadSaveText()
        observeData()
    }

    private fun observeData() {
        lifecycleScope.launch {
            logoViewModel.checkLogin.collect { isCheck ->
                if (isCheck != null) {
                    if (isCheck) {
                        val intent = Intent(this@LogoActivity, LoginActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this@LogoActivity, MovieActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("finish()"))
    override fun onBackPressed() {
        finish()
    }
}