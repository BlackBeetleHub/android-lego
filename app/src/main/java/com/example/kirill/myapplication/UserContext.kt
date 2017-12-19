package com.example.kirill.myapplication

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.content_user.*
import kotlinx.coroutines.experimental.async

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

        button.setOnClickListener {
            async {
                NetworkManager.createAccount(editText2.text.toString(), editText.text.toString(), { str ->
                    User.ID = str
                })
            }
        }
    }
}