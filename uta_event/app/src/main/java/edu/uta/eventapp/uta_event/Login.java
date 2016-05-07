package edu.uta.eventapp.uta_event;

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

public class Login extends AppCompatActivity  implements View.OnClickListener {
    socketAndroid SocketConn;
    Button login, forgotPwd;
    EditText email, password;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.bEmailLogin);
        password = (EditText) findViewById(R.id.bPasswordLogin);
        login = (Button) findViewById(R.id.bLoginLogin);
        forgotPwd = (Button) findViewById(R.id.bForgotPwd);

        login.setOnClickListener(this);
        forgotPwd.setOnClickListener(this);
        session = new Session(Login.this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.bLoginLogin:
                session.setusename(email.getText().toString());
                SocketConn = new socketAndroid(Login.this);
                SocketConn.execute(new String[] {"Login",email.getText().toString(),password.getText().toString()});
                //Toast.makeText(Register.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                break;

            case R.id.bForgotPwd:

                break;
        }

    }
}