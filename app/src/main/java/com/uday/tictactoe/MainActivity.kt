package com.uday.tictactoe

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.uday.tictactoe.databinding.ActivityMainBinding
import com.uday.tictactoe.viewmodel.MainViewModel
import com.uday.tictactoe.viewmodel.MyViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var adapter: BoardAdapter
    companion object{
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

       // viewModel = ViewModelProvider(this,MyViewModelFactory()).get(MainViewModel::class.java)

        binding.content.board.layoutManager = GridLayoutManager(this,3)
        adapter = BoardAdapter(::playerMove)
        binding.content.board.adapter = adapter

        viewModel.startGame()
        initObservers()
    }

    fun playerMove(pos:Int){
        viewModel.markMove(pos)
    }

    fun initObservers(){
        viewModel.gameStatus.observe(this,{
            Log.d(TAG, "initVM: gameStatus:"+it)
            if(it.isNullOrEmpty())
                return@observe
            binding.content.turnTv.setText(it)
            showAlerDialog(it)
        })

        viewModel.moveList.observe(this,{
            Log.d(TAG, "initVM: list change"+it.toString())
            adapter.moveList.clear()
            adapter.moveList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        viewModel.turnTracker.observe(this,{
            binding.content.turnTv.setText(it+"'s Turn")
        })
    }

    private fun showAlerDialog(message:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Game Over")
        builder.setMessage(message)
       // builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Ok"){dialogInterface, which ->
           viewModel.startGame()
        }
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}