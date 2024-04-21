package com.example.roomdemo02.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomdemo02.dao.BookDao;
import com.example.roomdemo02.entity.Book;

@Database(entities = {Book.class}, version = 4, exportSchema = false)
public abstract class AppDatabase  extends RoomDatabase {

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class
                    , "BookDB")

                    .allowMainThreadQueries()

                    .addMigrations(MIGRATION_1_2)

                    .build();
        }
        return instance;
    }

    public abstract BookDao bookDao();

    static final Migration MIGRATION_1_2 = new Migration(4, 5) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("alter table Book add column id integer not null default 1");
        }
    };
}
