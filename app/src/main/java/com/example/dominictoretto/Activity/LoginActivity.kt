package com.example.dominictoretto.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.dominictoretto.databinding.LoginPageBinding
import com.example.dominictoretto.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginPageBinding
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.inputName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                loginViewModel.onTextChange(s.toString())
                loginViewModel.saveData(s.toString())
            }
        })

        loginViewModel.isButtonVisible.observe(this) { isShow ->
            binding.button.isVisible = isShow
        }

        binding.button.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        loginViewModel.exitApp.observe(this) { shouldExit ->
            if (shouldExit) {
                super.onBackPressed()
            } else {
                Toast.makeText(this, "กดอีกครั้งเพื่อออก", Toast.LENGTH_SHORT).show()
                loginViewModel.onBackPressed()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        loginViewModel.onBackPressed()
    }
}