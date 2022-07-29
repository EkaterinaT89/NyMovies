package ru.netology.nymovies.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.nymovies.R

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private val delayHandler: Handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SPLASH_DELAY: Long = 3000
    }

    private val runnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        delayHandler.postDelayed(runnable, SPLASH_DELAY)
    }

    public override fun onDestroy() {
        delayHandler.removeCallbacks(runnable)
        super.onDestroy()
    }

}