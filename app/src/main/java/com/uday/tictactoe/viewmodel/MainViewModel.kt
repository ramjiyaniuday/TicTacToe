package com.uday.tictactoe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uday.tictactoe.MoveEnum
import javax.inject.Inject

class MainViewModel @Inject constructor(): ViewModel() {

    var moveList = MutableLiveData<MutableList<String>>()
    var gameStatus = MutableLiveData<String>()
    var turnTracker = MutableLiveData(MoveEnum.X.name)

    fun startGame() {
        moveList.value = ArrayList()
        gameStatus.value = ""
        var count = 0
        while (count<9){
            moveList.value?.add("")
            count++
        }
        moveList.postValue(moveList.value)
    }

    fun markMove(pos:Int){
        if(moveList.value?.get(pos).isNullOrEmpty()) {
            turnTracker.value?.let {
                moveList.value?.set(pos, it)
                moveList.postValue(moveList.value) }

            checkGameStatus()
            toggleTurn()
        }
    }

    private fun checkGameStatus() {
        if (checkMovesForWin(0,1,2));
        else if (checkMovesForWin(3,4,5));
        else if (checkMovesForWin(6,7,8));
        else if (checkMovesForWin(0,3,6));
        else if (checkMovesForWin(1,4,7));
        else if (checkMovesForWin(2,5,8));
        else if (checkMovesForWin(0,4,8));
        else if (checkMovesForWin(2,4, 6));
        else
            checkGameOver()
    }

    private fun checkGameOver() {
        var count = 0
        while (count<9){
            if(moveList?.value?.get(count).isNullOrEmpty())
                return
            count++
        }
        gameStatus.postValue("Draw")
    }

    private fun checkMovesForWin(pos: Int, pos1: Int, pos2: Int): Boolean {
        if (moveList?.value?.get(pos).isNullOrEmpty()
            || moveList?.value?.get(pos1).isNullOrEmpty()
            || moveList?.value?.get(pos2).isNullOrEmpty() ){
            return false
        }

        if(moveList?.value?.get(pos)?.equals(moveList?.value?.get(pos1),true) == true
            && moveList?.value?.get(pos1)?.equals(moveList?.value?.get(pos2),true) == true) {
            gameStatus.postValue(moveList?.value?.get(pos) + " won the game!")
            return true
        }
        else
            return false
    }

    private fun toggleTurn(){
        if (turnTracker.value.equals(MoveEnum.X.name)){
            turnTracker.postValue(MoveEnum.O.name)
        }else{
            turnTracker.postValue(MoveEnum.X.name)
        }
    }
}