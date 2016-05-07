package edu.uta.eventapp.uta_event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminDeleteUser extends AppCompatActivity implements View.OnClickListener{

    socketAndroid SocketConn;
    Button deleteUser;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_user);

        email = (EditText) findViewById(R.id.tdeleteUser);
        deleteUser  = (Button) findViewById(R.id.bdeleteUser);

        deleteUser.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.bdeleteUser:
                // session.setusename(email.getText().toString());
                SocketConn = new socketAndroid(AdminDeleteUser.this);
                SocketConn.execute(new String[] {"AdminDeleteUser",email.getText().toString()});
                //Toast.makeText(Register.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                break;
        }

    }
}

