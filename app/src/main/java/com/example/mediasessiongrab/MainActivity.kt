package com.example.mediasessiongrab

import android.R
import android.content.ComponentName
import android.graphics.drawable.Drawable
import android.media.session.MediaController
import android.media.session.MediaSessionManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.mediasessiongrab.R.layout.activity_main)
        val mngr = this.getSystemService(MEDIA_SESSION_SERVICE) as MediaSessionManager
        val mmlist = mngr.getActiveSessions(
            ComponentName(
                this,
                NotificationListener::class.java
            )
        )
       val paks = packageManager.getInstalledPackages(0)
        for(pak in paks)
        {
            Log.i("", pak.packageName)
        }
        val chips: MutableList<Chip> = mutableListOf()

        for(session in mmlist)
        {

            val chip = Chip(this)
            val icon: Drawable = packageManager.getApplicationIcon(session.packageName)

            val txt = session.packageName
            chip.text = txt
            chip.chipIcon = icon
            chips.add(chip)
            }



        val speeen = findViewById<Spinner>(com.example.mediasessiongrab.R.id.mediasessionlist)
        val inputlist: ArrayAdapter<Chip> = ArrayAdapter<Chip>(this, R.layout.simple_spinner_item, chips)
        inputlist.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        speeen.adapter = inputlist


        Log.i("", Mc_to_Str(mmlist))
    }
    private fun Mc_to_Str(mm_list: List<MediaController>): String {
        val new_str = StringBuilder()
        for (i in mm_list) {
            new_str.append(
                """
                ${i.packageName}
                
                """.trimIndent()
            )
        }
        return new_str.toString()
    }
}