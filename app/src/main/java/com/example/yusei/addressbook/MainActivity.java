package com.example.yusei.addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Person> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPerson();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        PersonAdapter adapter = new PersonAdapter(personList);
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
        String[] name = getResources().getStringArray(R.array.person_name);
        String[] phoneNumber = getResources().getStringArray(R.array.phone_number);
        int[] phtotID = {R.drawable.zs, R.drawable.ls, R.drawable.ww, R.drawable.ql, R.drawable.zq};
        for (int i = 0; i < 5; i++){
            for(int j = 0; j < name.length; j++){
                personList.add(new Person(name[j], phoneNumber[j], phtotID[j]));
            }
        }
    }
}
