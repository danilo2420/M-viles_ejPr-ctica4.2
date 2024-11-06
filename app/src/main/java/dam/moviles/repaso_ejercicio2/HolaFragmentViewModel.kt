package dam.moviles.repaso_ejercicio2

import androidx.lifecycle.ViewModel

class HolaFragmentViewModel : ViewModel() {
    lateinit var text: String
    var estado: Estado = Estado.NO_INICIADO
}