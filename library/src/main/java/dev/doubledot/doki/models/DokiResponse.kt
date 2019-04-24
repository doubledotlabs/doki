package dev.doubledot.doki.models

import androidx.databinding.Bindable
import dev.doubledot.doki.BR
import dev.doubledot.doki.api.models.DokiManufacturer
import dev.doubledot.doki.util.ObservableViewModel

class DokiResponse : ObservableViewModel() {

    @get:Bindable
    var manufacturer : DokiManufacturer? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.manufacturer)
        }

}