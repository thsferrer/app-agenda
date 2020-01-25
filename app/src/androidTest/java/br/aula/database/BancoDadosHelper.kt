package br.aula.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

class BancoDadosHelper(context: Context) :
    ManagedSQLiteOpenHelper(ctx = context ,
        name = "agenda.db",  version = 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // Criação de tabelas
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Atualização do banco de dados
    }

}
