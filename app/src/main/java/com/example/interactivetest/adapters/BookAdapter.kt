package com.example.interactivetest.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.interactivetest.controllers.BookController
import com.example.interactivetest.controllers.localStorage
import com.example.interactivetest.databinding.MainItemBinding
import com.example.interactivetest.models.Book
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import splitties.toast.toast
import java.io.InputStream
import java.net.URL
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class BookAdapter(val konteks: Context, val data: ArrayList<Book>, val listener: OnBookClickListener) : RecyclerView.Adapter<BookAdapter.BookHolder>() {

    interface OnBookClickListener {
        fun onCardBookClickListener(position: Int)
        fun onButtonBorrowClickListener(position: Int)
    }

    inner class BookHolder(private val binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val db = FirebaseFirestore.getInstance()
        init {
            binding.cardBook.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onCardBookClickListener(absoluteAdapterPosition)
        }

        fun bind(book: Book) {
            var localDate: LocalDate? = null
            if(book.date_booked.isNotBlank()) {
                localDate = LocalDate.parse(book.date_booked, DateTimeFormatter.ISO_DATE)
                binding.borrowerLayout.visibility = View.VISIBLE
                binding.borrower.text = book.is_booked
                binding.dateBooked.text = "Booked: $localDate"
            }
            Picasso.get().load("{${book.image}}").into(binding.imageViewMain1)
            //DownloadImage(binding.imageViewMain1).execute(data.image)
            binding.title1.text = book.name
            binding.author.text = "Author: " + book.author
            if(book.is_booked.isNotBlank()) binding.buttonBook.isEnabled = false

            binding.buttonBook.setOnClickListener {
                listener.onButtonBorrowClickListener(absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val binding = MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BookHolder(binding)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount(): Int = data.size

    /*private class DownloadImage(imageView: ImageView) : AsyncTask<String, Void, Bitmap>() {
        private var imageView = imageView

        override fun doInBackground(vararg params: String?): Bitmap {
            val urlQ = params[0]
            //var icon1: Bitmap? = null
            var input: InputStream? = null
            try {
                input = URL(urlQ).openStream() as InputStream
            } catch (e: Exception) {
                Log.e("ErrorBitmap", e.message!!)
                e.printStackTrace()
            }
            return BitmapFactory.decodeStream(input)
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            imageView.setImageBitmap(result)
        }
    }*/
}