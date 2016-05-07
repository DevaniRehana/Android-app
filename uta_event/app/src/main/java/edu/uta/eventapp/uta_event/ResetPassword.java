package edu.uta.eventapp.uta_event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener{
    Button save;
    Button broadcast;
    EditText newpassword;
    Session session;
    String session_email;
    socketAndroid SocketConn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        newpassword = (EditText) findViewById(R.id.newpassword);
        save =(Button)findViewById(R.id.bSavePwd);
        save.setOnClickListener(this);
        session = new Session(ResetPassword.this);
        session_email = session.getusename();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.bSavePwd:
                SocketConn = new socketAndroid(ResetPassword.this);
                SocketConn.execute(new String[]{"ResetPassword", session_email, newpassword.getText().toString()});
                //System.out.println("Line from register===="+socketAndroid.line.toString());

                break;


        }

    }
}
