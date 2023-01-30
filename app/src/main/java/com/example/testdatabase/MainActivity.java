package com.example.testdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testdatabase.MyDataBase.MyDataBase;
import com.example.testdatabase.MyDataBase.MyDataBaseThree;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText color , company ,price ;
    Button btn_save , btn_restore ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        color = (EditText) findViewById(R.id.color_EditText);
        company = (EditText) findViewById(R.id.company_EditText);
        price = (EditText) findViewById(R.id.price_EditText);

        btn_save = (Button) findViewById(R.id.save_button);
        btn_restore= (Button) findViewById(R.id.restore_button);


        MyDataBaseThree dataBase = new MyDataBaseThree(this);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String color_save = color.getText().toString();
                String company_save = company.getText().toString();
                String price_save = price.getText().toString();

                Mobile mobile = new Mobile(company_save,color_save,price_save);
                boolean res =dataBase.insertMobile(mobile);

                if (res){
                    Toast.makeText(MainActivity.this, "true", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this, "false", Toast.LENGTH_SHORT).show();

            }
        });


        btn_restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Mobile> mobileArrayList = dataBase.getAllMobile();

                for (Mobile m : mobileArrayList){
                    Toast.makeText(MainActivity.this, "#" + m.getCompany() + m.getColor() , Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}