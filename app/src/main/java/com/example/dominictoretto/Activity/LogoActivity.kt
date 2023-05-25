package com.example.dominictoretto.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dominictoretto.databinding.LogoBinding
import com.example.dominictoretto.viewModel.LogoViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogoActivity : AppCompatActivity() {
    private lateinit var binding: LogoBinding
    private val logoViewModel: LogoViewModel by viewModel()
    companion object{
        const val PREF_NAME = "dataSave"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LogoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val savedText = sharedPref.getString("key", "")

        GlobalScope.launch {
            delay(3000)
            if (savedText.isNullOrEmpty()) {
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

    override fun onBackPressed() {
        finish()
    }
}