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
public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button btnlogin, signUp;
    DBHelper DB;
    DBHelperBusiness DBbusiness;
    Switch sw2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        signUp = findViewById(R.id.btnsignup3);
        sw2 = findViewById(R.id.switch3);
        DBbusiness = new DBHelperBusiness(this);

        DB = new DBHelper(this);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    sw2.setText("Business");
                    btnlogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String user = username.getText().toString();
                            String pass = password.getText().toString();

                            if(user.equals("")||pass.equals(""))
                                Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                            else{
                                Boolean checkuserpass = DBbusiness.checkusernamepassword(user, pass);
                                if(checkuserpass==true){
                                    Toast.makeText(MainActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                                    Intent intent  = new Intent(getApplicationContext(), BusinessActivity.class);
                                    Bundle bundleClient = new Bundle();
                                    bundleClient.putString("username", user);
                                    bundleClient.putString("password", pass);
                                    intent.putExtras(bundleClient);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                }
                            }


                        }
                    });

                }else {
                    sw2.setText("Client");
                    btnlogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String user = username.getText().toString();
                            String pass = password.getText().toString();

                            if(user.equals("")||pass.equals(""))
                                Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                            else{
                                Boolean checkuserpass = DBbusiness.checkusernamepassword(user, pass);
                                if(checkuserpass==true){
                                    Toast.makeText(MainActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                                    Intent intent  = new Intent(getApplicationContext(), ClientActivity.class);
                                    Bundle bundleClient = new Bundle();
                                    bundleClient.putString("username", user);
                                    bundleClient.putString("password", pass);
                                    intent.putExtras(bundleClient);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                }
                            }


                        }
                    });
                }
            }
        });

    }
}