package edu.uta.eventapp.uta_event;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AdminVerifyEvent extends AppCompatActivity {
    socketAndroid SocketConn;
    static Activity thisActivity = null;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_verify_event);
        thisActivity = this;
        SocketConn = new socketAndroid(AdminVerifyEvent.this);
        SocketConn.execute(new String[]{"AdminVerifyEvent"});
    }

    public void populateListView(String[] events) {
        for(int i=0;i< events.length;i++) {
          System.out.println("Events Details = " + events[i]);
        //String[] items = {"Event1","Event2","Event3"};
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                thisActivity,
                R.layout.adminverifyevent, events);

        ListView list = (ListView) thisActivity.findViewById(R.id.AdminItems);
        list.setAdapter(adapter);
        registerClickCallBack();
    }

    public void registerClickCallBack() {
        ListView list = (ListView) thisActivity.findViewById(R.id.AdminItems);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                String value = textView.getText().toString();
                System.out.println(value);
                session = new Session(AdminVerifyEvent.thisActivity);
                session.seteventdetail(value);
                Intent intent = new Intent(thisActivity, AdminDetailEvent.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                thisActivity.startActivity(intent);
                //startActivity(new Intent(thisActivity, ViewDetailEvent.class));
                //String message = "You clicked " + position + " which is string: " + textView.getText().toString();
                //Toast.makeText(ViewEvent.thisActivity, message, Toast.LENGTH_LONG).show();
            }

        });

    }
}

