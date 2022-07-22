package com.example.interactivetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.interactivetest.controllers.localStorage
import com.example.interactivetest.databinding.ActivityLoginBinding
import com.google.firebase.firestore.FirebaseFirestore
import splitties.activities.start
import splitties.toast.toast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        binding.buttonLogin.setOnClickListener {
            toast("Loading")
            val db = FirebaseFirestore.getInstance().collection("users")
            /*val username = db.collection("users").whereEqualTo("username", binding.textInputUsername1.editText?.text.toString())
            val password = db.collection("users").whereEqualTo("password", binding.textInputPassword1.editText?.text.toString()).get()*/
            db.whereEqualTo("name", binding.textInputUsername1.editText?.text.toString())
                .whereEqualTo("password", binding.textInputPassword1.editText?.text.toString())
                .get().addOnSuccessListener { result ->
                    if(result.documents.size == 0){
                        toast("Username atau password salah")
                        Log.d("TAGLOGIN", "Username atau password salah")
                    }
                    toast("Selamat datang " + (result.documents.get(0).data?.get("name")))
                    Log.d("TAGLOGIN", result.documents.get(0).id + " => " + result.documents.get(0).data?.get("name"))
                    localStorage(result.documents.get(0).data?.get("name").toString(), this).editLocalUser(result.documents.get(0).data?.get("name").toString())
                    finish()
                    start<MainActivity>()
                }
            //db.whereEqualTo("password", binding.textInputPassword1.editText?.text.toString())
            /*db.get()
                .addOnSuccessListener { result ->
                    for(document in result) Log.d("TAGLOGIN", document.id + " => " + document.data)
                }*/
            /*db.document("V5PuHq9Xci4ztIRRAEFe")
                .delete()
                .addOnSuccessListener { Log.d("TAGLOGIN", "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.w("TAGLOGIN", "Error deleting document", e) }*/
        }
    }

    fun initView(){
        if (binding.textInputUsername1.editText?.text.toString().trim { it <= ' '}.isEmpty()) {
            binding.textInputUsername1.editText?.error = "Username masih kosong"
            binding.textInputUsername1.requestFocus()
        }
        else if(binding.textInputPassword1.editText?.text.toString().trim { it <= ' '}.isEmpty()){
            binding.textInputPassword1.editText?.error = "Password masih kosong"
            binding.textInputPassword1.requestFocus()
        }
        if(localStorage("", this).USERNAME != ""){
            start<MainActivity>()
        }
    }
}