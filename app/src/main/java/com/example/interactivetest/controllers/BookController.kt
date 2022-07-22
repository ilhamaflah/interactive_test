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
    private val TAG_HISTORY = "TAGHISTORY"

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
            .addOnFailureListener { e -> Log.w(TAG_HISTORY, "Error adding document", e) }
    }

    fun editBook(db: FirebaseFirestore, datas: ArrayList<Book>, adapter: BookAdapter, konteks: Context, id: String, username: String, bookName: String, author: String, is_booked: String, date_booked: String, date_booked_end: String, image: String, position: Int){
        val idBook = db.collection("books").document(id)

        db.collection("history").whereEqualTo("name", bookName).get()
            .addOnSuccessListener { result ->
                if(result.documents.size == 0){
                    val history = hashMapOf(
                        "name" to bookName,
                        "author" to is_booked,
                        "is_booked" to is_booked,
                        "date_booked" to date_booked,
                        "date_booked_end" to date_booked_end,
                        "image" to image
                    )
                    db.collection("history")
                        .add(history)
                        .addOnSuccessListener {
                            Log.d(TAG_HISTORY, "History successfully added")
                            datas[position] = Book(id, bookName, author, is_booked, date_booked, date_booked_end, image)
                            adapter.notifyDataSetChanged()
                        }
                        .addOnFailureListener { e -> Log.w(TAG_HISTORY, "Error adding document", e) }
                }
            }
        db.runTransaction { transaction ->
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formatted = current.format(formatter)
            transaction.update(idBook, "is_booked", username)
            transaction.update(idBook, "date_booked", formatted)
            transaction.update(idBook, "date_booked_end", formatted.plus(Calendar.getInstance().get(Calendar.DATE) + 3))
        }.addOnSuccessListener { Log.d(TAG_BOOK, "Book successfully borrowed") }
            .addOnFailureListener { e -> Log.d(TAG_BOOK, "Error borrowing book: " + e) }
    }
}