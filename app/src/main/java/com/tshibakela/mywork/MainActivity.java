package com.tshibakela.mywork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    //initializing the variable
    EditText Name,surname, contact, Pass, email, updateold, updatenew, delete;
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    //casting the EditText

        Name = (EditText) findViewById(R.id.editName);
        surname=(EditText)findViewById(R.id.editSurname);
        contact=(EditText)findViewById(R.id.editContact);
        email=(EditText)findViewById(R.id.editEmail);
        Pass = (EditText) findViewById(R.id.editPass);

    //for updating
        updateold = (EditText) findViewById(R.id.editText3);
        updatenew = (EditText) findViewById(R.id.editText5);

     //for deleting
        delete = (EditText) findViewById(R.id.editText6);

    //calling the class
        helper = new myDbAdapter(this);
    }

    //for adding the user on the database
    public void addUser(View view) {
        String t1 = Name.getText().toString();//for name
        String t3 = surname.getText().toString();//for surname
        String t4 = contact.getText().toString();//for contact
        String t5 = email.getText().toString();//for email

        String t2 = Pass.getText().toString();//for password
        if (t1.isEmpty() ||t2.isEmpty() ||t3.isEmpty()||t4.isEmpty()|| t5.isEmpty() ) {
            Message.message(getApplicationContext(), "Enter Both Name and Password");
        } else {
            long id = helper.insertData(t1,t2 , t3, t4, t5);
            if (id <= 0) {
                Message.message(getApplicationContext(), "Insertion Unsuccessful");

                Name.setText("");

                Pass.setText("");
            } else {
                Message.message(getApplicationContext(), "Insertion Successful");

                Name.setText("");
                surname.setText("");
                contact.setText("");
                email.setText("");
                Pass.setText("");
            }
        }
    }

    //for viewing the data stored
    public void viewdata(View view) {
        String data = helper.getData();
        Message.message(this, data);
    }


    //for updating the information
    public void update(View view) {
        String u1 = updateold.getText().toString();
        String u2 = updatenew.getText().toString();
        if (u1.isEmpty() || u2.isEmpty()) {
            Message.message(getApplicationContext(), "Enter Data");
        } else {
            int a = helper.updateName(u1, u2);
            if (a <= 0) {
                Message.message(getApplicationContext(), "Unsuccessful");
                updateold.setText("");
                updatenew.setText("");
            } else {
                Message.message(getApplicationContext(), "Updated");
                updateold.setText("");
                updatenew.setText("");
            }
        }

    }

    //for deleting the data within database
    public void delete(View view) {
        String uname = delete.getText().toString();
        if (uname.isEmpty()) {
            Message.message(getApplicationContext(), "Enter Data");
        } else {
            int a = helper.delete(uname);
            if (a <= 0) {
                Message.message(getApplicationContext(), "Unsuccessful");
                delete.setText("");
            } else {
                Message.message(this, "DELETED");
                delete.setText("");
            }
        }
    }
}