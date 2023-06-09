package com.example.dominictoretto.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.dominictoretto.R
import com.example.dominictoretto.databinding.LoginPageBinding
import com.example.dominictoretto.viewModel.LoginViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginPageBinding
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observer()
    }
    private fun observer(){
        lifecycleScope.launch {
            loginViewModel.isButtonVisible.collect{ isShow ->
                binding.button.isVisible = isShow
            }
        }

        binding.inputName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                loginViewModel.onTextChange(s.toString())
            }
        })

        binding.button.setOnClickListener {
                loginViewModel.onClick()
                val intent = Intent(this@LoginActivity, MovieActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        loginViewModel.checkCurrentTime(System.currentTimeMillis())
        if (loginViewModel.backPressed.value) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, R.string.close_app, Toast.LENGTH_SHORT).show()
        }
    }
}