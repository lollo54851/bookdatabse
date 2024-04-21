package com.example.roomdemo02.data;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.example.roomdemo02.dao.BookDao;
import com.example.roomdemo02.db.AppDatabase;
import com.example.roomdemo02.entity.Book;
import java.util.List;

public class BookRepository {

    private LiveData<List<Book>> liveDataAllBook;
    private BookDao bookDao;

    public BookRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        bookDao = database.bookDao();
        if (liveDataAllBook == null) {
            liveDataAllBook = bookDao.getAllLiveDataBook();
        }
    }

    // 增
    void insert(Book... books) {
        bookDao.insert(books);
    }

    // 删
    void delete(Book book) {
        bookDao.delete(book);
    }

    // 改
    void update(Book book) {
        bookDao.update(book);
    }

    // 查
    List<Book> getAll() {
        return bookDao.getAll();
    }

    // 查 关联 LiveData 暴露环节
    LiveData<List<Book>> getAllLiveDataBook() {
        return bookDao.getAllLiveDataBook();
    }

    LiveData<List<Book>> getLiveDataBookById(int id) {
        return bookDao.getLiveDataBookById(id);
    }

    void clear() {
        bookDao.clear();
    }
}
