package com.example.finaltestjava;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;
    DBHelperBusiness DBbusiness;
    Switch sw1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        sw1 = findViewById(R.id.switch1);
        DBbusiness = new DBHelperBusiness(this);
        DB = new DBHelper(this);

        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    sw1.setText("Business");
                    signup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String user = username.getText().toString();
                            String pass = password.getText().toString();
                            String repass = repassword.getText().toString();
                            String serviceDetails = "";

                            if(user.equals("")||pass.equals("")||repass.equals(""))
                                Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                            else{
                                if(pass.equals(repass)){
                                    Boolean checkuser = DBbusiness.checkusername(user);
                                    if(checkuser==false){
                                        Boolean insert = DBbusiness.insertData(user, pass, serviceDetails);
                                        if(insert==true){
                                            Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(), "Passwords not matching", Toast.LENGTH_SHORT).show();
                                }
                            } }
                    });
                }else {
                    sw1.setText("Client");
                    signup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String user = username.getText().toString();
                            String pass = password.getText().toString();
                            String repass = repassword.getText().toString();
                            String serviceDetails = "";

                            if(user.equals("")||pass.equals("")||repass.equals(""))
                                Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                            else{
                                if(pass.equals(repass)){
                                    Boolean checkuser = DB.checkusername(user);
                                    if(checkuser==false){
                                        Boolean insert = DB.insertData(user, pass, serviceDetails);
                                        if(insert==true){
                                            Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(), "Passwords not matching", Toast.LENGTH_SHORT).show();
                                }
                            } }
                    });
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}