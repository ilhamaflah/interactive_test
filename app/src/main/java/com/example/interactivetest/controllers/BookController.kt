package com.example.interactivetest.controllers

import android.content.Context
import android.util.Log
import com.example.interactivetest.adapters.BookAdapter
import com.example.interactivetest.models.Book
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class BookController() {
    private val TAG_BOOK = "TAGBOOK"

    fun listBook(db: FirebaseFirestore, datas: ArrayList<Book>, adapter: BookAdapter, konteks: Context){
        db.collection("books")
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    Log.d(TAG_BOOK, "${document.id} => ${document.data["image"].toString()}")
                    datas.add(
                        Book(document.id, document.data["name"].toString(), document.data["author"].toString(), document.data["is_booked"].toString(),
                            document.data["date_booked"].toString(), document.data["date_booked_end"].toString(), document.data["image"].toString())
                    )
                    adapter.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { e -> Log.w(TAG_BOOK, "Error adding document", e) }
    }

    fun listHistory(db: FirebaseFirestore, datas: ArrayList<Book>, adapter: BookAdapter, konteks: Context, username: String){
        db.collection("history").whereEqualTo("is_booked", username)
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    Log.d(TAG_BOOK, "${document.id} => ${document.data["image"].toString()}")
                    datas.add(
                        Book(document.id, document.data["name"].toString(), document.data["author"].toString(), document.data["is_booked"].toString(),
                            document.data["date_booked"].toString(), document.data["date_booked_end"].toString(), document.data["image"].toString())
                    )
                    adapter.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { e -> Log.w(TAG_BOOK, "Error adding document", e) }
    }

    fun editBook(db: FirebaseFirestore, datas: ArrayList<Book>, adapter: BookAdapter, konteks: Context, id: String, username: String){
        val idBook = db.collection("books").document(id)
        db. runTransaction { transaction ->
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formatted = current.format(formatter)
            transaction.update(idBook, "is_booked", username)
            transaction.update(idBook, "date_booked", formatted)
            transaction.update(idBook, "date_booked_end", formatted.plus(Calendar.getInstance().get(Calendar.DATE) + 3))
        }.addOnSuccessListener { Log.d(TAG_BOOK, "Buku berhasil dipesan") }
            .addOnFailureListener { e -> Log.d(TAG_BOOK, "Buku gagal dipesan: " + e) }
    }
}