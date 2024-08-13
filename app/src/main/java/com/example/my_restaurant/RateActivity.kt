package com.example.my_restaurant
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
/**
 * The RateActivity class represents an Android activity responsible for allowing users
 * to rate a restaurant based on various aspects such as service, food taste, and value.
 * It includes a Toolbar to navigate back to the main page, SeekBars for rating food, value,
 * and service, animations to enhance user experience, and a section for providing feedback.
 */
class RateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate)
        //tool bar to navigate back to main page
        val arrowBackIcon: ImageView = findViewById(R.id.arrow_back_icon)
        arrowBackIcon.setOnClickListener {
            // Navigate back to MainActivity when arrow icon is clicked
            val intent = Intent(this@RateActivity, MainActivity::class.java)
            startActivity(intent)
        }
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Find the SeekBars and TextView in the layout by their IDs
        val serviceSeekBar: SeekBar = findViewById(R.id.service_seekbar)
        val foodTasteSeekBar: SeekBar = findViewById(R.id.food_taste_seekbar)
        val valueSeekBar: SeekBar = findViewById(R.id.value_seekbar)
        val averageRatingTextView: TextView = findViewById(R.id.average_rating_text)
        // Create a SeekBarChangeListener to handle changes in SeekBar progress
        val seekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Calculate the average rating
                val averageRating = (serviceSeekBar.progress +
                        foodTasteSeekBar.progress +
                        valueSeekBar.progress) / 3.0
                // Display the average rating in the TextView
                val averageRatingLabel = getString(R.string.average_rating_label)
                averageRatingTextView.text = averageRatingLabel.format(averageRating)

            }
            // Display a toast message when the user starts adjusting a SeekBar
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Display a toast message when the user starts adjusting a SeekBar
                when (seekBar?.id) {
                    R.id.service_seekbar -> {
                        Toast.makeText(applicationContext, getString(R.string.adjusting_service_rating), Toast.LENGTH_SHORT).show()
                    }
                    R.id.food_taste_seekbar -> {
                        Toast.makeText(applicationContext, getString(R.string.adjusting_food_taste_rating), Toast.LENGTH_SHORT).show()
                    }
                    R.id.value_seekbar -> {
                        Toast.makeText(applicationContext, getString(R.string.adjusting_value_rating), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Display a toast message when the user stops adjusting a SeekBar
                when (seekBar?.id) {
                    R.id.service_seekbar -> {
                        Toast.makeText(applicationContext, getString(R.string.service_rating_set), Toast.LENGTH_SHORT).show()
                    }
                    R.id.food_taste_seekbar -> {
                        Toast.makeText(applicationContext, getString(R.string.food_taste_rating_set), Toast.LENGTH_SHORT).show()
                    }
                    R.id.value_seekbar -> {
                        Toast.makeText(applicationContext, getString(R.string.value_rating_set), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        // Attach the seekBarChangeListener to the SeekBars
        serviceSeekBar.setOnSeekBarChangeListener(seekBarChangeListener)
        foodTasteSeekBar.setOnSeekBarChangeListener(seekBarChangeListener)
        valueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener)
        // Find the TextView
        val thankyouTextView = findViewById<TextView>(R.id.thankyou)
        // Load the animations from the resources
        val blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.blink_animation)
        val fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_animation)
        val zoomAnimation= AnimationUtils.loadAnimation(this, R.anim.zoom_animation)
        val rotateAnimation= AnimationUtils.loadAnimation(this,R.anim.rotate_animation)
        // Apply the animations to the TextView
        // use setAnimation() to apply multiple animations simultaneously
        val animationSet = AnimationSet(true)
        animationSet.addAnimation(blinkAnimation)
        animationSet.addAnimation(fadeAnimation)
        animationSet.addAnimation(zoomAnimation)
        animationSet.addAnimation(rotateAnimation)
        // Apply the animation set to the TextView
        thankyouTextView.startAnimation(animationSet)
        // Set the visibility of the TextView to visible
        thankyouTextView.visibility = View.VISIBLE
        //feedBck switch
        val feedbackSwitch = findViewById<SwitchCompat>(R.id.feedback_switch)
        val feedbackEditText = findViewById<EditText>(R.id.feedback_edit_text)
        val saveButton = findViewById<Button>(R.id.save_feedback_button)
        //Set listener for feedback switch
        feedbackSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                feedbackEditText.visibility = View.VISIBLE
                saveButton.visibility = View.VISIBLE
            } else {
                feedbackEditText.visibility = View.GONE
                saveButton.visibility = View.GONE
            }
        }
        // Set click listener for save feedback button
        saveButton.setOnClickListener {
            // show a confirmation message to the user
            Toast.makeText(this, getString(R.string.Feedbacksaved) , Toast.LENGTH_SHORT).show()
        }
    }
}