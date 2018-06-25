package com.three.cse.computerapplicationdesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                Intent searchResultIntent = new Intent(LoginActivity.this, MainPageActivity.class);
                startActivity(searchResultIntent);
            }
        });

        sellerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchResultIntent = new Intent(LoginActivity.this, SellerMainPageActivity.class);
                startActivity(searchResultIntent);
            }
        });
    }
}
