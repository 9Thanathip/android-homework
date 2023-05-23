package com.example.dominictoretto.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.dominictoretto.databinding.LoginPageBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginPageBinding.inflate(layoutInflater)
        val view = binding.root
        val PREF_NAME = "dataSave"
        var inputText = ""
        val sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        setContentView(view)
        binding.inputName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val textLength = s?.length ?: 0

                binding.button.isVisible = textLength >= 4

                inputText = s.toString()
            }
        })

        binding.button.setOnClickListener {
            with(sharedPref.edit()) {
                putString("key", inputText)
                apply()
            }
            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)
        }
    }
}