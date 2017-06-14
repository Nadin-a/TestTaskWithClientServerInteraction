package com.nadina.android.testtask.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nadina.android.testtask.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  First activity in application
 */
public class StartActivity extends AppCompatActivity {

    @BindView(R.id.reg_button) Button registrationButton;
    @BindView(R.id.auth_button)Button loginButton;

    public static final String LOGIN_INTENT = "LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
        ButterKnife.bind(this);

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg_intent = new Intent(StartActivity.this, AuthorizationActivity.class);
                startActivity(reg_intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log_intent = new Intent(StartActivity.this, AuthorizationActivity.class);
                log_intent.putExtra(LOGIN_INTENT, true);
                startActivity(log_intent);
                finish();
            }
        });
    }
}
