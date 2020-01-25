package br.aula.agenda

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import br.aula.agenda.bd.Contato
import br.aula.agenda.bd.ContatoRepository
import kotlinx.android.synthetic.main.activity_contato.*
import java.text.SimpleDateFormat
import java.util.*

class ContatoActivity : AppCompatActivity() {
    // Ligar o botão à data
    var cal = Calendar.getInstance()
    var datanascimento: Button? = null
    var contato: Contato? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato)

        val myChildToolbar = toolbar_child
        setSupportActionBar(myChildToolbar)

        if(intent != null){
            if(intent.getSerializableExtra("contato") != null){
                contato = intent.getSerializableExtra("contato") as Contato
                txtNome?.setText(contato?.nome)
                txtEndereco?.setText(contato?.endereco)
                txtTelefone?.setText(contato?.telefone?.toString())
                //dataNascimento = cal.timeInMillis,
                txtEmail?.setText(contato?.email)
                txtSite?.setText(contato?.site)
            }
            else{
                contato = Contato()
            }
        }

        // Get a support ActionBar corresponding to this toolbar
        val ab = supportActionBar

        // Enable the Up button
        ab?.setDisplayHomeAsUpEnabled(true)

        // Componente para escolher a data e mostrar, já deixa setada uma data de início no calendário
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        txtDatanascimento.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                    this@ContatoActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })

        btnCadastro?.setOnClickListener {
                contato?.nome = txtNome?.text.toString()
                contato?.endereco = txtEndereco?.text.toString()
                contato?.telefone = txtTelefone?.text.toString().toLong()
                contato?.dataNascimento = cal.timeInMillis
                contato?.email = txtEmail?.text.toString()
                contato?.site = txtSite?.text.toString()

            if(contato?.id?.toInt() == 0){
                ContatoRepository(this).create(contato!!)
            }else{
                ContatoRepository(this).update(contato!!)
            }
            finish()
        }
    }
        private fun updateDateInView() {
            val myFormat = "dd/MM/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            datanascimento!!.text = sdf.format(cal.getTime())
        }
}
