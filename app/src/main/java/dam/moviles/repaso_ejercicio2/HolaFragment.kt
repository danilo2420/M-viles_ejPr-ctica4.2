package dam.moviles.repaso_ejercicio2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dam.moviles.repaso_ejercicio2.databinding.FragmentHolaBinding
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class HolaFragment : Fragment() {
    private lateinit var binding: FragmentHolaBinding
    private lateinit var viewModel: HolaFragmentViewModel
    private lateinit var file: File

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        inicializarBinding(inflater, container)
        inicializarViewModel()
        inicializarFile()
        inicializarEditText()
        inicializarEventos()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if(viewModel.estado == Estado.INICIADO){
            binding.bloc.setText(viewModel.text)
        }else{
            viewModel.estado = Estado.INICIADO
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.text = binding.bloc.text.toString()
    }

    fun inicializarBinding(inflater: LayoutInflater, container: ViewGroup?){
        binding = FragmentHolaBinding.inflate(inflater, container, false)
    }

    fun inicializarViewModel(){
        viewModel = ViewModelProvider(this).get(HolaFragmentViewModel::class.java)
    }

    fun inicializarEditText(){
        if(file.exists()){
            var s: String = ""
            FileReader(file).use{ reader -> s = reader.readText() }
            //Log.d("myMessage", "s is $s")
            binding.bloc.setText(s)
        }
    }

    private fun inicializarFile() {
        file = File(requireContext().filesDir, "bloc_notas.txt")
    }

    private fun inicializarEventos() {
        binding.button.setOnClickListener {
            if(!file.exists()){
                file.createNewFile()
            }
            var s: String = binding.bloc.text.toString()
            FileWriter(file).use{ writer -> writer.write(s) }

        }
    }

}