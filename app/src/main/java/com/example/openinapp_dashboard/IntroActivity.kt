package com.example.openinapp_dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.openinapp_dashboard.databinding.ActivityIntroBinding
import com.example.openinapp_dashboard.fragments.RecentLinkFragment
import com.example.openinapp_dashboard.fragments.TopLinkFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.util.ArrayList
import java.util.Calendar

class IntroActivity : AppCompatActivity() {

    private lateinit var edittxtName: TextInputEditText
    private lateinit var textGreeting: TextView

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityIntroBinding.inflate(layoutInflater)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(binding.root)



        edittxtName = findViewById(R.id.edittxtName)
        textGreeting = findViewById(R.id.textGerrting)

        // Set greeting based on time of day
        setGreetingText()

        findViewById<MaterialButton>(R.id.NextButton).setOnClickListener {
            val name = edittxtName.text.toString()

            if (name.isEmpty()) {
                Snackbar.make(it, "Please enter your name", Snackbar.LENGTH_SHORT).show()
            } else {
                val greeting = textGreeting.text.toString()
                startActivity(Intent(this@IntroActivity, MainActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("greeting", greeting)
                })
                finish()
            }
        }


    }





    private fun setGreetingText() {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)

        textGreeting.text = when {
            hourOfDay in 0..11 -> "Good Morning!"
            hourOfDay in 12..17 -> "Good Afternoon!"
            else -> "Good Evening!"
        }
    }
}