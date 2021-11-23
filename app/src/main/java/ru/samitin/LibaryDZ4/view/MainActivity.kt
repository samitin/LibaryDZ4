package ru.samitin.LibaryDZ4.view



import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.samitin.LibaryDZ4.databinding.ActivityMainBinding
import ru.samitin.LibaryDZ4.model.RepositoryImpl
import ru.samitin.LibaryDZ4.presenter.MainPresenter
import ru.samitin.LibaryDZ4.presenter.Presenter

class MainActivity : AppCompatActivity(),View {


    var binding:ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val presenter:Presenter=MainPresenter(this, RepositoryImpl())
        binding?.editText?.setText("/storage/emulated/0/DCIM/SharedFolder/android.jpg")
        binding?.button?.setOnClickListener {
            presenter.startProcess(binding?.editText?.text.toString())

        }
        binding?.buttonCancel?.setOnClickListener {
            presenter.cancelPocess()

        }
    }


    override fun cancelShowProcess() {
        binding?.layoutProcess?.visibility=android.view.View.GONE
    }

    override fun showProcess() {
        binding?.layoutProcess?.visibility=android.view.View.VISIBLE
    }

    override fun showError() {
        binding?.layoutProcess?.visibility=android.view.View.GONE
        Toast.makeText(this, "Error!!!", Toast.LENGTH_SHORT).show()
    }
}