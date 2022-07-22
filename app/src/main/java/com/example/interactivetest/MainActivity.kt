package com.example.interactivetest

import android.os.Bundle
import android.util.Log
import android.view.Window
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.interactivetest.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_history
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)*/
        navView.setupWithNavController(navController)

        /*val db = FirebaseFirestore.getInstance()
        var books = hashMapOf(
            "name" to "The Book",
            "author" to "M. Clifford",
            "is_booked" to "tester2",
            "date_booked" to "2022-07-21",
            "date_booked_end" to "2022-07-24",
            "image" to "https://upload.wikimedia.org/wikipedia/commons/9/92/THE_BOOK_cover_image.png"
        )
        db.collection("history").add(books)
            .addOnSuccessListener { Log.d("TAGBOOKADD", "Book successfully added")
            }
            .addOnFailureListener { e -> Log.w("TAGBOOKADD", "Error adding document", e) }

        books = hashMapOf(
            "name" to "The Ginger Man",
            "author" to "J. P. Donleavy",
            "is_booked" to "tester1",
            "date_booked" to "2022-07-21",
            "date_booked_end" to "2022-07-24",
            "image" to "https://bestlifeonline.com/wp-content/uploads/sites/3/2020/10/The-Ginger-Man-book-cover.jpg?resize=500,742&quality=82&strip=all"
        )
        db.collection("history").add(books)
            .addOnSuccessListener { Log.d("TAGBOOKADD", "Book successfully added")
            }
            .addOnFailureListener { e -> Log.w("TAGBOOKADD", "Error adding document", e) }*/
    }
}