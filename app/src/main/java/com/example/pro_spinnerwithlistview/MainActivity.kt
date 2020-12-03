package com.example.pro_spinnerwithlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.pro_spinnerwithlistview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val spinData = arrayOf("항목1", "항목2", "항목3")

    lateinit var binding: ActivityMainBinding

    // to use in listener Method, get this out from onCreate Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 1. set the spinner adapter
        val spinAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, spinData)
        // 2. definite spinner's function
        spinAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        // 3. adapt to spinner
        binding.spinner1.adapter = spinAdapter

        binding.spinner1.onItemSelectedListener = spinnerListener

        // List val for listView item
        val listData1 = mutableListOf<String>()
        val totalListData = ArrayList<HashMap<String, Any>>()

        // set Button Click Listener
        binding.button1.setOnClickListener {
            listData1.add(binding.textView1.text.toString())
            for (i in listData1.indices){
                val map = HashMap<String, Any>()
                map["textData"] = listData1[i]

                totalListData.add(map)
            }
//            binding.textView2.text = totalListData[0].toString()

            val keys = arrayOf("textData")
            val values = intArrayOf(R.id.list_textView1)

            val listAdapter = SimpleAdapter(this, totalListData, R.layout.list_view, keys, values)

            binding.listView1.adapter = listAdapter

            listData1.clear()
        }
        setContentView(binding.root)
    }

    val spinnerListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(parent?.id){
                R.id.spinner1 -> binding.textView1.text = parent.getItemAtPosition(position).toString()
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }
}