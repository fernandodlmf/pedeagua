package com.pedeagua.SQLite;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

@SuppressLint("NewApi") public class DBCore extends SQLiteOpenHelper {
	private static final String NOME_DB = "dbPedeAgua";
	private static final int VERSAO_DB = 7;

	
	public DBCore(Context ctx){
		super(ctx, NOME_DB, null,  VERSAO_DB);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String create_produto = "CREATE TABLE IF NOT EXISTS produto_agua"
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, id_produto INTEGER,"
				+ " id_foto INTEGER, nome TEXT, descricao TEXT, preco TEXT);";
		
		String create_pedido = "CREATE TABLE IF NOT EXISTS pedido_agua"
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, id_produto INTEGER, qtdIndividual INTEGER,"
				+ "nome TEXT, preco TEXT);";
		//db.execSQL(create_produto);
		db.execSQL(create_pedido);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int VERSAO_DB) {
		String drop_produto = "DROP TABLE produto_agua";
		db.execSQL(drop_produto);
		onCreate(db);
		
		
		
	}

	
}
