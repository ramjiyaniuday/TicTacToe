package com.uday.tictactoe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uday.tictactoe.databinding.BoardItemLayoutBinding

class BoardAdapter(val clickAction: (Int) -> Unit) : RecyclerView.Adapter<BoardAdapter.DataViewHolder>(){

    var moveList = mutableListOf<String>()

    class DataViewHolder(val binding: BoardItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(move: String, position: Int, clickAction: (Int) -> Unit) {
            binding.board.setText(move)
            binding.board.setOnClickListener {
                clickAction.invoke(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BoardItemLayoutBinding.inflate(inflater,parent,false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bindData(moveList.get(position), position, clickAction)
    }

    override fun getItemCount(): Int {
        return moveList.count()
    }

  /*  fun startGame(): LiveData<String> {
        var count = 0
        while (count++<9){
            moveList.add("")
        }

        return gameStatus
    }

    fun markMove(pos:Int, move:MoveEnum){
        if(moveList.get(pos).isNullOrEmpty())
            moveList.set(pos,move.name)

        checkGameStatus()
    }


    private fun checkGameStatus() {
        if (checkMovesForWin(0,1,2));
        else if (checkMovesForWin(0,3,8));
        else if (checkMovesForWin(2,4, 6));
        else
            checkGameOver()
    }

    private fun checkGameOver() {
        var count = 0
        while (count++<9){
            if(moveList[count].isNullOrEmpty())
                return
        }
        gameStatus.postValue("Draw")
    }

    private fun checkMovesForWin(pos: Int, pos1: Int, pos2: Int): Boolean {
        if(moveList.get(pos).equals(moveList.get(pos1).equals(moveList.get(pos2)))) {
            gameStatus.postValue(moveList.get(pos) + " won!")
            return true
        }
        else
            return false
    }*/
}