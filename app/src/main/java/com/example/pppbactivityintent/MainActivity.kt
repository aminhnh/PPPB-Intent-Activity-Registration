package com.example.pppbactivityintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.pppbactivityintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val primaryColor = ContextCompat.getColor(applicationContext, R.color.main_blue);

        with(binding){
            val spannableLogin = SpannableStringBuilder( txtLoginLink.text.toString() )
            spannableLogin.setSpan(
                ForegroundColorSpan(primaryColor),
                "Already have an account? ".length,
                txtLoginLink.text.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            txtLoginLink.text = spannableLogin

            val spannableTOC = SpannableStringBuilder( txtToC.text.toString() )
            val index1 = "By checking the box you agree to our ".length
            val index2 = "By checking the box you agree to our Terms and ".length
            spannableTOC.setSpan(
                ForegroundColorSpan(primaryColor),
                index1,
                index1 + "Terms".length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            spannableTOC.setSpan(
                ForegroundColorSpan(primaryColor),
                index2,
                index2 + "Conditions".length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            txtToC.text = spannableTOC


            btnRegister.setOnClickListener{
                val data = Bundle()
                data.putString("username", inputUsername.text.toString())
                data.putString("email", inputEmail.text.toString())
                data.putString("phone", inputPhone.text.toString())
                data.putString("password", inputPassword.text.toString())

                if ( validate(data) ){
                    val intentToHomepage = Intent(this@MainActivity, Homepage::class.java)
                    intentToHomepage.apply { putExtras(data) }
                    startActivity(intentToHomepage)
                }
            }
        }

    }
    private fun validate(data : Bundle) : Boolean {
        with(binding){
            var result = true

            if ( data.getString("username")!!.isEmpty() ){
                editTextUsername.error = "Username is required!"
                result = false
            } else {
                editTextUsername.isErrorEnabled = false
            }
            if ( data.getString("email") == "" ){
                inputEmail.error = "Email is required!"
                result = false
            }
            if ( data.getString("phone") == ""){
                inputPhone.error = "Phone number is required!"
                result = false
            }
            if ( data.getString("password")!!.isEmpty() ) {
                inputPassword.error = "Password is required!"
                result = false
            }
            if ( data.getString("password")!!.length < 7 ) {
                inputPassword.error = "Password must be at least 7 characters long"
                result = false
            }
            if (result && !checkbox.isChecked) {
                Toast.makeText(this@MainActivity, "Please agree to the Terms and Conditions", Toast.LENGTH_SHORT).show()
                result = false
            }

            return result
        }
    }
}