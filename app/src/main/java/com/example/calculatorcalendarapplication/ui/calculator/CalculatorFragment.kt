package com.example.calculatorcalendarapplication.ui.calculator

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculatorcalendarapplication.databinding.FragmentCalculatorBinding
import java.util.LinkedList

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.apply({
            button.setOnClickListener({
                try{
                    editText.setText(calculate(editText.text.toString()))
                }
                catch(e : Exception) {

                }
            })
        })

        return root
    }

    fun calculate(s: String): String {
        var acc = 0
        var i = 0

        val signStack = LinkedList<Int>()
        var parenSigns = 1
        var prevSign = 1

        while (i < s.length) {
            val c = s[i]
            when(c) {
                ' ' -> {}
                '(' -> {
                    signStack.push(prevSign)
                    if (prevSign < 0) parenSigns *= -1
                    prevSign = 1
                }
                ')' -> if (signStack.pop() < 0) parenSigns *= -1
                '+' ->  prevSign = 1
                '-' -> prevSign = -1
                else -> { // parse whole number
                    var n = 0
                    while(i < s.length && '0' <= s[i] && s[i] <= '9') {
                        n = n * 10 + (s[i] - '0')
                        i++
                    }
                    i--
                    acc += n * parenSigns * prevSign
                }
            }
            i++
        }

        return acc.toString()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)
        // TODO: Use the ViewModel
    }
}