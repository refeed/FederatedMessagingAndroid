package com.refeed_ppb1.federatedmessaging.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.refeed_ppb1.federatedmessaging.models.ServerModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String SERVER_TABLE = "SERVER_TABLE";
    public static final String COLUMN_SERVER_ADDRESS = "SERVER_ADDRESS";
    public static final String COLUMN_SERVER_NAME = "SERVER_NAME";

    public DBHelper(Context context) {
        super(context, "server.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + SERVER_TABLE + " (" +
                COLUMN_SERVER_ADDRESS + " TEXT PRIMARY KEY," +
                COLUMN_SERVER_NAME + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addServer(ServerModel serverModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SERVER_NAME, serverModel.getName());
        cv.put(COLUMN_SERVER_ADDRESS, serverModel.getAddress());

        final long insert = db.insert(SERVER_TABLE, null, cv);
        db.close();
        if (insert == -1) {
            return false;
        }
        return true;
    }

    public List<ServerModel> getAllServers() {
        List<ServerModel> servers = new ArrayList<>();

        String getQuery = "SELECT * FROM " + SERVER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(getQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String serverName = cursor.getString(1);
                String serverAddress = cursor.getString(0);

                ServerModel server = new ServerModel(serverName, serverAddress);
                servers.add(server);
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return servers;
    }
}
