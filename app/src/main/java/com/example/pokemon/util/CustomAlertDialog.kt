
package com.example.pokemon.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnShowListener
import android.widget.EditText
import androidx.core.content.ContextCompat
import java.util.*

class CustomAlertDialog(
    context: Context?,
    private val editText: EditText,
    private val alertDialog: AlertDialog,
    private val funcFiltrar: () -> Unit) :  AlertDialog(context) {

    private var colorPositive = 0
    private var textSizePositive = 0
    private var colorNegative = 0
    private var textSizeNegative = 0
    fun customButtonPositive(colorResources: Int, textSize: Int): CustomAlertDialog {
        colorPositive = colorResources
        textSizePositive = textSize
        return this
    }

    fun customButtonNegative(colorResources: Int, textSize: Int): CustomAlertDialog {
        colorNegative = colorResources
        textSizeNegative = textSize
        return this
    }

    override fun show() {
        alertDialog.setOnShowListener(showListener())
        alertDialog.show()
    }

    private fun showListener(): OnShowListener {
        return OnShowListener { dialog: DialogInterface ->
            val positive = alertDialog.getButton(
                DialogInterface.BUTTON_POSITIVE
            )
            val positiveColor = Optional.of(colorPositive)
            positiveColor.ifPresent { c: Int? ->
                    positive.setTextColor(ContextCompat.getColor(context, c!!))
                    positive.textSize = textSizePositive.toFloat()
                }
            val negativeColor = Optional.of(colorNegative)
            negativeColor
                .ifPresent { c: Int? ->
                    val negative = alertDialog.getButton(
                        DialogInterface.BUTTON_NEGATIVE
                    )
                    negative.setTextColor(ContextCompat.getColor(context, c!!))
                    negative.textSize = textSizeNegative.toFloat()
                }
            positive.setOnClickListener{clickPositivo(dialog)}
        }
    }

    fun clickPositivo(dialog: DialogInterface) {

        funcFiltrar()

        dialog.dismiss()

    }

}