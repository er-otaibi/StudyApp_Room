package com.example.studyapp_room

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface

class CustomAlertDialog (activity: Activity, title: String, text: String) {
    init {
        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(activity)

        // set message of alert dialog
        dialogBuilder.setMessage(text)
            // positive button text and action
            .setPositiveButton("OK", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(title)
        // show alert dialog
        alert.show()
    }
}