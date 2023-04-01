package com.example.assignment_day1.Observable

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import java.lang.reflect.Field

class Observables : BaseObservable() {
    @get:Bindable

    var height: String= ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.height)

        }

    @get:Bindable

    var weight : String =""
    set(value) {

        field = value
        notifyPropertyChanged(BR.weight)
    }
}
