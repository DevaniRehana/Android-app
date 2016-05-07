package edu.uta.eventapp.uta_event;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button register, cancel;
    EditText name, email, password;
    socketAndroid SocketConn;
    CheckBox bAcademic,bNonAcademic;
    String Category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
// Name, Email, Password
        name = (EditText) findViewById(R.id.bName);
        email = (EditText) findViewById(R.id.bEmail);
        password = (EditText) findViewById(R.id.bPassword);
// CheckBox Selection
        bAcademic = (CheckBox) findViewById(R.id.bAcademic);
        bNonAcademic = (CheckBox) findViewById(R.id.bNonAcademic);
// Buttons for Register and Cancel
        register = (Button) findViewById(R.id.bRegister);
        cancel = (Button) findViewById(R.id.bCancel);
//Calling Function for Register and Cancel.
        register.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bRegister:
                String Academic,NonAcademic;
                String Category;


                if(bAcademic.isChecked())
                    {
                        Academic="true";
                    }
                else
                        Academic="false";

                if(bNonAcademic.isChecked())
                    {
                        NonAcademic="true";
                    }
                else
                        NonAcademic="false";

                Category = Academic+"#"+NonAcademic;

                SocketConn = new socketAndroid(Register.this);
                SocketConn.execute(new String[] {"Register",name.getText().toString(),email.getText().toString(),password.getText().toString(),Category});
                //Toast.makeText(Register.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                break;

            case R.id.bCancel:
                startActivity(new Intent(this, WelcomeScreen.class));
                break;
        }
    }

//    public void feedback(String result){
//        System.out.println("Result=========>" + result);
        //ShowToast("Some String",getApplicationContext());
//    }
}

