package edu.uta.eventapp.uta_event;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class socketAndroid extends AsyncTask<String,Void,String> {
    ViewEvent viewevent;
    ViewDetailEvent vieweventdetail;
    AdminVerifyEvent adminVerifyEvent;
    AdminDetailEvent adminDetailEvent;
    Context context;
    Toast toast;
    //ArrayList<String> ViewEventArray = new ArrayList<String>();
    //String[] ViewEventArray = new String[];
    String[] events;

    socketAndroid(Context context) {
        this.context = context.getApplicationContext();
    }

    private static final String HOST = "ec2-52-37-178-204.us-west-2.compute.amazonaws.com";
    private static final int PORT = 7000;
    Register RegisterReturn;

    @Override
    protected String doInBackground(String... params) {
        String retValue = null;
        String line = "Waiting";
        String choice;
        try {
            //Socket Declaration...
            Socket socket = new Socket(HOST, PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String data;
            choice = params[0];
            System.out.println("Choice Is=================>" + choice);
            switch (choice) {
                case "Register":
                    data = Register(params);
                    out.write(data);
                    out.flush();
                    line = in.readLine();
                    // Closing Connection
                    out.close();
                    in.close();
                    socket.close();

                    // Verifying Result
                    if (line.equals("Done")) {
                        retValue = "Register+Done";
                        return (retValue);
                    } else {
                        retValue = "Register+Error";
                        return (retValue);
                    }

                case "Login":
                    data = Login(params);
                    out.write(data);
                    out.flush();
                    line = in.readLine();
                    // Closing Connection
                    out.close();
                    in.close();
                    socket.close();

                    if (line.equals("Verified")) {
                        retValue = "Login+Verified";
                        return (retValue);
                    } else {
                        retValue = "Login+Wrong Password";
                        return (retValue);
                    }

                case "Broadcast":
                    data = Broadcast(params);
                    out.write(data);
                    out.flush();
                    line = in.readLine();
                    // Closing Connection
                    out.close();
                    in.close();
                    socket.close();

                    if (line.equals("Submit")) {
                        retValue = "Broadcast+Submit";
                        return (retValue);
                    } else {
                        retValue = "Broadcast+Fail";
                        return (retValue);
                    }

                case "ViewEvent":

                    out.write("ViewEvent");
                    out.flush();
                    line = in.readLine();
                    out.close();
                    in.close();
                    socket.close();

                    if (line.equals("Failure")) {
                        retValue = "ViewEvent+Fail";
                        return (retValue);
                    } else {
                        String line1 = line.replace('[', ' ');
                        String line2 = line1.replace(']', ' ');
                        String line3 = line2.replace("'", " ");

                        events = line3.split(",");
                        retValue = "ViewEvent+Success";

                        return (retValue);
                    }



                case "ViewDetailEvent":
                    data = ViewEventDetail(params);
                    System.out.println(data);
                    out.write(data);
                    out.flush();
                    line = in.readLine();
                    out.close();
                    in.close();
                    socket.close();

                    if (line.equals("Failure")) {
                        retValue = "ViewEventDetail+Fail";
                        return (retValue);
                    }
                    else {
                        System.out.println("This is what you will get -=====> " + line);
                        String line1 = line.replace('[', ' ');
                        String line2 = line1.replace(']', ' ');
                        String line3 = line2.replace("'", " ");
                        events = line3.split(",");
                        retValue = "ViewEventDetail+Success";
                        return (retValue);
                    }

                case "AdminVerifyEvent":
                    out.write("AdminVerifyEvent");
                    out.flush();
                    line = in.readLine();
                    out.close();
                    in.close();
                    socket.close();

                    if (line.equals("Failure")) {
                        retValue = "AdminVerifyEvent+Fail";
                        return (retValue);
                    }
                    else {
                        String line1 = line.replace('[', ' ');
                        String line2 = line1.replace(']', ' ');
                        String line3 = line2.replace("'", " ");
                        events = line3.split(",");
                        retValue = "AdminVerifyEvent+Success";
                        return (retValue);
                    }


                case "AdminDetailEvent":
                    data = ViewEventDetail(params);
                    System.out.println(data);
                    out.write(data);
                    out.flush();
                    line = in.readLine();
                    out.close();
                    in.close();
                    socket.close();

                    if (line.equals("Failure")) {
                        retValue = "AdminEventDetail+Fail";
                        return (retValue);
                    }
                    else {
                        System.out.println("This is what you will get -=====> " + line);
                        String line1 = line.replace('[', ' ');
                        String line2 = line1.replace(']', ' ');
                        String line3 = line2.replace("'", " ");
                        events = line3.split(",");
                        retValue = "AdminDetailEvent+Success";
                        return (retValue);
                    }
                case "EventVerify":
                    data = ViewEventDetail(params);
                    System.out.println(data);
                    out.write(data);
                    out.flush();
                    line = in.readLine();
                    out.close();
                    in.close();
                    socket.close();

                    if (line.equals("Failure")) {
                        retValue = "EventVerify+Fail";
                        return (retValue);
                    }
                    else {
                        retValue = "EventVerify+Success";
                        return (retValue);
                    }

                case  "EventDecline":
                    data = ViewEventDetail(params);
                    System.out.println(data);
                    out.write(data);
                    out.flush();
                    line = in.readLine();
                    out.close();
                    in.close();
                    socket.close();

                    if (line.equals("Failure")) {
                        retValue = "EventDecline+Fail";
                        return (retValue);
                    }
                    else {
                        retValue = "EventDecline+Success";
                        return (retValue);
                    }

                case  "ResetPassword":
                    data = Login(params);
                    System.out.println(data);
                    out.write(data);
                    out.flush();
                    line = in.readLine();
                    out.close();
                    in.close();
                    socket.close();

                    if (line.equals("Failure")) {
                        retValue = "ResetPassword+Fail";
                        return (retValue);
                    }
                    else {
                        retValue = "ResetPassword+Success";
                        return (retValue);
                    }
                case  "AdminDeleteUser":
                    data = ViewEventDetail(params);
                    System.out.println(data);
                    out.write(data);
                    out.flush();
                    line = in.readLine();
                    out.close();
                    in.close();
                    socket.close();

                    if (line.equals("Failure")) {
                        retValue = "AdminDeleteUser+Fail";
                        return (retValue);
                    }
                    else {
                        retValue = "AdminDeletUser+Success";
                        return (retValue);
                    }

                case "ModifyEvent":
                    data = ViewEventDetail(params);
                    System.out.println(data);
                    out.write(data);
                    out.flush();
                    line = in.readLine();
                    out.close();
                    in.close();
                    socket.close();

                    if (line.equals("Failure")) {
                        retValue = "ViewEventDetail+Fail";
                        return (retValue);
                    }
                    else {
                        System.out.println("This is what you will get -=====> " + line);
                        String line1 = line.replace('[', ' ');
                        String line2 = line1.replace(']', ' ');
                        String line3 = line2.replace("'", " ");
                        events = line3.split(",");
                        retValue = "ViewEventDetail+Success";
                        return (retValue);
                    }
            }

        } catch (Exception e) {
            e.printStackTrace();
            retValue = "Failure";
        }

        return retValue;

    }


    protected void onPostExecute(String result) {
        //System.out.println("Can this be executed===>" + result);
        //
        if(result.equals("Register+Done")){
            Toast.makeText(context,"Registration Successful",Toast.LENGTH_LONG).show();
            //context.startActivity(new Intent(context, Login.class));
            Intent intent = new Intent(context, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else if (result.equals("Register+Error")) {
            Toast.makeText(context,"Registration Failed",Toast.LENGTH_LONG).show();
            //context.startActivity(new Intent(context, Register.class));

            Intent intent = new Intent(context, Register.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

        else if (result.equals("Login+Verified")) {
            Toast.makeText(context,"Login Successful",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, HomeScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

        else if ( result.equals("Login+Wrong Password")) {
            Toast.makeText(context,"Login Failed",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, WelcomeScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

        else if (result.equals("Broadcast+Submit")){
            Toast.makeText(context,"Submit:Under Admin Review",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, HomeScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

        else if (result.equals("Broadcast+Fail")) {
            Toast.makeText(context,"Failed:Check Internet Connectivity",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, HomeScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else if (result.equals("ViewEvent+Fail")) {
            Toast.makeText(context,"Failed:Check Internet Connectivity",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, HomeScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else if (result.equals("ViewEvent+Success")) {
            Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();
            for(int i=0;i< events.length;i++) {
                System.out.println("Events = " + events[i]);
            }
            viewevent = new ViewEvent();
            viewevent.populateListView(events);
        }
        else if (result.equals("ViewEventDetail+Fail")) {
            Toast.makeText(context,"Failed:Check Internet Connectivity",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, HomeScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else if (result.equals("ViewEventDetail+Success")) {
            Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();
            for(int i=0;i< events.length;i++) {
                System.out.println("Events = " + events[i]);
            }
            vieweventdetail = new ViewDetailEvent();
            vieweventdetail.viewdetailrespond(events);
        }

        else if (result.equals("AdminVerifyEvent+Fail")) {
            Toast.makeText(context,"Failed:Check Internet Connectivity",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, Admin_Console.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else if (result.equals("AdminVerifyEvent+Success")) {
            Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();
            for(int i=0;i< events.length;i++) {
                System.out.println("Events = " + events[i]);
            }
            adminVerifyEvent = new AdminVerifyEvent();
            adminVerifyEvent.populateListView(events);
        }

        else if (result.equals("AdminDetailEvent+Fail")) {
            Toast.makeText(context,"Failed:Check Internet Connectivity",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, HomeScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else if (result.equals("AdminDetailEvent+Success")) {
            Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();
            for(int i=0;i< events.length;i++) {
                System.out.println("Events = " + events[i]);
            }
            adminDetailEvent = new AdminDetailEvent();
            adminDetailEvent.admindetailrespond(events);
        }


        else if (result.equals("EventVerify+Fail")) {
            Toast.makeText(context,"Failed:Check Internet Connectivity",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, Admin_Console.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else if (result.equals("EventVerify+Success")) {
            Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, Admin_Console.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

        else if (result.equals("EventDecline+Fail")) {
            Toast.makeText(context,"Failed:Check Internet Connectivity",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, Admin_Console.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else if (result.equals("EventDecline+Success")) {
            Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, Admin_Console.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

        else if (result.equals("ResetPassword+Fail")) {
            Toast.makeText(context,"Failed:Check Internet Connectivity",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, Settings.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else if (result.equals("ResetPassword+Success")) {
            Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, HomeScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }


        else if (result.equals("AdminDeleteUser+Fail")) {
            Toast.makeText(context,"Failed:Check Internet Connectivity",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, AdminDeleteUser.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else if (result.equals("AdminDeleteUser+Success")) {
            Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, Admin_Console.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else {
            System.out.println(result);
            Toast.makeText(context,"Check Internet Connectivity",Toast.LENGTH_LONG).show();
        }
        //RegisterReturn = new Register();
        //RegisterReturn.feedback(result);
    }
    public String Register(String...params)
    {
        String methodName = params[0];
        String name = params[1];
        String email = params[2];
        String pwd = params[3];
        String Category = params[4];

        String data = methodName+","+name+","+email+","+pwd+","+Category;

        return(data);
    }
    public String Login(String...params)
    {
        String methodName = params[0];
        String email = params[1];
        String pwd = params[2];

        String data = methodName+","+email+","+pwd;

        return(data);

    }

    public String Broadcast(String...params) {

        String methodName = params[0];
        String session_email = params[1];
        String Eventname = params[2];
        String desc = params[3];
        String date = params[4];
        String time = params[5];
        String location = params[6];

        String data = methodName + "," + session_email + "," + Eventname + "," + desc + "," + date + "," + time + "," + location;
        return(data);
    }
    public String ViewEventDetail(String...params) {

        String methodName = params[0];
        String eventname = params[1];

        String data = methodName + "," + eventname;
        return(data);
    }

}
