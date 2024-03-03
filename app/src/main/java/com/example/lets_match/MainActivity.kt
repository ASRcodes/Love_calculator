package com.example.lets_match

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.Random

class MainActivity : AppCompatActivity() {
    lateinit var his: EditText
    lateinit var my: EditText

    lateinit var button: Button
     private var  match :Int = 0
    private lateinit var yo :String
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var soundeffect1:MediaPlayer
    private lateinit var soundeffect2:MediaPlayer
    private lateinit var soundeffect3:MediaPlayer
    private lateinit var soundeffect4:MediaPlayer
    private lateinit var soundeffect5:MediaPlayer
    private lateinit var soundeffect6:MediaPlayer
    lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.wiii)
        mediaPlayer.setVolume(0.5f,0.5f)
        mediaPlayer.isLooping = true // Set it to repeat indefinitely
        mediaPlayer.start()

        soundeffect1 = MediaPlayer.create(this@MainActivity,R.raw.boi)
        soundeffect2 = MediaPlayer.create(this,R.raw.damage)
        soundeffect3 = MediaPlayer.create(this,R.raw.omg)
        soundeffect4 = MediaPlayer.create(this,R.raw.haahahah)
        soundeffect5 = MediaPlayer.create(this,R.raw.amazing)
        soundeffect6 = MediaPlayer.create(this,R.raw.kya)



        his =findViewById(R.id.his)
        my = findViewById(R.id.my)

        button = findViewById(R.id.button)
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                check()
            }
        })
        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            sendEmailFeedback()
        }


    }
//    private fun sendEmailFeedback(feedback: String) {
//        val emailIntent = Intent(Intent.ACTION_SEND)
//        emailIntent.type = "message/rfc822"
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("rajsinghrajput7002@gmail.com"))
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from App")
//        emailIntent.putExtra(Intent.EXTRA_TEXT, feedback)
//        if (emailIntent.resolveActivity(packageManager) != null) {
//            startActivity(Intent.createChooser(emailIntent, "Send Email"))
//        }
//    }
private fun sendEmailFeedback() {
    val emailIntent = Intent(Intent.ACTION_SENDTO)
    emailIntent.data = Uri.parse("mailto:rajsinghrajput7002@gmail.com")
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from App")

    if (emailIntent.resolveActivity(packageManager) != null) {
        startActivity(emailIntent)
    }
}
    private fun check (){
        val random = Random()
        match = random.nextInt(100)+1
        if (my.text.toString().trim().isNotEmpty() && his.text.toString().trim().isNotEmpty()) {
            if (match > 90) {
                yo = ("nice is was "+ match+"%")
                soundeffect3.start()
            } else if (match > 70) {
                 yo = "nice it was "+match+"%"
                soundeffect1.start()
            }
            else if (match<30 &&match >10) {
                yo = "heheh "+match+"%"
                soundeffect4.start()
            }
            else if (match<10 &&match > 1) {
                yo = "O no "+match+"%"
                soundeffect2.start()
            }
            else {
                yo = "not interesting "+match+"%"
                soundeffect5.start()

            }
            Toast.makeText(this@MainActivity,yo, Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Please fill the Name first", Toast.LENGTH_SHORT).show()
            soundeffect6.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        soundeffect1.release()
        soundeffect2.release()
        soundeffect3.release()
        soundeffect4.release()
        soundeffect5.release()
        soundeffect6.release()
    }

}
