package com.example.firstapplication

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    val kgToPound = 2.2045
    val poundToKg = 0.45359237
    val onMars = 0.38
    val onVenus = 0.91
    val onJupiter = 2.34


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Glide.with(this).load(R.drawable.planets).into(planets)

        resultText.text = savedInstanceState?.getString("result")

        checkBoxMars.setOnClickListener(this)
        checkBoxVenus.setOnClickListener(this)
        checkBoxJupiter.setOnClickListener(this)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("result",resultText.text.toString())

    }

    fun kgToPound(kg:Double):Double{
        return kg * kgToPound
    }

    fun poundToKg(pound:Double):Double{
        return pound * poundToKg
    }

    fun Double.format(howManyNumber : Int) = String.format("%.${howManyNumber}f",this)

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        v as CheckBox
        var isChecked : Boolean = v.isChecked
        if(enteredKg.text.toString().isNotEmpty()){
            var inputKG = enteredKg.text.toString().toDouble()
            var convertedPound = kgToPound(inputKG)
            when(v.id){
            R.id.checkBoxMars -> if(isChecked){
                checkBoxVenus.isChecked = false
                checkBoxJupiter.isChecked = false
                calculateInPound(convertedPound,v)
            }
            R.id.checkBoxVenus ->if(isChecked){
                checkBoxMars.isChecked = false
                checkBoxJupiter.isChecked = false
                calculateInPound(convertedPound,v)
            }
            R.id.checkBoxJupiter ->if(isChecked){
                checkBoxMars.isChecked = false
                checkBoxVenus.isChecked = false
                calculateInPound(convertedPound,v)
            }
        }}
    }

    fun calculateInPound(pound : Double,cb : CheckBox){
        var result : Double =  0.0
        when(cb.id){
            R.id.checkBoxMars -> result = pound * onMars
            R.id.checkBoxVenus -> result = pound * onVenus
            R.id.checkBoxJupiter -> result = pound * onJupiter
            else -> result = 0.0
        }

        var resultAsKg  = poundToKg(result)
        resultText.text = resultAsKg.format(2)

    }
}