package com.example.kirill.myapplication

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.content_user.*
import kotlinx.coroutines.experimental.async
import org.w3c.dom.Text

class UserContext : Fragment() {

    lateinit var myView :View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (inflater != null) {
            myView = inflater.inflate(R.layout.content_user, container, false)
        }
        return myView
    }

    override fun onStart() {
        super.onStart()
        var button :Button = activity.findViewById<Button>(R.id.button)
        var welcomeView :TextView = activity.findViewById<TextView>(R.id.welcomeTextView)
        button.setOnClickListener {
            async {
                NetworkManager.createAccount(editText2.text.toString(), editText.text.toString(), { str ->
                    User.ID = str
                    User.Email = editText2.text.toString()
                    welcomeView.setText(User.Email)
                })
            }
        }
    }
}