package com.example.roomdemo02.data;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdemo02.entity.Book;

import java.util.List;

// AndroidViewModel 保证数据的稳定性
public class BookViewModel extends AndroidViewModel {

    // LiveData

    private BookRepository bookRepository; // 定义仓库 ViewModel只操作仓库

    public BookViewModel(Application application) {
        super(application);

        bookRepository = new BookRepository(application);
    }

    // 增
    public void insert(Book... books) {
        bookRepository.insert(books);
    }

    // 删
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    // 改
    public void update(Book book) {
        bookRepository.update(book);
    }

    // 查 等下不同，因为他没有灵活
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    // 查 关联 LiveData  【相当于 我的 ViewModel 也有同一份 LiveData】
    public LiveData<List<Book>> getAllLiveDataBook() {
        return bookRepository.getAllLiveDataBook();
    }

    public LiveData<List<Book>> getLiveDataBookById(int id) {
        return bookRepository.getLiveDataBookById(id);
    }

    public void clear() {
        bookRepository.clear();
    }
}
