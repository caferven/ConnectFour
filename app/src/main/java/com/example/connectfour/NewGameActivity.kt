package com.example.connectfour

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.get
import androidx.core.view.size
import com.example.connectfour.databinding.ActivityNewGameBinding

class NewGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewGameBinding
    private var turn = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.column1.setOnClickListener { drawPiece(binding.column1) }
        binding.column2.setOnClickListener { drawPiece(binding.column2) }
        binding.column3.setOnClickListener { drawPiece(binding.column3) }
        binding.column4.setOnClickListener { drawPiece(binding.column4) }
        binding.column5.setOnClickListener { drawPiece(binding.column5) }
        binding.column6.setOnClickListener { drawPiece(binding.column6) }
        binding.column7.setOnClickListener { drawPiece(binding.column7) }
    }

    private fun drawPiece(column: LinearLayout) {
        val pieceCount = column.childCount
        var v: View?
        for (i in 0 until pieceCount) {
            v = column.getChildAt(i)
            if (v.background != null) {
                continue
            } else {
                when(turn) {
                    0 -> v.setBackgroundColor(Color.RED)
                    1 -> v.setBackgroundColor(Color.YELLOW)
                }
            }
        }
        changeTurn()
    }

    private fun changeTurn() {
        when(turn) {
            0 -> turn = 1
            1 -> turn = 0
        }
    }
}