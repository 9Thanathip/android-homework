package com.example.dominictoretto

import android.util.Log
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.example.dominictoretto.databinding.LoginPageBinding

class loginViewHolder (
) {
    fun onBindViewHolder(holder:LoginPageViewHolder){
        holder.binding.apply {
            val text = inputName.toString()
            Log.d("ddd",text)
        }
    }

    inner class LoginPageViewHolder(
        val binding: LoginPageBinding,
    ) : RecyclerView.ViewHolder(binding.root)
}