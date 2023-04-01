package com.example.assignment_day1

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.assignment_day1.ViewModel.BmiViewModel
import com.example.assignment_day1.databinding.ActivityMainBinding
import com.example.assignment_day1.databinding.RatingBarBinding
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isclear: Boolean = false
    lateinit var viewModel: BmiViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        super.onCreate(savedInstanceState)
        //var weight = binding.wContainer.editText.toString()
        //var height = binding.hContainer.editText.toString()

        binding.btnnClick.setOnClickListener() {

            if (isclear) {
                binding.weight.isEnabled = true
                binding.height.isEnabled = true
                isclear = false
                binding.weight.text!!.clear()
                binding.height.text!!.clear()
                binding.te2Input.setText("")
                binding.result.setText("")
                binding.btnnClick.setText("Calculate")
                Toast.makeText(this, "Clear All", Toast.LENGTH_SHORT).show()
            } else {
                if (binding.height.text.toString().isNotEmpty() && binding.weight.text.toString()
                        .isNotEmpty()
                ) {
                    if (!isclear) {
                        isclear = true
                        binding.btnnClick.setText("Clear")
                        binding.weight.isEnabled = false
                        binding.height.isEnabled = false
                        Toast.makeText(this, "Calculate Bmi", Toast.LENGTH_SHORT).show()
                    }
                }

                viewModel.validate()
            }
        }

        onBackPressedDispatcher.addCallback(this, callback)

        viewModel = ViewModelProvider(this)[BmiViewModel::class.java]
        binding.lifecycleOwner = this

        binding.myviewmodel = viewModel

        viewModel.errormessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.BMI.observe(this, Observer {
            binding.result.text = it.toString()
        })
        viewModel.healthStatus.observe(this, Observer {
            binding.te2Input.text = it.toString()
        })

        callback.isEnabled = true
        if (isclear) {
            isclear = false
            binding.btnnClick.text = "CALCULATE"

        }
    }

    private var callback = object : OnBackPressedCallback(false){
        override fun handleOnBackPressed() {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle(R.string.app_name)
            builder.setMessage("Do You want to Exit")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            builder.setPositiveButton("Yes")
            { dialogInterface, which ->
                finish()
            }
            builder.setNegativeButton("No") { dialogInterface, which ->
                Toast.makeText(applicationContext, "clicked No", Toast.LENGTH_SHORT).show()
            }
            builder.setNeutralButton("Cancel") { dialogInterface, which ->
                Toast.makeText(applicationContext, "operation cancel", Toast.LENGTH_SHORT).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()

        }
    }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.option_menu, menu)
            return super.onCreateOptionsMenu(menu)

        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.about_app -> {
                    val intent = Intent(this, webView::class.java)
                    startActivity(intent)
                    return true


                }

                R.id.abt_developer -> {
                    val intent = Intent(this, abt_developer::class.java)
                    startActivity(intent)
                    return true

                }

                R.id.contact_us -> {
                    val intent = Intent(this, contact_us::class.java)
                    startActivity(intent)
                    return true

                }

                R.id.chart -> {
                    val intent = Intent(this, chart::class.java)
                    startActivity(intent)
                }

                R.id.rating -> {

                    val rating = RatingBarBinding.inflate(layoutInflater)
                    val dialog = Dialog(this)
                    dialog.setContentView(rating.root)
                    dialog.setCancelable(false)
                    val windowManager = WindowManager.LayoutParams()
                    windowManager.width = WindowManager.LayoutParams.MATCH_PARENT
                    windowManager.height = WindowManager.LayoutParams.WRAP_CONTENT
                    dialog.window?.attributes = windowManager
                    dialog.show()

                    rating.button.setOnClickListener {

                        dialog.dismiss()
                    }

                }

            }
            return super.onOptionsItemSelected(item)
        }
    }





