package edu.uta.eventapp.uta_event;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdminDetailEvent extends AppCompatActivity implements View.OnClickListener {

    socketAndroid SocketConn;
    Button verify;
    Button decline;

    TextView event_name,event_description,date,time,location;
    Session session;
    String EventNameDetail;
    static Activity thisActivity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_event);
        thisActivity = this;
        event_name = (TextView) findViewById(R.id.bNameOfEvent);
        event_description = (TextView) findViewById(R.id.bEventDescription);
        date = (TextView) findViewById(R.id.bDate);
        time = (TextView) findViewById(R.id.bTime);
        location = (TextView) findViewById(R.id.bLocation);

        session = new Session(AdminDetailEvent.this);
        EventNameDetail = session.geteventdetail();


      /*  event_name.setOnClickListener(this);
        event_description.setOnClickListener(this);
        date.setOnClickListener(this);
        time.setOnClickListener(this);
        location.setOnClickListener(this);  */

        decline = (Button) findViewById(R.id.bdecline);
        verify = (Button) findViewById(R.id.bverify);

        decline.setOnClickListener(this);
        verify.setOnClickListener(this);

        SocketConn = new socketAndroid(AdminDetailEvent.this);
        SocketConn.execute(new String[] {"AdminDetailEvent",EventNameDetail});
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bverify:

                SocketConn = new socketAndroid(AdminDetailEvent.this);
                SocketConn.execute(new String[]{"EventVerify", EventNameDetail});
                //Toast.makeText(Register.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                break;

            case R.id.bdecline:
                // session.setusename(email.getText().toString());
                SocketConn = new socketAndroid(AdminDetailEvent.this);
                SocketConn.execute(new String[]{"EventDecline", EventNameDetail});
                //Toast.makeText(Register.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                break;
        }

    }
    public void admindetailrespond(String[] events) {
        for(int i=0;i< events.length;i++) {
            System.out.println("Events In thiS Class= " + events[i]);
        }
        event_name = (TextView) thisActivity.findViewById(R.id.vNameOfEvent);
        event_description = (TextView) thisActivity.findViewById(R.id.vEventDescription);
        date    = (TextView) thisActivity.findViewById(R.id.bDate);
        time       = (TextView) thisActivity.findViewById(R.id.bTime);
        location = (TextView) thisActivity.findViewById(R.id.bLocation);

        //abhishek = (TextView) thisActivity.findViewById(R.id.abhishek);
        String name = events[0];
        event_name.setText(name);

        String description = events[1];
        event_description.setText(description);
        //thisActivity.printtext(events);

        String event_date = events[2];
        date.setText(event_date);

        String event_time = events[3];
        time.setText(event_time);

        String event_location = events[4];
        location.setText(event_location);
    }
}