package com.example.kirill.myapplication

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async


class UserWordContext : Fragment() {

    lateinit var myView: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (inflater != null) {
            myView = inflater.inflate(R.layout.content_user_words, container, false)
        }
        return myView
    }

    fun showWords(rawList: String) {
        try {
            var gson = Gson()
            var parser: Parser = Parser()
            val stringBuilder: StringBuilder = StringBuilder(rawList)
            val jsonList: JsonArray<JsonObject> = parser.parse(stringBuilder) as JsonArray<JsonObject>
            var listWord :MutableList<Word> = arrayListOf()
            jsonList.forEach {
                el -> listWord.add(Word(el.get("Word_id").toString(), el.get("Word_value").toString()))
            }
            val lv = activity.findViewById<ListView>(R.id.listWordView)
            lv.adapter = WordAdapter(activity, listWord)
        }catch (ex:Exception){
            var errView = activity.findViewById<TextView>(R.id.textView2)
            errView.setText("Something wrong try sing in again...")
        }

    }

    override fun onStart() {
        super.onStart()
        var list: List<String> = listOf("One", "Second", "Three")
        var stringBuilder: String
        async {
            NetworkManager.getAllCustomKnownWords(User.ID, { str ->
                showWords(str)
            })
        }
    }

    private class WordAdapter(context: Context, list: List<Word>) : BaseAdapter() {
        private val mListString: List<Word>

        private val mInflator: LayoutInflater

        init {
            mListString = list
            this.mInflator = LayoutInflater.from(context)
        }

        override fun getCount(): Int {
            return mListString.size
        }

        override fun getItem(p0: Int): Any {
            return mListString[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            val view: View?
            val vh: ListRowHolder
            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.word_row, parent, false)
                vh = ListRowHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }

            vh.label.text = mListString[position].Word_value
            return view
        }
    }

    private class ListRowHolder(row: View?) {
        public val label: TextView

        init {
            this.label = row!!.findViewById<TextView>(R.id.wordValueView) as TextView
        }
    }
}