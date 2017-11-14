package com.example.yusei.addressbook;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Deatil extends AppCompatActivity implements View.OnClickListener{

    private EditText phoneNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatil);
        Intent intent =getIntent();
        Person person = intent.getParcelableExtra("Person");
        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setImageResource(person.getPhotoID());
        EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        nameEditText.setText(person.getName());
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        phoneNumberEditText.setText(person.getPhoneNumber());
        Button call = (Button) findViewById(R.id.Call);
        call.setOnClickListener(this);
        Button message = (Button) findViewById(R.id.Message);
        message.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.Call:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phoneNumberEditText.getText()));
                startActivity(intent);
                break;
            case R.id.Message:
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"+phoneNumberEditText.getText()));
                startActivity(intent);
                break;
        }

    }
}
