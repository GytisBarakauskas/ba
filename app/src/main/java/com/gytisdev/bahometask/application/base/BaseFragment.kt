package com.gytisdev.bahometask.application.base

import android.app.AlertDialog
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    fun showDialog(
        title: String,
        message: String,
        cancelButtonText: String,
        okButtonText: String,
        onOkClick: () -> Unit
    ) {
        activity?.let { activity ->
            val alertDialog: AlertDialog = AlertDialog.Builder(activity).create()
            alertDialog.setTitle(title)
            alertDialog.setMessage(message)
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, okButtonText) { dialog, _ ->
                onOkClick()
                dialog.dismiss()
            }
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, cancelButtonText) { dialog, _ ->
                dialog.dismiss()
            }
            alertDialog.show()
        }
    }
}