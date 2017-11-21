package com.example.yusei.addressbook;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Person> personList = new ArrayList<>();
    private PersonAdapter adapter;
    private MyDataBaseHelper dbHelper;
    private EditText search_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDataBaseHelper(this, "AddressBook.db", null ,9);
        search_edit = (EditText) findViewById(R.id.search_edit);
        Button search_button = (Button) findViewById(R.id.search);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPerson();
                adapter.notifyDataSetChanged();
            }
        });
        initDB();
        initPerson();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PersonAdapter(personList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new PersonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, Deatil.class);
                intent.putExtra("Person",personList.get(position));
                startActivity(intent);
            }
        });
    }

    private void initPerson() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor;
        if (search_edit.getText().toString().equals("")) {
            cursor = db.query("Person",null,null,null,null,null,null);
        }
        else {
            String[] col = {"name", "phoneNumber", "photoID"};
            cursor = db.query("Person",col, "name = ?", new String[]{search_edit.getText().toString()},null,null,null);
        }
        if (cursor.moveToFirst()) {
            personList.clear();
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));
                int photoID = cursor.getInt(cursor.getColumnIndex("photoID"));
                personList.add(new Person(name, phoneNumber, photoID));
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void initDB() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String[] name = getResources().getStringArray(R.array.person_name);
        String[] phoneNumber = getResources().getStringArray(R.array.phone_number);
        int[] phtotID = {R.drawable.zs, R.drawable.ls, R.drawable.ww, R.drawable.ql, R.drawable.zq};
        for (int i = 0; i < name.length; i++) {
            values.put("name", name[i]);
            values.put("phoneNumber", phoneNumber[i]);
            values.put("photoID", phtotID[i]);
            db.insert("Person", null, values);
            values.clear();
        }
    }
}
