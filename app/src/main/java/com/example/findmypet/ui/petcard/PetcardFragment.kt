package com.example.findmypet.ui.petcard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.findmypet.databinding.FragmentPetcardBinding

class PetcardFragment : Fragment() {

    private var _binding: FragmentPetcardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val petcardViewModel =
            ViewModelProvider(this).get(PetcardViewModel::class.java)

        _binding = FragmentPetcardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPetcard
        petcardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}