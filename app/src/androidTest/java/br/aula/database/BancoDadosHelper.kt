package br.aula.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class BancoDadosHelper(context: Context) :
    ManagedSQLiteOpenHelper(ctx = context ,
        name = "agenda.db",  version = 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable("agenda", true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "email" to TEXT,
            "nome" to TEXT,
            "endereco" to TEXT,
            "telefone" to INTEGER,
            "datanascimento" to INTEGER,
            "site" to TEXT,
            "foto" to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Atualização do banco de dados
    }

}
