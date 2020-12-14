package com.uni.myuniapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import com.uni.myuniapp.adapter.Person
import com.uni.myuniapp.adapter.RecAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_recycler.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val persons = listOf(
        Person("Alexey", "E"),
        Person("Vasya", "E"),
        Person("V", "S"),
        Person("IVAN", "PILESOS"),
    )

    private val adapter = RecAdapter().apply { items = persons }

    override fun onStart() {
        super.onStart()
        button.setOnClickListener {
            Intent(this, SecondActivity::class.java).also { intent ->
                intent.putExtras(
                    bundleOf("myText" to textInput.text.toString())
                )
                startActivity(intent, bundleOf())
            }
        }

        recycler.adapter = adapter
    }
}