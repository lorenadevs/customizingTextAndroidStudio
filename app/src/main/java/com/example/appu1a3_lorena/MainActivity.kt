package com.example.appu1a3_lorena

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.ToggleButton
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {
    private lateinit var inputWord : TextView
    private lateinit var inputWordAux : String

    private lateinit var rdBColorGreen : RadioButton
    private lateinit var rdBColorPink : RadioButton
    private lateinit var rdBColorRed : RadioButton
    private lateinit var rdBColorCyan : RadioButton

    private lateinit var rdBSize12 : RadioButton
    private lateinit var rdBSize16 : RadioButton
    private lateinit var rdBSize22 : RadioButton
    private lateinit var rdBSize26 : RadioButton

    private lateinit var chBBold : CheckBox
    private lateinit var chBItalic : CheckBox

    private lateinit var tgRealTimeDisplay : ToggleButton
    private lateinit var swUpperLower : Switch

    private lateinit var rdGColors : RadioGroup
    private lateinit var rdGSizes : RadioGroup
    private lateinit var rdGStyles : RadioGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start()
    }

    fun start(){

        inputWord = findViewById(R.id.inputWord)


        rdGColors = findViewById(R.id.rdGColors)
        rdGSizes = findViewById(R.id.rdGSizes)
        rdGStyles = findViewById(R.id.rdGStyles)

        rdBColorGreen = findViewById(R.id.rdBColor1)
        rdBColorPink = findViewById(R.id.rdBColor2)
        rdBColorRed = findViewById(R.id.rdBColor3)
        rdBColorCyan = findViewById(R.id.rdBColor4)

        rdBSize12 = findViewById(R.id.rdBSize1)
        rdBSize16 = findViewById(R.id.rdBSize2)
        rdBSize22 = findViewById(R.id.rdBSize3)
        rdBSize26 = findViewById(R.id.rdBSize4)

        chBBold = findViewById(R.id.chBBold)
        chBItalic = findViewById(R.id.chBItalic)

        swUpperLower = findViewById(R.id.swUpperLower)
        tgRealTimeDisplay = findViewById(R.id.tgBRealTime)

        //adding checkedChangeListeners to each group
        rdGColors.setOnCheckedChangeListener { group, checkedId ->
            selectStyles()
        }

        rdGSizes.setOnCheckedChangeListener { group, checkedId ->
            selectStyles()
        }

        chBItalic.setOnCheckedChangeListener{ group, checkedId ->
            selectStyles()
        }

        chBBold.setOnCheckedChangeListener{ group, checkedId ->
            selectStyles()
        }

        swUpperLower.setOnCheckedChangeListener{ buttonView, isChecked -> //even if we don't need this params, we need them to don't get a type mismatch exception
            selectStyles()
        }

        tgRealTimeDisplay.setOnCheckedChangeListener{buttonView, isChecked ->
            selectStyles()
        }


    }

    fun selectStyles() {
        inputWordAux = inputWord.text.toString()

        //declared here to make it accessible also from the "else" statement
        var textStyle: Int = 0

        if (tgRealTimeDisplay.isChecked) { //only if the toggle button is in ON state
            var selectedColor : Int
            //Log.e("Asd", "entró fun")

            if(rdBColorGreen.isChecked){
                //Log.e("Asd", "entró if")
                selectedColor = Color.GREEN
            }else if(rdBColorPink.isChecked){
                selectedColor = Color.MAGENTA
            }else if(rdBColorRed.isChecked){
                selectedColor = Color.RED
            }else if(rdBColorCyan.isChecked){
                selectedColor = Color.CYAN
            }else{
                selectedColor = Color.WHITE
            }
            inputWord.setTextColor(selectedColor)

            var fontSize : Float
            if(rdBSize12.isChecked){
                fontSize = 30f
            }else if(rdBSize16.isChecked){
                fontSize = 34f
            }else if(rdBSize26.isChecked){
                fontSize = 44f
            }else{
                fontSize = 38f
            }
            inputWord.textSize = fontSize

            if(swUpperLower.isChecked){
                inputWord.text = inputWord.text.toString().uppercase()
            }else{
                inputWord.text = inputWord.text.toString().lowercase()
            }

            if ((chBBold.isChecked) && (chBItalic.isChecked)) {
                textStyle = Typeface.BOLD_ITALIC
            } else if (chBItalic.isChecked) {
                textStyle = Typeface.ITALIC
            } else if (chBBold.isChecked) {
                textStyle = Typeface.BOLD
            } else {
                textStyle = Typeface.NORMAL
            }
        }else{
            inputWord.text = inputWordAux
            inputWord.textSize = 38f
            inputWord.setTypeface(null, Typeface.NORMAL)
            inputWord.setTextColor(Color.WHITE)
        }
        //these 2 values at the end or we'll see the default arial font
        val typeface2 = ResourcesCompat.getFont(this, R.font.milku)
        inputWord.setTypeface(typeface2, textStyle)

    }
}