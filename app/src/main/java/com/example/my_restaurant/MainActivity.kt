package com.example.my_restaurant
import BookTableDialogFragment
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
/**
 * The MainActivity class represents the main entry point of the restaurant application.
 * It displays buttons for accessing different features of the application, including the menu,
 * rating functionality, and booking a table. Click listeners are implemented for each button
 * to navigate to their respective activities or dialogs.
 **/
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // Adjust padding to accommodate system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //handle Menu button
        val menuButton = findViewById<Button>(R.id.Menu)
        menuButton.setOnClickListener{
            // Start MenuActivity when the button is clicked
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
        //handle Rate us button
        val rateUsButton = findViewById<Button>(R.id.rateUs)
        rateUsButton.setOnClickListener{
            // Start MenuActivity when the button is clicked
            val intent = Intent(this, RateActivity::class.java)
            startActivity(intent)
        }
        //handle book table button
        val bookTableButton = findViewById<Button>(R.id.bookTableButton)
        bookTableButton.setOnClickListener {
            // Show dialog
            val dialogFragment = BookTableDialogFragment()
            dialogFragment.show(supportFragmentManager, "BookTableDialogFragment")
        }
    }
}

