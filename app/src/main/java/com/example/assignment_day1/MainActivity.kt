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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.assignment_day1.databinding.ActivityMainBinding
import com.example.assignment_day1.databinding.RatingBarBinding
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    val TAG: String = "MainActivity"

lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")

        var isclear: Boolean = false

        var weight = binding.wContainer.editText.toString()
        var height = binding.hContainer.editText.toString()

        binding.btnnClick.setOnClickListener() {
            if (weight.isEmpty() && height.isEmpty()) {
                Toast.makeText(this, "please fill both value", Toast.LENGTH_SHORT).show()
            }

            if (height.isEmpty() && weight.isNotEmpty()) {
                Toast.makeText(this, "please fill the height", Toast.LENGTH_SHORT).show()
                binding.hContainer.requestFocus()
            }

            if (weight.isEmpty() && height.isNotEmpty()) {
                Toast.makeText(this, "please fill the weight", Toast.LENGTH_SHORT).show()
               binding.wContainer.requestFocus()
            }
            if (weight.isNotEmpty() && height.isNotEmpty()) {

            }

            if (isclear) {
                isclear = false
                binding.wContainer.editText!!.text.clear()

                binding.wContainer.isEnabled = true
                binding.hContainer.isEnabled = true

                binding.hContainer.editText!!.text.clear()
                binding.te2Input.setText("")
                binding.result.setText("")
               binding.btnnClick.setText("Calculate")
                Toast.makeText(this, "Clear All", Toast.LENGTH_SHORT).show()

            } else {

                if (height.isNotEmpty() && weight.isNotEmpty()) {
                   binding.wContainer.isEnabled = false
                       binding.hContainer.isEnabled = false
                    binding.wContainer.requestFocus()

                    if (!isclear) {
                        isclear = true
                        var msg2: String = "BMI Value:"
                        binding.btnnClick.setText("Clear")
                        Toast.makeText(this, "Calculate Bmi", Toast.LENGTH_SHORT).show()
                        val hF = height.toDouble()
                        val wF = weight.toDouble()
                        val hi = hF / 100
                        val bmi = wF / (hi * hi)
                        binding.result.text = ((bmi * 100) / 100.0).toString()

                        // var bmi: Float = (wF / ((hF / 100) * (hF / 100)))5
                        var msg: String = "";


                        if (bmi < 18.5) {
                            msg = "You are under weight"
                        } else if (bmi > 25 && bmi < 29.9) {
                            msg = "You are over Weight";
                        } else if (bmi > 30) {
                            msg = "you are obese";
                        } else {
                            msg = "You Are Normal"
                        }
                        val df = DecimalFormat("#.##")
                        binding.te2Input.setText(msg);
                        binding.result.setText("Your BMI Value : " + df.format(bmi).toString())


                    }

                }

            }


        }


    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
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
                return  true

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


    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.app_name)
        builder.setMessage("Do You want to Exit")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Yes")
        { dialogInterface, which ->
            finish()
        }
        builder.setNegativeButton("No"){dialogInterface, which ->
            Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_SHORT).show()
        }
        builder.setNeutralButton("Cancel") { dialogInterface, which ->
            Toast.makeText(applicationContext, "operation cancel", Toast.LENGTH_SHORT).show()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()


    }

}




