package com.toreskotech.pontinho

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var nomeJogador1: EditText
    private lateinit var nomeJogador2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_main)

        start.setOnClickListener {
            val intent = Intent(this, Game::class.java)

            nomeJogador1 = findViewById(R.id.PlayerDig1a)
            println("nome player $nomeJogador1")

            nomeJogador2 = findViewById(R.id.PlayerDig2a)
            println("nome player $nomeJogador2")


            intent.putExtra("nome", "${nomeJogador1.text}")
            intent.putExtra("nome2", "${nomeJogador2.text}")

            if (valida() == false) {
                startActivity(intent)
            }

        }


    }


    private fun valida(): Boolean {
        var errot = Toast.makeText(this, "Digite o nome dos players", Toast.LENGTH_SHORT)
        var erro = false

        when {
            nomeJogador1.text.isEmpty() && nomeJogador2.text.isEmpty() -> {
                errot.show()
                erro = true
            }
            nomeJogador1.text.isEmpty() -> {
                errot = Toast.makeText(this, "Digite o nome do player 1!", Toast.LENGTH_SHORT)
                errot.show()
                erro = true
            }
            nomeJogador2.text.isEmpty() -> {
                errot = Toast.makeText(this, "Digite o nome do player 2!", Toast.LENGTH_SHORT)
                errot.show()
                erro = true
            }
        }
        return erro

    }


    override fun onBackPressed() {
        // não chame o super desse método
        var voltar = 0
        voltar++
        when {
            voltar == 2 -> Toast.makeText(
                this,
                "Se aperta mais uma vez, o app encerra.",
                Toast.LENGTH_SHORT
            ).show()
            voltar == 3 -> finish()
        }



    }


}









