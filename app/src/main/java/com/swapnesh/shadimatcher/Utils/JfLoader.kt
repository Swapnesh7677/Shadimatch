package com.triologic.jewel_cliq.utils

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.swapnesh.shadimatcher.R


class JfLoader {
    private var alertDialog: AlertDialog? = null

    fun showLoader(act: Activity?) {
        if (act != null) {
            hideLoader(act)
            val v = act.layoutInflater.inflate(R.layout.jf_loader_layout, null)
            val builder = AlertDialog.Builder(act).setCancelable(false).setView(v)
            alertDialog = builder.create()
            alertDialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            alertDialog!!.show()
        }
    }

    fun showLoader(act: Activity?, message: String?) {
        if (act != null) {
            hideLoader(act)
            val v = act.layoutInflater.inflate(R.layout.jf_loader_msg_layout, null)
            val builder = AlertDialog.Builder(act).setCancelable(false).setView(v)
            alertDialog = builder.create()
            alertDialog!!.show()
            val tv_msg = v.findViewById<TextView>(R.id.tv_msg)
            tv_msg.visibility = View.VISIBLE
            tv_msg.text = message
        }
    }

    fun hideLoader(act: Activity?) {
        if (act != null && !act.isFinishing && !act.isDestroyed) {
            if (alertDialog != null && alertDialog!!.isShowing) {
                alertDialog!!.dismiss()
            }
        }
    }

    companion object {
        /* var instance: JfLoader? = null
             get() {
                 if (field == null) {
                     field = JfLoader()
                 }
                 return field
             }
             private set*/
        private var instance: JfLoader? = null

        fun init(): JfLoader {
            if (instance == null) {
                instance = JfLoader()
            }
            return instance!!
        }


        val newInstance: JfLoader
            get() = JfLoader()
    }
}