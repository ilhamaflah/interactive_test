package com.example.interactivetest.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.interactivetest.LoginActivity
import com.example.interactivetest.controllers.localStorage
import com.example.interactivetest.databinding.FragmentProfileBinding
import splitties.fragments.start

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView(){
        binding.textUsername.text = localStorage("", requireContext()).USERNAME
        binding.menuProfileLogout.setOnClickListener {
            localStorage("", requireContext()).editLocalUser("")
            requireActivity().finish()
            start<LoginActivity>()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}