package com.toreskotech.pontinho

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*

class Game : AppCompatActivity() {

       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_game)


        val dados: Bundle? = intent.extras
        println("passou pontos string")
        val nome1 = dados?.getString("nome")
        exibir.add(nome1.toString())
        val nome2 = dados?.getString("nome2")
        exibir.add(nome2.toString())
        nomeP1.text = exibir[0]
        nomeP2.text = exibir[1]
        cortename.text = exibir[numero]


        contar.setOnClickListener {
            when {
                digPontosP1.text.isNullOrEmpty() && digPontosP2.text.isNullOrEmpty() -> {
                    var err = Toast.makeText(
                        this,
                        "Digite o valor de pontos perdidos",
                        Toast.LENGTH_SHORT
                    )
                    err.show()
                }
                !digPontosP1.text.isNullOrEmpty() && !digPontosP2.text.isNullOrEmpty() -> {
                    var err = Toast.makeText(
                        this,
                        "Digite apenas quem perdeu os pontos!",
                        Toast.LENGTH_SHORT
                    )
                    err.show()
                }
                digPontosP1.text.isNullOrEmpty() -> {
                    corte()
                    digPontosP1.setText("0")
                    debitar()
                }
                digPontosP2.text.isNullOrEmpty() -> {
                    corte()
                    digPontosP2.setText("0")
                    debitar()
                }
            }
            cores()
            vencendor()

        }

    }


    var exibir = arrayListOf<String>()
    var part = arrayListOf<Int>()
    var pointsMatchUpP1 = arrayListOf<Int>()
    var pointsMatchUpP2 = arrayListOf<Int>()
    var numero = 0
    var contador1 = 100
    var contador2 = 100


    fun vencendor() {

        val intent = Intent(this, GameOver::class.java)
        var win1 = nomeP1.text.toString()
        var win2 = nomeP2.text.toString()
        var partidas = part.size
        var jogo1 = pointsMatchUpP1

        intent.putExtra("player1", "$win1")
        intent.putExtra("player2", "$win2")

        intent.putExtra("rodadas", "$partidas")
        intent.putExtra("result1", "$pointsMatchUpP1")
        intent.putExtra("result2", "$pointsMatchUpP2")

        if (contador2 < 0) {
            //passar dados
            intent.putExtra("vencedor", "$win1")
            startActivity(intent)
        }
        if (contador1 < 0) {
            //passar dados
            intent.putExtra("vencedor", "$win2")
            startActivity(intent)
        }



    }

    fun cores(){
        // cores player1
        if (contador1 <= 50 && contador1 > 30) pontosP1.setTextColor(Color.parseColor("#FFE600"))
        if (contador1 <= 30 && contador1 < 50) pontosP1.setTextColor(Color.parseColor("#DF0101"))
        // cores player2
        if (contador2 <= 50 && contador2 > 30) pontosP2.setTextColor(Color.parseColor("#FFE600"))
        if (contador2 <= 30 && contador2 < 50) pontosP2.setTextColor(Color.parseColor("#DF0101"))

    }

    fun debitar() {

        var count = pontosP1
        var count2 = pontosP2
        var digpontos1 = digPontosP1.text.toString().toInt()
        var digpontos2 = digPontosP2.text.toString().toInt()

        count.text = contador1.toString()
        count2.text = contador2.toString()


        contador1 -= digpontos1
        count.text = contador1.toString()
        contador2 -= digpontos2
        count2.text = contador2.toString()

        digPontosP1.setText("")
        digPontosP2.setText("")

        points()

    }

    fun corte(){

        var erro = false

        if (digPontosP1.text.toString() == "0" || digPontosP2.text.toString() == "0" ){
            var erro1 = Toast.makeText(
                this,
                "Digite o valor de pontos perdidos",
                Toast.LENGTH_SHORT
            )
            erro1.show()


        } else {


            if (erro == false) {
                exibir.add(exibir[0])
                exibir.add(exibir[1])
                part.add(0)

                numero++

                cortename.text = exibir[numero]

            }
        }
    }

    fun points(){
        pointsMatchUpP1.add(contador1)
        pointsMatchUpP2.add(contador2)

    }
}

