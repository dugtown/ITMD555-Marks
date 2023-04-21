package com.example.marks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    //elements
    EditText editName, editMarks, editId;
    Button btnAddData;
    Button btnViewAll;
    Button btnDelete;
    Button btnUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        TextView editName = (TextView) findViewById(R.id.editName);
        TextView editMarks = (TextView) findViewById(R.id.editMarks);
        TextView editId = (TextView) findViewById(R.id.editId);
        Button btnAddData = (Button)findViewById(R.id.btnAddData);
        Button btnViewAll = (Button)findViewById(R.id.btnViewAll);
        Button btnDelete = (Button)findViewById(R.id.btnDelete);
        Button btnUpdate = (Button)findViewById(R.id.btnUpdate);
        System.out.println("******* ADD DATA CALLED");

        btnDelete.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Integer deletedRows = myDb.deleteData(editId.getText().toString());
                                         }
                                     }
        );

        btnUpdate.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             boolean isUpdate = myDb.updateData(editId.getText().toString(),
                                                     editName.getText().toString(), editMarks.getText().toString());
                                         }
                                     }
        );

        btnAddData.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              boolean isInserted = myDb.insertData(editId.getText().toString(), editName.getText().toString(),
                                                      editMarks.getText().toString());
                                          }
                                      }
        );


        btnViewAll.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Cursor res = myDb.getAllData();

                                              StringBuffer buffer = new StringBuffer();
                                              buffer.append("Record Data...\n");
                                              while (res.moveToNext()) { //cycle thru result set
                                                  buffer.append("Id :" + res.getString(0) + "\n");
                                                  buffer.append("Name :" + res.getString(1) + "\n");
                                                  buffer.append("Marks :" + res.getString(2) + "\n\n");
                                              }

                                              // Show all data
                                              Log.i("Data", buffer.toString());

                                          }
                                      }
        );

    } //end onCreate()
}








