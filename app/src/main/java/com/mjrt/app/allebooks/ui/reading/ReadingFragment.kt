package com.mjrt.app.allebooks.ui.reading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mjrt.app.allebooks.databinding.FragmentReadingBinding

class ReadingFragment : Fragment() {

    private var _binding: FragmentReadingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val readingViewModel =
            ViewModelProvider(this).get(ReadingViewModel::class.java)

        _binding = FragmentReadingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textReading
        readingViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}