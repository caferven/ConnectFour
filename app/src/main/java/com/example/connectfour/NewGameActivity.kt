package com.example.connectfour

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.connectfour.databinding.ActivityNewGameBinding

class NewGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewGameBinding
    private var gameFinished = false
    private var columns: MutableList<LinearLayout> = mutableListOf()
    private var redWins = false
    private var redNumWins = 0
    private var purpleWins = false
    private var purpleNumWins = 0
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

        binding.btnReload.setOnClickListener { clearBoard() }
        binding.btnReset.setOnClickListener { resetGame() }

        binding.redWins.setText(redNumWins.toString())
        binding.purpleWins.setText(purpleNumWins.toString())

        val columnCount = binding.board.childCount
        var col: View?
        for (i in 0 until columnCount) {
            col = binding.board.getChildAt(i)
            columns.add(col as LinearLayout)
        }
    }

    private fun drawPiece(column: LinearLayout) {
        val pieceCount = column.childCount
        var piece: View?
        for (i in pieceCount-1 downTo 0) {
            piece = column.getChildAt(i)
            if (piece.background != null) {
                continue
            } else {
                when(turn) {
                    0 -> piece.setBackgroundResource(R.color.red_piece)
                    1 -> piece.setBackgroundResource(R.color.purple_piece)
                }
                break
            }
        }
        changeTurn()
        //checkFinished()
    }

    private fun changeTurn() {
        when(turn) {
            0 -> turn = 1
            1 -> turn = 0
        }
    }

    private fun checkFinished() {
        //upwards
        for (column in columns) {
            val pieceCount = column.childCount
            var piece: View?
            for (i in 0 until pieceCount) {
                piece = column.getChildAt(i)
                if (piece.background != null
                    && piece.background == column.getChildAt(i+1).background
                    && piece.background == column.getChildAt(i+2).background
                    && piece.background == column.getChildAt(i+3).background) {
                    val winnerIntColor = (piece.background as ColorDrawable).color
                    val winnerColor = Integer.toHexString(winnerIntColor)
                    when(winnerColor) {
                        "FFFD636E" -> redWins
                        "FF6C7FD8" -> purpleWins
                    }
                    gameFinished = true
                    if (redWins) {
                        redNumWins++
                        binding.redWins.setText((redNumWins).toString())
                    } else {
                        purpleNumWins++
                        binding.purpleWins.setText((purpleNumWins).toString())
                    }
                }
            }
        }
    }

    private fun clearBoard() {
        for (column in columns) {
            val pieceCount = column.childCount
            var piece: View?
            for (i in 0 until pieceCount) {
                piece = column.getChildAt(i)
                piece.setBackgroundDrawable(null)
            }
        }
    }

    private fun resetGame() {
        redNumWins = 0
        purpleNumWins = 0
        binding.redWins.setText((redNumWins).toString())
        binding.purpleWins.setText((purpleNumWins).toString())
        clearBoard()
    }
}