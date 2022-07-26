package com.example.interactivetest.ui.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interactivetest.adapters.BookAdapter
import com.example.interactivetest.controllers.BookController
import com.example.interactivetest.controllers.localStorage
import com.example.interactivetest.databinding.FragmentBookBinding
import com.example.interactivetest.models.Book
import com.google.firebase.firestore.FirebaseFirestore
import splitties.toast.toast
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class BookFragment : Fragment(), BookAdapter.OnBookClickListener {

    private var _binding: FragmentBookBinding? = null
    private lateinit var books: ArrayList<Book>
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: BookAdapter
    private val TAG_BOOK = "TAGBOOK"
    private val db = FirebaseFirestore.getInstance()
    private var globalPosition: Int? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBookBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        loadData()
    }

    private fun initView(){
        books = arrayListOf()
        adapter = BookAdapter(requireContext(), books, this)
        layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
//        layoutManager = LinearLayoutManager(requireContext())
        binding.dataRV.adapter = adapter
        binding.dataRV.layoutManager = layoutManager

        /*var books = hashMapOf(
            "name" to "The Book",
            "author" to "tester2",
            "is_booked" to "tester2",
            "date_booked" to "2022-07-21",
            "date_booked_end" to "2022-07-24",
            "image" to "https://upload.wikimedia.org/wikipedia/commons/9/92/THE_BOOK_cover_image.png"
        )
        db.collection("books")
            .add(books)
            .addOnSuccessListener { Log.d(TAG_BOOK, "Book successfully added")
            }
            .addOnFailureListener { e -> Log.w(TAG_BOOK, "Error adding document", e) }*/

        /*db.collection("history")
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    Log.d(TAG_BOOK, "${document.id} => ${document.data}")
                    adapter.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { e -> Log.w(TAG_BOOK, "Error adding document", e) }*/
        /*db.collection("books").document("z66lwrB8M49aKt8Cn7ot")
            .delete()
            .addOnSuccessListener {  }
            .addOnFailureListener {  }*/
        BookController().editBookBorrowEnd(FirebaseFirestore.getInstance())
    }

    private fun loadData(){
        BookController().listBook(db, books, adapter, requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCardBookClickListener(position: Int) {
        //globalPosition = position
        toast(books[position].name)
    }

    override fun onButtonBorrowClickListener(position: Int) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, 3)
        val dateAfter = sdf.format(cal.time)
        BookController().editBook(db, books, adapter, requireContext(), books[position].id, localStorage("", requireContext()).USERNAME.toString(), books[position].name,
            books[position].author, localStorage("", requireContext()).USERNAME.toString(), formatted, dateAfter, books[position].image, position)
    }
}