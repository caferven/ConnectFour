package com.example.connectfour

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.connectfour.databinding.ActivityNewGameBinding

class NewGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewGameBinding
    private var gameFinished = false
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

        binding.btnReload.setOnClickListener { cleanBoard() }
        binding.btnReset.setOnClickListener { resetGame() }
    }

    private fun resetGame() {
        cleanBoard()
    }

    private fun cleanBoard() {
        TODO("Not yet implemented")
    }

    private fun drawPiece(column: LinearLayout) {
        val pieceCount = column.childCount
        var v: View?
        for (i in pieceCount-1 downTo 0) {
            v = column.getChildAt(i)
            if (v.background != null) {
                continue
            } else {
                when(turn) {
                    0 -> v.setBackgroundResource(R.color.red_piece)
                    1 -> v.setBackgroundResource(R.color.purple_piece)
                }
                break
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