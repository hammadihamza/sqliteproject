package com.example.sqliteproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText name,email,password;
Button insert,update,delete,view;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.Name);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);

        insert = findViewById(R.id.InsertBtn);
        update = findViewById(R.id.UpdateBtn);
        delete = findViewById(R.id.DeleteBtn);
        view = findViewById(R.id.ViewBtn);
        dbHelper = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt = name.getText().toString();
                String emailTxt = email.getText().toString();
                String passwordTxt = password.getText().toString();

                Boolean checkInsertData = dbHelper.insertUserData(nameTxt,emailTxt,passwordTxt);
                if (checkInsertData==true) Toast.makeText(MainActivity.this,"Entry Inserted!",Toast.LENGTH_LONG).show();
                else Toast.makeText(MainActivity.this,"Entry not Inserted!",Toast.LENGTH_LONG).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt = name.getText().toString();
                String emailTxt = name.getText().toString();
                String passwordTxt = password.getText().toString();

                Boolean checkUpdateData = dbHelper.updateUserData(nameTxt,emailTxt,passwordTxt);
                if (checkUpdateData==true) Toast.makeText(MainActivity.this,"Entry Updated!",Toast.LENGTH_LONG).show();
                else Toast.makeText(MainActivity.this,"Entry not Updated!",Toast.LENGTH_LONG).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt = name.getText().toString();

                Boolean checkDeleteData = dbHelper.deleteUserData(nameTxt);
                if (checkDeleteData==true) Toast.makeText(MainActivity.this,"Entry Deleted!",Toast.LENGTH_LONG).show();
                else Toast.makeText(MainActivity.this,"Entry not Deleted!",Toast.LENGTH_LONG).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = dbHelper.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No Entries", Toast.LENGTH_LONG).show();
                    return;
                }

                StringBuffer stringBuffer=new StringBuffer();
                while (res.moveToNext()){
                    stringBuffer.append("Name: "+res.getString(0)+"\n");
                    stringBuffer.append("Email: "+res.getString(1)+"\n");
                    stringBuffer.append("Password: "+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(stringBuffer.toString());
                builder.show();
            }
        });
    }
}