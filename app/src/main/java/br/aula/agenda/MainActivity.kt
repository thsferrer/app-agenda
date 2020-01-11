package br.aula.agenda

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
// Compat é utilizado para que os componentes sejam compatíveis com todos os telefones
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        // O action bar serve para dar uma ação ao menu (voltar, por exemplo)
        val myToolbar = toolbar
        myToolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(myToolbar)

        val contatos = arrayOf("Maria", "José", "Carlos")
        val adapter
                = ArrayAdapter(this, android.R.layout.simple_list_item_1, contatos)

        var listaContatos = lista // lista corresponde ao id que está no layout no componente ListView
        listaContatos.setAdapter(adapter);
        // Poderia deixar apenas conforme abaixo:
        // lista.setAdapter(adapter);
    }
    // Trabalhando com menus na aplicação
    // Criar um método override para poder customiza-lo. Irá controlar o listener.
    // O método OnCreate controla o ciclo de vida da aplicação.
   override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        // a classe R puxa o layout
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    // Comportamento do Menu: recebe o id do menu e realiza uma ação
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.novo -> {
                val intent = Intent(this, ContatoActivity::class.java)
                startActivity(intent)
                return false
            }

            R.id.sincronizar -> {
                Toast.makeText(this, "Enviar", Toast.LENGTH_LONG).show()
                return false
            }

            R.id.receber -> {
                Toast.makeText(this, "Receber", Toast.LENGTH_LONG).show()
                return false
            }

            R.id.mapa -> {
                Toast.makeText(this, "Mapa", Toast.LENGTH_LONG).show()
                return false
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

}
