package com.toreskotech.pontinho

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_game_over.*


class GameOver : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_game_over)


        val dados: Bundle? = intent.extras
        val nome1 = dados?.getString("vencedor")
        val nome2 = dados?.getString("player1")
        val nome3 = dados?.getString("player2")

        val rodadas = dados?.getString("rodadas")
        val resultp1 = dados?.getString("result1")
        val resultp2 = dados?.getString("result2")


        venc.text = nome1
        p1nome.text = nome2
        p2nome.text = nome3



        resP1.text = resultp1?.replace(",","")?.replace("[","")?.replace("]","")?.replace(" ","\n")
        resP2.text = resultp2?.replace(",","")?.replace("[","")?.replace("]","")?.replace(" ","\n")
        rodadass.text = rodadas


        compG = nome1.toString()
        comp1.addAll(listOf(arrayListOf(resultp1).toString()))
        compN1 = nome2.toString()
        comp2.addAll(listOf(arrayListOf(resultp2).toString()))
        compN2 = nome3.toString()
        compR = rodadas.toString()


        bVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        comp.setOnClickListener {

            val shareTask = comp.text.toString()
            val dialog = AlertDialog.Builder(this).setTitle("Otima partida").setMessage("Você deseja compartilhar?")
                .setPositiveButton("Sim") { dialog, _ ->
                    setShareIntent(shareTask(shareTask))
                    dialog.dismiss()
                }
                .setNegativeButton("Não") { dialog, _ ->
                    dialog.dismiss()
                }
            dialog.show()
        }
    }
    private fun setShareIntent(shareBody: String){
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(sharingIntent, "Resultado da Partida"))
    }
    fun shareTask(str: String): String {
        val resp = ("GANHADOR DA PARTIDA: ${compG.uppercase()}\nEM $compR RODADAS\n\n" +
                "${compN1.uppercase()}\n" +
                "${comp1}\n\n" +
                "${compN2.uppercase()}\n" +
                "${comp2}")
        return resp
    }



    override fun onBackPressed() {
        // não chame o super desse método
    }

    var comp1 = mutableListOf<String>()
    var compN1 = ""
    var comp2 = mutableListOf<String>()
    var compN2 = ""
    var compR = ""
    var compG = ""
}





