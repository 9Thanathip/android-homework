package com.example.dominictoretto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dominictoretto.data.appModule
import com.example.dominictoretto.databinding.LogoBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class LogoActivity : AppCompatActivity() {
    private lateinit var binding: LogoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LogoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val PREF_NAME = "dataSave"
        val sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val savedText = sharedPref.getString("key", "")
        startKoin {
            androidContext(this@LogoActivity)
            modules(appModule)
        }

        GlobalScope.launch {
            delay(3000)
            if (savedText.isNullOrEmpty()) {
                val intent = Intent(this@LogoActivity, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@LogoActivity, MovieActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}