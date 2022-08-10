package com.example.finaltestjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ClientActivity extends AppCompatActivity {
    TextView tvWelcome, tvService;
    EditText serviceDetailsText;
    Button buttonClient;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        tvWelcome = findViewById(R.id.textView2);
        serviceDetailsText = findViewById(R.id.ClientText);
        buttonClient = findViewById(R.id.buttonClient);
        tvService = findViewById(R.id.textView6);
        DB = new DBHelper(this);

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");
        String password = bundle.getString("password");
        tvService.setText("Button to add Service !");

        tvWelcome.setText("Welcome " + username + "/Client !");

        buttonClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serviceText = serviceDetailsText.getText().toString();

                DB.updateData(username, password, serviceText);

                tvWelcome.setText("New Service Updated as " + serviceText +"!");
            }
        });

    }
}