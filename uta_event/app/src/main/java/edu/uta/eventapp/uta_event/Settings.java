package edu.uta.eventapp.uta_event;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity implements View.OnClickListener{


    Button resetpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        resetpwd = (Button)findViewById(R.id.bResetPwd);

        resetpwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch ((view.getId())){
            case R.id.bResetPwd:
                startActivity(new Intent(this, ResetPassword.class));
                break;
        }
    }
}