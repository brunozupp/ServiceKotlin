package com.novelitech.servicekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.novelitech.servicekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            Intent(this, MyService::class.java).also {
                startService(it)
                binding.tvMessage.text = "Service Running"
            }
        }

        binding.btnStop.setOnClickListener {
            Intent(this, MyService::class.java).also {
                stopService(it)
                binding.tvMessage.text = "Service Stopped"
            }
        }

        binding.btnSendData.setOnClickListener {
            Intent(this, MyService::class.java).also {

                val dataString = binding.etData.text.toString()

                it.putExtra("EXTRA_DATA", dataString)

                binding.etData.text.clear()

                startService(it)
            }
        }
    }
}