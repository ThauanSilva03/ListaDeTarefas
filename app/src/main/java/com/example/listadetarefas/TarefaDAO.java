package com.example.listadetarefas;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());
        try {
            write.insert(DbHelper.TABELA_TAREFAS, null, cv);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean atualizar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());
        try {
            String[] args = {String.valueOf(tarefa.getId())};
            write.update(DbHelper.TABELA_TAREFAS, cv, "id=?", args);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deletar(Tarefa tarefa) {
        try {
            String[] args = {String.valueOf(tarefa.getId())};
            write.delete(DbHelper.TABELA_TAREFAS, "id=?", args);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressLint("Range")
    public List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + ";";
        Cursor c = read.rawQuery(sql, null);

        while (c.moveToNext()) {
            Tarefa tarefa = new Tarefa();
            tarefa.setId(c.getLong(c.getColumnIndex("id")));
            tarefa.setNomeTarefa(c.getString(c.getColumnIndex("nome")));
            tarefas.add(tarefa);
        }
        c.close();
        return tarefas;
    }
}
