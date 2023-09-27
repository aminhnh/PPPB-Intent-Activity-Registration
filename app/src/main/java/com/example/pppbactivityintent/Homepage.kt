package com.example.pppbactivityintent

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.example.pppbactivityintent.databinding.ActivityHomepageBinding
import com.example.pppbactivityintent.databinding.ActivityMainBinding

class Homepage : AppCompatActivity() {
    private lateinit var binding : ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val primaryColor = ContextCompat.getColor(applicationContext, R.color.main_blue);

        val data = intent.extras
        val username = data?.getString("username")
        val email = data?.getString("email")
        val phone = data?.getString("phone")

        with(binding){
            val text = "Welcome $username" +
                    "\nYour $email has been activated" +
                    "\nYour $phone has been registered "
            val index1 = "Welcome ".length
            val index2 = "Welcome $username\nYour ".length
            val index3 = "Welcome $username\nYour $email has been activated\nYour".length

            val spannable = SpannableStringBuilder(text)
            spannable.setSpan(
                ForegroundColorSpan(primaryColor),
                index1,
                index1 + username!!.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            spannable.setSpan(
                ForegroundColorSpan(primaryColor),
                index2,
                index2 + email!!.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            spannable.setSpan(
                ForegroundColorSpan(primaryColor),
                index3,
                index3 + phone!!.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            txtInfo.text = spannable

            btnLogout.setOnClickListener{
                val intentToRegister = Intent(this@Homepage, MainActivity::class.java)
                startActivity(intentToRegister)
            }
        }
    }
}