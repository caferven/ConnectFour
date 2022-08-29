package com.example.connectfour

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.connectfour.databinding.ActivityNewGameBinding
import com.example.connectfour.databinding.DialogWinnerBinding

class NewGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewGameBinding
    private var gameFinished = false
    private var columns: Array<LinearLayout> = arrayOf()
    private var board: Array<Array<ImageView>> = arrayOf()
    private var turn = 0
    private val rows = 6

    companion object {
        var redNumWins = 0
        var purpleNumWins = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReload.setOnClickListener { clearBoard() }
        binding.btnReset.setOnClickListener { resetGame() }

        binding.redWins.setText(redNumWins.toString())
        binding.purpleWins.setText(purpleNumWins.toString())

        setBoard()
        setColumns()
    }

    private fun setBoard() {
        board = arrayOf(
            arrayOf(binding.piece11, binding.piece12, binding.piece13, binding.piece14, binding.piece15, binding.piece16),
            arrayOf(binding.piece21, binding.piece22, binding.piece23, binding.piece24, binding.piece25, binding.piece26),
            arrayOf(binding.piece31, binding.piece32, binding.piece33, binding.piece34, binding.piece35, binding.piece36),
            arrayOf(binding.piece41, binding.piece42, binding.piece43, binding.piece44, binding.piece45, binding.piece46),
            arrayOf(binding.piece51, binding.piece52, binding.piece53, binding.piece54, binding.piece55, binding.piece56),
            arrayOf(binding.piece61, binding.piece62, binding.piece63, binding.piece64, binding.piece65, binding.piece66),
            arrayOf(binding.piece71, binding.piece72, binding.piece73, binding.piece74, binding.piece75, binding.piece76)
        )
    }

    private fun setColumns() {
        columns = arrayOf(
            binding.column1, binding.column2, binding.column3,
            binding.column4, binding.column5, binding.column6, binding.column7
        )
        for (i in columns.indices) {
            columns[i].setOnClickListener {
                for (j in 0 until rows) {
                    if (board[i][j].background == null) {
                        drawPiece(i,j)
                        break
                    } else {
                        continue
                    }
                }
            }
        }
    }

    private fun drawPiece(x: Int, y: Int) {
        when(turn) {
            0 -> board[x][y].setBackgroundResource(R.color.red_piece)
            1 -> board[x][y].setBackgroundResource(R.color.purple_piece)
        }
        checkFinished(x, y)
        changeTurn()
    }

    private fun changeTurn() {
        when(turn) {
            0 -> turn = 1
            1 -> turn = 0
        }
    }

    private fun checkFinished(x: Int, y: Int) {
        //downwards
        if (board[x][y].background != null
            && y-3 >= 0
            && board[x][y].background.constantState == board[x][y-1].background?.constantState
            && board[x][y].background.constantState == board[x][y-2].background?.constantState
            && board[x][y].background.constantState == board[x][y-3].background?.constantState) {

            gameFinished = true
        } else

        //left-to-right
        if (board[x][y].background != null
            && x+3 < columns.size
            && board[x][y].background.constantState == board[x+1][y].background?.constantState
            && board[x][y].background.constantState == board[x+2][y].background?.constantState
            && board[x][y].background.constantState == board[x+3][y].background?.constantState) {

            gameFinished = true
        } else

        //right-to-left
        if (board[x][y].background != null
            && x-3 >= 0
            && board[x][y].background.constantState == board[x-1][y].background?.constantState
            && board[x][y].background.constantState == board[x-2][y].background?.constantState
            && board[x][y].background.constantState == board[x-3][y].background?.constantState) {

            gameFinished = true
        } else

        //to-top-right
        if (board[x][y].background != null
            && x+3 < columns.size && y+3 < rows
            && board[x][y].background.constantState == board[x+1][y+1].background?.constantState
            && board[x][y].background.constantState == board[x+2][y+2].background?.constantState
            && board[x][y].background.constantState == board[x+3][y+3].background?.constantState) {

            gameFinished = true
        } else

        //to-top-left
        if (board[x][y].background != null
            && x-3 >= 0 && y+3 < rows
            && board[x][y].background.constantState == board[x-1][y+1].background?.constantState
            && board[x][y].background.constantState == board[x-2][y+2].background?.constantState
            && board[x][y].background.constantState == board[x-3][y+3].background?.constantState) {

            gameFinished = true
        } else

        //to-bottom-right
        if (board[x][y].background != null
            && x+3 < columns.size && y-3 >= 0
            && board[x][y].background.constantState == board[x+1][y-1].background?.constantState
            && board[x][y].background.constantState == board[x+2][y-2].background?.constantState
            && board[x][y].background.constantState == board[x+3][y-3].background?.constantState) {

            gameFinished = true
        } else

        //to-bottom-left
        if (board[x][y].background != null
            && x-3 >= 0 && y-3 >= 0
            && board[x][y].background.constantState == board[x-1][y-1].background?.constantState
            && board[x][y].background.constantState == board[x-2][y-2].background?.constantState
            && board[x][y].background.constantState == board[x-3][y-3].background?.constantState) {

            gameFinished = true
        }

        if (gameFinished) {
            when (turn) {
                0 -> {
                    redNumWins++
                    binding.redWins.text = (redNumWins).toString()
                }
                1 -> {
                    purpleNumWins++
                    binding.purpleWins.text = (purpleNumWins).toString()
                }
            }
            showWinningMessage()
            Handler().postDelayed({
                clearBoard()
            }, 100)
            changeTurn()
            gameFinished = false
        }
    }

    private fun showWinningMessage() {
        val dialogView = DialogWinnerBinding.inflate(LayoutInflater.from(this), null, false)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView.root)
        val dialog = builder.create()
        when (turn) {
            0 -> dialogView.imgWinner.setImageResource(R.drawable.red_wins)
            1 -> dialogView.imgWinner.setImageResource(R.drawable.purple_wins)
        }
        dialog.show()
        Handler().postDelayed({
            dialog.dismiss()
        }, 2000)
    }

    private fun clearBoard() {
        for (column in board) {
            for (piece in column) {
                piece.setBackgroundDrawable(null)
            }
        }
        changeTurn()
    }

    private fun resetGame() {
        redNumWins = 0
        purpleNumWins = 0
        binding.redWins.text = (redNumWins).toString()
        binding.purpleWins.text = (purpleNumWins).toString()
        clearBoard()
    }
}