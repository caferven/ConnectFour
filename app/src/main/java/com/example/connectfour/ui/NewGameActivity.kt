package com.example.connectfour.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.connectfour.R
import com.example.connectfour.ui.StatsActivity.Companion.purpleStatsWins
import com.example.connectfour.ui.StatsActivity.Companion.redStatsWins
import com.example.connectfour.databinding.ActivityNewGameBinding
import com.example.connectfour.databinding.DialogWinnerBinding

class NewGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewGameBinding
    private var gameFinished = false
    private var columns: Array<LinearLayout> = arrayOf()
    private var board: Array<Array<ImageView>> = arrayOf()
    private var turn = 0
    private val rows = 6
    private var redNumWins = 0
    private var purpleNumWins = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

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
         if (checkDownwards(x,y) || checkHorizontal(x,y) || checkDiagonal(x,y)) gameFinished = true

        if (gameFinished) {
            when (turn) {
                0 -> {
                    redNumWins++
                    redStatsWins++
                    binding.redWins.text = (redNumWins).toString()
                }
                1 -> {
                    purpleNumWins++
                    purpleStatsWins++
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

    private fun checkDownwards(x: Int, y: Int): Boolean {
        if (y-3 >= 0
            && board[x][y].background.constantState == board[x][y-1].background?.constantState
            && board[x][y].background.constantState == board[x][y-2].background?.constantState
            && board[x][y].background.constantState == board[x][y-3].background?.constantState) {
            return true
        }
        return false
    }

    private fun checkHorizontal(x: Int, y: Int): Boolean {
        var painted = 0

        //left-to-right
        if (x+1 < columns.size && board[x][y].background.constantState == board[x+1][y].background?.constantState) {
            painted++
            if (x+2 < columns.size && board[x][y].background.constantState == board[x+2][y].background?.constantState) {
                painted++
                if (x+3 < columns.size && board[x][y].background.constantState == board[x+3][y].background?.constantState) {
                    painted++
                }
            }
        }
        //right-to-left
        if (x-1 >= 0 && board[x][y].background.constantState == board[x-1][y].background?.constantState) {
            painted++
            if (x-2 >= 0 && board[x][y].background.constantState == board[x-2][y].background?.constantState) {
                painted++
                if (x-3 >= 0 && board[x][y].background.constantState == board[x-3][y].background?.constantState) {
                    painted++
                }
            }
        }
        if (painted > 2) {
            return true
        }
        return false
    }

    private fun checkDiagonal(x: Int, y: Int): Boolean {
        var paintedTop = 0
        var paintedBottom = 0

        //to-top-right
        if (x+1 < columns.size && y+1 < rows && board[x][y].background.constantState == board[x+1][y+1].background?.constantState) {
            paintedTop++
            if (x+2 < columns.size && y+2 < rows && board[x][y].background.constantState == board[x+2][y+2].background?.constantState) {
                paintedTop++
                if (x+3 < columns.size && y+3 < rows && board[x][y].background.constantState == board[x+3][y+3].background?.constantState) {
                    paintedTop++
                }
            }
        }
        //to-top-left
        if (x-1 >= 0 && y+1 < rows && board[x][y].background.constantState == board[x-1][y+1].background?.constantState) {
            paintedTop++
            if (x-2 >= 0 && y+2 < rows && board[x][y].background.constantState == board[x-2][y+2].background?.constantState) {
                paintedTop++
                if (x-3 >= 0 && y+3 < rows && board[x][y].background.constantState == board[x-3][y+3].background?.constantState) {
                    paintedTop++
                }
            }
        }
        //to-bottom-right
        if (x+1 < columns.size && y-1 >= 0 && board[x][y].background.constantState == board[x+1][y-1].background?.constantState) {
            paintedBottom++
            if (x+2 < columns.size && y-2 >= 0 && board[x][y].background.constantState == board[x+2][y-2].background?.constantState) {
                paintedBottom++
                if (x+3 < columns.size && y-3 >= 0 && board[x][y].background.constantState == board[x+3][y-3].background?.constantState) {
                    paintedBottom++
                }
            }
        }
        //to-bottom-left
        if (x-1 >= 0 && y-1 >= 0 && board[x][y].background.constantState == board[x-1][y-1].background?.constantState) {
            paintedBottom++
            if (x-2 >= 0 && y-2 >= 0 && board[x][y].background.constantState == board[x-2][y-2].background?.constantState) {
                paintedBottom++
                if (x-3 >= 0 && y-3 >= 0 && board[x][y].background.constantState == board[x-3][y-3].background?.constantState) {
                    paintedBottom++
                }
            }
        }
        if (paintedTop > 2 || paintedBottom > 2) {
            return true
        }
        return false
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