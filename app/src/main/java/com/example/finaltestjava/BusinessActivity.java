package com.example.finaltestjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BusinessActivity extends AppCompatActivity {
    TextView tvWelcome, tvService;
    EditText serviceDetailsText;
    Button buttonClient;
    DBHelperBusiness DBbusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        tvWelcome = findViewById(R.id.textView);
        serviceDetailsText = findViewById(R.id.businessText);
        buttonClient = findViewById(R.id.businessButton);
        tvService = findViewById(R.id.textView5);
        DBbusiness = new DBHelperBusiness(this);

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");
        String password = bundle.getString("password");
        tvService.setText("Button to add service !");


        tvWelcome.setText("Welcome " + username + "/Business !");

        buttonClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serviceText = serviceDetailsText.getText().toString();

                DBbusiness.updateData(username, password, serviceText);

                tvService.setText("New Service Updated as " + serviceText +"!");
            }
        });

    }
}