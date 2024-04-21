package com.example.roomdemo02.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.roomdemo02.entity.Book;
import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insert(Book... books);

    @Delete
    void delete(Book book);

    @Update
    void update(Book book);

    @Query("select * from Book")
    List<Book> getAll();

    @Query("select * from Book order by id")
    LiveData<List<Book>> getAllLiveDataBook();

    @Query("select * from Book where id = :bookId")
    LiveData<List<Book>> getLiveDataBookById(int bookId);

    @Query("delete from Book ")
    void clear();
}
