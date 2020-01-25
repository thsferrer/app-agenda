package br.aula.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class BancoDadosHelper(context: Context) :
    ManagedSQLiteOpenHelper(ctx = context ,
        name = "agenda.db",  version = 1) {
    //singleton da classe (obj é criado 1x e reutilizado)
    companion object {
        private var instance: BancoDadosHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): BancoDadosHelper {
            if (instance == null) {
                instance = BancoDadosHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }
    override fun onCreate(db: SQLiteDatabase) {
        // cria uma tabela no banco de dados
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
        TODO("not implemented")
    }
}
