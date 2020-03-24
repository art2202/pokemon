package com.example.pokemon.util


import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View


class CustomAlertDialogBuilder(context: Context?) : AlertDialog.Builder(context) {

    fun customBuilder(
        view: View?,
        positiveButton: String?,
        negativeButton: String?): AlertDialog.Builder {
        return AlertDialog.Builder(context)
            .setView(view)
            .setCancelable(false)
            .setPositiveButton(positiveButton, null)
            .setNegativeButton(
                negativeButton
            ) { dialog: DialogInterface, _
            : Int ->

                dialog.cancel()
            }
    }
}
