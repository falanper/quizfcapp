package com.falanper.jogodostimes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnStart: Button
    private lateinit var etName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        btnStart = findViewById(R.id.btn_start)
        etName = findViewById(R.id.et_name)
        btnStart.setOnClickListener {
            if(etName.text.toString().isEmpty()){
                Toast.makeText(this, getString(R.string.toast_text_main_activity), Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, QuizzQuestionsActivity::class.java)
                intent.putExtra(Constants.USERNAME, etName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}