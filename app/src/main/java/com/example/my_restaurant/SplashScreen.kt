package com.example.my_restaurant
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

val Time = 4000L

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Find the ImageViews
        val smileImageforLeft = findViewById<ImageView>(R.id.leftpizza)
        val smileImageforRight = findViewById<ImageView>(R.id.rightpizza)

        // Load the animations
        val slideRightAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_right)
        val slideLeftAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_left)
        val danceLeftAnimation = AnimationUtils.loadAnimation(this, R.anim.dance_left_pizza_animation)
        val danceRightAnimation = AnimationUtils.loadAnimation(this, R.anim.dance_right_pizza_animation)

        // Set the visibility of the ImageViews to visible
        smileImageforLeft.visibility = View.VISIBLE
        smileImageforRight.visibility = View.VISIBLE

        // Apply the slide-right animation to the left ImageView and slide-left animation to the right ImageView
        smileImageforLeft.startAnimation(slideRightAnimation)
        smileImageforRight.startAnimation(slideLeftAnimation)

        // Start the dance animations after a delay
        val slideDuration = Math.max(slideRightAnimation.duration, slideLeftAnimation.duration)
        Handler().postDelayed({
            smileImageforLeft.startAnimation(danceLeftAnimation)
            smileImageforRight.startAnimation(danceRightAnimation)
        }, slideDuration)

        // Start the MainActivity after the specified delay
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, Time)
    }
}
