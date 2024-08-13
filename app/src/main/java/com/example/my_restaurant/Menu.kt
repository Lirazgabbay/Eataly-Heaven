package com.example.my_restaurant
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
/**
 * The Menu class represents an Android activity that displays the restaurant menu.
 * Users can browse through different sections of the menu, such as starters, main course,
 * desserts, and beverages. Each section contains various items with descriptions that can
 * be toggled to show or hide. The activity includes click listeners for navigating back
 * to the main page and toggling the visibility of menu items.
 **/

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        //Tool Bar
        val arrowBackIcon: ImageView = findViewById(R.id.arrow_back_icon)
        arrowBackIcon.setOnClickListener {
            // Navigate back to MainActivity when arrow icon is clicked
            val intent = Intent(this@Menu, MainActivity::class.java)
            startActivity(intent)
        }
        enableEdgeToEdge()
        // Handling click for the "Starters" section
        val startersSection = findViewById<TextView>(R.id.starters_section)
        startersSection.setOnClickListener {
            // Toggle the visibility of all starter images
            val startersImagesContainer = findViewById<View>(R.id.starters_images_container)
            startersImagesContainer.visibility = if (startersImagesContainer.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
        //taking care of pasta salad button
        val pastaSaladDescription = findViewById<TextView>(R.id.pasta_salad_description)
        val startersImageButton1 = findViewById<ImageButton>(R.id.pasta_image_button)
        startersImageButton1.setOnClickListener {
            // Toggle the visibility of the description TextView
            if (pastaSaladDescription.visibility == View.VISIBLE) {
                pastaSaladDescription.visibility = View.GONE
            } else {
                pastaSaladDescription.visibility = View.VISIBLE
            }
        }
        //taking care of pesto Mozzarella Arancini button
        val pestoMozzarellaAranciniDescription = findViewById<TextView>(R.id.arancini_description)
        val startersImageButton2 = findViewById<ImageButton>(R.id.arancini_image_button)
        startersImageButton2.setOnClickListener {
            // Toggle the visibility of the description TextView
            if (pestoMozzarellaAranciniDescription.visibility == View.VISIBLE) {
                pestoMozzarellaAranciniDescription.visibility = View.GONE
            } else {
                pestoMozzarellaAranciniDescription.visibility = View.VISIBLE
            }
        }
        //taking care of grilled eggplants button
        val grilledeggplantsDescription = findViewById<TextView>(R.id.grilled_eggplants_description)
        val startersImageButton3 = findViewById<ImageButton>(R.id.grilled_eggplants_image_button)
        startersImageButton3.setOnClickListener {
            // Toggle the visibility of the description TextView
            if (grilledeggplantsDescription.visibility == View.VISIBLE) {
                grilledeggplantsDescription.visibility = View.GONE
            } else {
                grilledeggplantsDescription.visibility = View.VISIBLE
            }
        }
        //taking care of the "Main Course" section click
        val mainCourseSection = findViewById<TextView>(R.id.Main_Course_section)
        mainCourseSection.setOnClickListener {
            // Toggle the visibility of all starter images
            val MainCourseImagesContainer = findViewById<View>(R.id.Main_Course_images_container)
            MainCourseImagesContainer.visibility = if (MainCourseImagesContainer.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
        //taking care of ravioli button
        val ravioliDescription = findViewById<TextView>(R.id.ravioli_description)
        val mainCourseImageButton1 = findViewById<ImageButton>(R.id.ravioli_image_button)
        mainCourseImageButton1.setOnClickListener {
            // Toggle the visibility of the description TextView
            if (ravioliDescription.visibility == View.VISIBLE) {
                ravioliDescription.visibility = View.GONE
            } else {
                ravioliDescription.visibility = View.VISIBLE
            }
        }
        //taking care of pizza margherita button
        val pizzamargheritaDescription = findViewById<TextView>(R.id.pizzamargherita_description)
        val mainCourseImageButton2 = findViewById<ImageButton>(R.id.pizzamargherita_image_button)
        mainCourseImageButton2.setOnClickListener {
            // Toggle the visibility of the description TextView
            if (pizzamargheritaDescription.visibility == View.VISIBLE) {
                pizzamargheritaDescription.visibility = View.GONE
            } else {
                pizzamargheritaDescription.visibility = View.VISIBLE
            }
        }
        //taking care of Creamy broccoli pasta button
        val patabroccoli = findViewById<TextView>(R.id.pasta_broccoli_description)
        val mainCourseImageButton3 = findViewById<ImageButton>(R.id.pastabroccoli_image_button)
        mainCourseImageButton3.setOnClickListener {
            // Toggle the visibility of the description TextView
            if (patabroccoli.visibility == View.VISIBLE) {
                patabroccoli.visibility = View.GONE
            } else {
                patabroccoli.visibility = View.VISIBLE
            }
        }
        //taking care of the "Dessert" section click
        val DessertSection = findViewById<TextView>(R.id.Dessert_section)
        val DessertImagesContainer = findViewById<View>(R.id.Dessert_images_container)
        DessertSection.setOnClickListener {
            // Toggle the visibility of all starter images
            DessertImagesContainer.visibility = if (DessertImagesContainer.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
        //taking care of tiramisu button
        val tiramisuDescription = findViewById<TextView>(R.id.tiramisu_description)
        val DessertImageButton1 = findViewById<ImageButton>(R.id.tiramisu_image_button)
        DessertImageButton1.setOnClickListener {
            // Toggle the visibility of the description TextView
            if (tiramisuDescription.visibility == View.VISIBLE) {
                tiramisuDescription.visibility = View.GONE
            } else {
                tiramisuDescription.visibility = View.VISIBLE
            }
        }
        //taking care of Crème Brûlée button
        val creamBurleeDescription = findViewById<TextView>(R.id.cream_Burlee_description)
        val DessertImageButton2 = findViewById<ImageButton>(R.id.cream_Burlee_image_button)
        DessertImageButton2.setOnClickListener {
            // Toggle the visibility of the description TextView
            if (creamBurleeDescription.visibility == View.VISIBLE) {
                creamBurleeDescription.visibility = View.GONE
            } else {
                creamBurleeDescription.visibility = View.VISIBLE
            }
        }
        //taking care of the "Beverages" section click
        val beveragesSection = findViewById<TextView>(R.id.Beverages_section)
        beveragesSection.setOnClickListener {
            // Toggle the visibility of all starter images
            val BeveragesImagesContainer = findViewById<View>(R.id.Beverages_images_container)
            BeveragesImagesContainer.visibility = if (BeveragesImagesContainer.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
        //taking care of softdrinks button
        val softdrinksDescription = findViewById<TextView>(R.id.softdrinks_description)
        val BeveragesImageButton = findViewById<ImageButton>(R.id.softdrinks_image_button)
        BeveragesImageButton.setOnClickListener {
            // Toggle the visibility of the description TextView
            if (softdrinksDescription.visibility == View.VISIBLE) {
                softdrinksDescription.visibility = View.GONE
            } else {
                softdrinksDescription.visibility = View.VISIBLE
            }
        }
        //taking care of pink Lemonade Mimosa button
        val pinklemonademimosaDescription = findViewById<TextView>(R.id.pinklemonademimosa_description)
        val BeveragesImageButton2 = findViewById<ImageButton>(R.id.pinklemonademimosa_image_button)
        BeveragesImageButton2.setOnClickListener {
            // Toggle the visibility of the description TextView
            if (pinklemonademimosaDescription.visibility == View.VISIBLE) {
                pinklemonademimosaDescription.visibility = View.GONE
            } else {
                pinklemonademimosaDescription.visibility = View.VISIBLE
            }
        }
        //taking care of mango milkshake button
        val mangomilkshakeDescription = findViewById<TextView>(R.id.mangomilkshake_description)
        val BeveragesImageButton3 = findViewById<ImageButton>(R.id.mangomilkshake_image_button)
        BeveragesImageButton3.setOnClickListener {
            // Toggle the visibility of the description TextView
            if (mangomilkshakeDescription.visibility == View.VISIBLE) {
                mangomilkshakeDescription.visibility = View.GONE
            } else {
                mangomilkshakeDescription.visibility = View.VISIBLE
            }
        }

    }
}