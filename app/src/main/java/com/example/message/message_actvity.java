package com.example.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class message_actvity extends AppCompatActivity {
    private FloatingActionButton contact;
    private EditText phoneNumber;
    private  Button send;
    private EditText message;
    private final static int REQUEST_CODE_PERMISSION_SEND_SMS = 123;
    private final static int REQUEST_CODE_PERMISSION_READ_SMS = 456;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_actvity);
        contact = findViewById(R.id.addContact);
        phoneNumber = findViewById(R.id.phoneNumber);
        message = findViewById(R.id.messagebody);
        send = (Button) findViewById(R.id.send);


        if(checkPermission(Manifest.permission.SEND_SMS)){
            send.setEnabled(true);
        }else{
            ActivityCompat.requestPermissions(message_actvity.this, new String[] {
                    (Manifest.permission.SEND_SMS)}, REQUEST_CODE_PERMISSION_SEND_SMS);
        }
        if(checkPermission(Manifest.permission.READ_CONTACTS))
        {

        }
        else{
            ActivityCompat.requestPermissions(message_actvity.this,new String[]
                    {(Manifest.permission.READ_CONTACTS)},1);
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = message.getText().toString();
                String phoneNum = phoneNumber.getText().toString();

                SmsManager smsMan =  SmsManager.getDefault();
                smsMan.sendTextMessage(phoneNum, null, msg, null, null);
                Toast.makeText(message_actvity.this,
                        "SMS send to " + phoneNum, Toast.LENGTH_LONG).show();
            }
        });


        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(message_actvity.this,contact_activety.class);
                startActivity(intent);
            }
        });


    }






    private boolean checkPermission(String permission){
        int checkPermission = ContextCompat.checkSelfPermission(this, permission);
        return checkPermission == PackageManager.PERMISSION_GRANTED;
    }

}
