package com.three.cse.computerapplicationdesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.three.cse.computerapplicationdesign.activities.BaseActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button customerLoginButton = (Button) findViewById(R.id.login_for_customer);
        Button sellerLoginButton = (Button) findViewById(R.id.login_for_seller);

        customerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent customerLoginIntent = new Intent(LoginActivity.this, MainPageActivity.class);
                BaseActivity.isSeller=false;
                startActivity(customerLoginIntent);
            }
        });

        sellerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sellerLoginIntent = new Intent(LoginActivity.this, MainPageActivity.class);
                BaseActivity.isSeller=true;
                startActivity(sellerLoginIntent);
            }
        });
    }
}
