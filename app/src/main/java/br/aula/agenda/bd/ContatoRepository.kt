package br.aula.agenda.bd

import android.content.Context
import br.aula.agenda.bd.ConstantsDb.CONTATOS_DB_NAME
import br.aula.agenda.bd.ConstantsDb.CONTATOS_DB_TABLE
import org.jetbrains.anko.db.*
import timber.log.Timber


class ContatoRepository(val context: Context) {

    fun findAll(): ArrayList<Contato> = context.database.use {
        val contatos = ArrayList<Contato>()
        select(CONTATOS_DB_TABLE,
            "id",
            "email",
            "endereco",
            "nome",
            "telefone",
            "datanascimento",
            "site",
            "foto"
        )
            .parseList(object : MapRowParser<List<Contato>> {
                override fun parseRow(columns: Map<String, Any?>): List<Contato> {
                    val contato = Contato(
                    id = columns.getValue("id").toString()?.toLong(),
                    nome = columns.getValue("nome")as String,
                    endereco = columns.getValue("endereco") as String,
                    email = columns.getValue("email") as String,
                    site = columns.getValue("site") as String)

                    contatos.add(contato)
                    return contatos
                }
            })
        contatos
    }

    fun create(contato: Contato) = context.database.use {
        insert(CONTATOS_DB_TABLE,
            "foto" to contato.foto,
            "nome" to contato.nome,
            "endereco" to contato.endereco,
            "telefone" to contato.telefone,
            "email" to contato.email,
            "site" to contato.site,
            "dataNascimento" to contato.dataNascimento)
    }

    fun update(contato: Contato) = context.database.use {
        val updateResult = update(CONTATOS_DB_TABLE,
            "foto" to contato.foto,
            "nome" to contato.nome,
            "endereco" to contato.endereco,
            "telefone" to contato.telefone,
            "email" to contato.email,
            "site" to contato.site)
            .whereArgs("id = {id}","id" to contato.id).exec()

        Timber.d("Update result code is $updateResult")
    }

    fun delete(id: Int) = context.database.use {
        delete(CONTATOS_DB_TABLE, whereClause
        = "id = {contatoId}", args = *arrayOf("contatoId" to id))
    }

}