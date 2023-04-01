package com.example.assignment_day1.ViewModel

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment_day1.Observable.Observables
import java.text.DecimalFormat
import kotlin.math.roundToInt

class BmiViewModel(): ViewModel(){
    var _count: MutableLiveData<Double> = MutableLiveData()
    var BMI: LiveData<Double> = _count
    var healthStatus: MutableLiveData<String> = MutableLiveData()
    var baseObservable: Observables = Observables()
    var errormessage: MutableLiveData<String> = MutableLiveData()

    fun validate(){
        if(baseObservable.height.isBlank() && baseObservable.weight.isBlank()){
            errormessage.value = "Please enter thr height and weight"
        }
        else if(baseObservable.height.isBlank()){
            errormessage.value = "Please enter thr height"
        }
        else if(baseObservable.weight.isBlank()){
            errormessage.value = "Please enter the weight"
        }
        else{
            val hF = baseObservable.height.toFloat()/100
            val bmi = baseObservable.weight.toFloat() / (hF * hF)
            val x = (bmi * 100).roundToInt() / 100.0

            // var bmi: Float = (wF / ((hF / 100) * (hF / 100)))5
            var msg: String = "";


            if (x < 18.5) {
                healthStatus.value = "You are under weight "
                _count.value = x
            } else if (x > 18.5 && x < 24.9) {
                healthStatus.value = "You are Healthy"
                _count.value = x
            } else if (x > 24.9 && x< 30) {
                healthStatus.value = "You are over weight "
                _count.value = x
            } else {
                healthStatus.value = "You are Obesse "
                _count.value = x
            }
        }
    }
}