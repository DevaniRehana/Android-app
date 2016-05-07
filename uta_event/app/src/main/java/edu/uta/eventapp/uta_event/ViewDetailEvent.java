package edu.uta.eventapp.uta_event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Abhishek Sharma on 1/5/16.
 */
public class ViewDetailEvent extends AppCompatActivity implements View.OnClickListener {
    Session session;
    String EventNameDetail;
    socketAndroid SocketConn;
    RadioButton bRadioButton1,bRadioButton2,bRadioButton3;
    Button home;
    //EditText description,date,time,location;
    //TextView name;
    TextView name,description,date,time,location;
    static Activity thisActivity = null;

    public void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_detail_event);
            thisActivity = this;
            bRadioButton1 = (RadioButton) findViewById(R.id.bRadioButton1);
            bRadioButton2 = (RadioButton) findViewById(R.id.bRadioButton2);
            bRadioButton3 = (RadioButton) findViewById(R.id.bRadioButton3);

            name = (TextView) findViewById(R.id.bname);
            description = (TextView) findViewById(R.id.bevent);
            date    = (TextView) findViewById(R.id.bdate);
            time       = (TextView) findViewById(R.id.btime);
            location = (TextView) findViewById(R.id.blocation);


        //abhishek.setText("Sharma");
            //description = (EditText) findViewById(R.id.bEventDescription);
            //date = (EditText) findViewById(R.id.bDate);
            //time = (EditText) findViewById(R.id.bTime);
            //location = (EditText) findViewById(R.id.bTime);

            //name.setText("Abhishek Sharma");
            //description.setText("Patrick What you have made");
            home = (Button) findViewById(R.id.bHome);
            home.setOnClickListener(this);

            session = new Session(ViewDetailEvent.this);
            EventNameDetail = session.geteventdetail();

            SocketConn = new socketAndroid(ViewDetailEvent.this);
            SocketConn.execute(new String[] {"ViewDetailEvent",EventNameDetail});

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bHome:

                if (bRadioButton1.isChecked()) {
                    String Interested = "true";
                    System.out.println("Interested.....");
                } else if (bRadioButton2.isChecked()) {
                    String NotInterested = "true";
                    System.out.println("Not Interested.....");
                } else if (bRadioButton3.isChecked()) {
                    String Report = "true";
                    System.out.println("Report.....");
                }
                startActivity(new Intent(this, HomeScreen.class));
        }
    }


    public void viewdetailrespond(String[] events) {
        for(int i=0;i< events.length;i++) {
            System.out.println("Events In thiS Class= " + events[i]);
        }
        name = (TextView) thisActivity.findViewById(R.id.bname);
        description = (TextView) thisActivity.findViewById(R.id.bevent);
        date    = (TextView) thisActivity.findViewById(R.id.bdate);
        time       = (TextView) thisActivity.findViewById(R.id.btime);
        location = (TextView) thisActivity.findViewById(R.id.blocation);

        //abhishek = (TextView) thisActivity.findViewById(R.id.abhishek);
        String event_name = events[0];
        name.setText(event_name);

        String event_description = events[1];
        description.setText(event_description);
        //thisActivity.printtext(events);

        String event_date = events[2];
        date.setText(event_date);

        String event_time = events[3];
        time.setText(event_time);

        String event_location = events[4];
        location.setText(event_location);
    }

}
