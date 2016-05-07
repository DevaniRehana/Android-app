package edu.uta.eventapp.uta_event;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BroadcastEvent extends AppCompatActivity implements View.OnClickListener {

    EditText name, desc, date,time,location;
    socketAndroid SocketConn;
    Button broadcast;
    Session session;
    String session_email;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_event);

        name = (EditText) findViewById(R.id.bNameOfEvent);
        desc = (EditText) findViewById(R.id.bEventDescription);
        date = (EditText) findViewById(R.id.bDate);
        time = (EditText) findViewById(R.id.bTime);
        location = (EditText) findViewById(R.id.bLocation);
        broadcast = (Button) findViewById(R.id.bBroadcast);
        session = new Session(BroadcastEvent.this);
        session_email = session.getusename();
        System.out.println("Session Email" + session_email);

        name.setOnClickListener(this);
        desc.setOnClickListener(this);
        date.setOnClickListener(this);
        time.setOnClickListener(this);
        location.setOnClickListener(this);
        broadcast.setOnClickListener(this);

    }

    @Override
    public void onClick (View v){
            System.out.print("Abhishek Sharma");
            System.out.println(v.getId());
            System.out.println(R.id.bBroadcast);
        //switch (v.getId()) {
          //  case R.id.bBroadcast:
            SocketConn = new socketAndroid(BroadcastEvent.this);
            SocketConn.execute(new String[]{"Broadcast", session_email, name.getText().toString(), desc.getText().toString(), date.getText().toString(), time.getText().toString(), location.getText().toString()});
                //System.out.println("Line from register===="+socketAndroid.line.toString());
            //    break;
       // }

    }

}

