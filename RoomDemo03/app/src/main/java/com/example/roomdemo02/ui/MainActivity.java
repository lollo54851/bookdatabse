package com.example.roomdemo02.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.roomdemo02.R;
import com.example.roomdemo02.data.BookViewModel;
import com.example.roomdemo02.entity.Book;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    BookViewModel bookViewModel;
    private Button btnBook;
    private Button btnUpdate;

    private EditText etId;
    private EditText etTitle;
    private EditText etName;
    private EditText etISBN;
    private EditText etPub;
    private int mQueryType;
    private static final int CREATE = 0;
    private static final int QUERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        listView = findViewById(R.id.listView);
        btnBook = findViewById(R.id.btn_book);

        etId = findViewById(R.id.id);
        etTitle = findViewById(R.id.title);
        etName = findViewById(R.id.name);
        etISBN = findViewById(R.id.isbn);
        etPub = findViewById(R.id.pub);
        btnBook = findViewById(R.id.btn_book);
        btnUpdate = findViewById(R.id.btn_update);

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);


        btnBook.setOnClickListener(v -> {
            //bookViewModel.insert(new Book("title3","author3","pub3","isbn3"));
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("选择一项");
            final String[] items = {"创建", "根据id检索"};
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mQueryType = which;
                    updateInput(which);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        });

        btnUpdate.setOnClickListener(v -> {
            if (mQueryType == CREATE) {
                String title = getContent(etTitle);
                String name = getContent(etName);
                String pub = getContent(etPub);
                String isbn = getContent(etISBN);

                bookViewModel.insert(new Book(title, name, pub, isbn));
                bookViewModel.getAllLiveDataBook().observe(this, new Observer<List<Book>>() {
                    @Override
                    public void onChanged(List<Book> books) {
                        Log.d("MainActivity-lollo", "onChanged: "+ books);
                        // 更新UI
                        listView.setAdapter(new BookAdapter(MainActivity.this, books));
                    }
                });
            } else if (mQueryType == QUERY) {
                String idStr = getContent(etId);
                if (!TextUtils.isEmpty(idStr)) {
                    int id;
                    try {
                        id = Integer.parseInt(idStr);
                    } catch (NumberFormatException e) {
                        id = 0;
                    }
                    bookViewModel.getLiveDataBookById(id).observe(this, new Observer<List<Book>>() {
                        @Override
                        public void onChanged(List<Book> books) {
                            // 更新UI
                            listView.setAdapter(new BookAdapter(MainActivity.this, books));
                        }
                    });
                }
            }
        });

        initData();
    }

    private void initData() {
        bookViewModel.clear();
        bookViewModel.insert(new Book("title1","author1","pub1","isbn1"));
        bookViewModel.insert(new Book("title2","author2","pub2","isbn2"));
        bookViewModel.insert(new Book("title3","author3","pub3","isbn3"));
    }

    private String getContent(EditText et) {
        String result = "";
        if (null != et) {
            result = et.getText().toString();
        }
        return result;
    }

    private void updateInput(int what) {
        if (what == 0) {
            etId.setEnabled(false);
            etTitle.setEnabled(true);
            etTitle.setText("");
            etName.setEnabled(true);
            etName.setText("");
            etISBN.setEnabled(true);
            etISBN.setText("");
            etPub.setEnabled(true);
            etPub.setText("");
        } else if (what == 1) {
            etId.setEnabled(true);
            etId.setText("");
            etTitle.setEnabled(false);
            etName.setEnabled(false);
            etISBN.setEnabled(false);
            etPub.setEnabled(false);
        }
    }
}
