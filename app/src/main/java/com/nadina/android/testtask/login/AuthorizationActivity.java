package com.nadina.android.testtask.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nadina.android.testtask.App;
import com.nadina.android.testtask.R;
import com.nadina.android.testtask.UserActivity;
import com.nadina.android.testtask.model.LoginBody;
import com.nadina.android.testtask.model.RegistrationBody;
import com.nadina.android.testtask.model.RegistrationResponse;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Registration or authorization.
 */
public class AuthorizationActivity extends AppCompatActivity {

    @BindString(R.string.error_network)
    String error_network;
    @BindString(R.string.err_authorization)
    String error_authorization;
    @BindString(R.string.conflict)
    String error_conflict;

    @BindView(R.id.username_et)
    EditText username_et;
    @BindView(R.id.password_et)
    EditText password_et;
    @BindView(R.id.email_tv)
    TextView email_tv;
    @BindView(R.id.email_et)
    EditText email_et;
    @BindView(R.id.accept_btn)
    Button accept_btn;

    private boolean isLogin;
    private String username;
    private String password;
    private String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_data_layout);
        ButterKnife.bind(this);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity.hasExtra(StartActivity.LOGIN_INTENT)) {
            isLogin = getIntent().getExtras().getBoolean(StartActivity.LOGIN_INTENT);
        }

        if (isLogin) {
            email_tv.setVisibility(View.GONE);
            email_et.setVisibility(View.GONE);
        }


        accept_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                username = username_et.getText().toString();
                password = password_et.getText().toString();
                email = email_et.getText().toString();
                if(username.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(AuthorizationActivity.this, error_authorization, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isLogin) {
                    LoginBody body = new LoginBody(username,email,password);
                    App.getServerApi().login(body).enqueue(new Callback<RegistrationResponse>() {
                        @Override
                        public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                            if(response.isSuccessful())
                            {
                                    Intent intent = new Intent(AuthorizationActivity.this, UserActivity.class);
                                    startActivity(intent);
                                    finish();
                            }
                            else
                            {
                                Toast.makeText(AuthorizationActivity.this, error_authorization, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                            Toast.makeText(AuthorizationActivity.this, error_network, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    if(email.isEmpty())
                    {
                        Toast.makeText(AuthorizationActivity.this, error_authorization, Toast.LENGTH_SHORT).show();
                        return;
                    }
                   RegistrationBody  body = new RegistrationBody(username,password,email);
                    App.getServerApi().registration(body).enqueue(new Callback<RegistrationResponse>() {
                        @Override
                        public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                            if(response.isSuccessful())
                            {
                                if(response.body().getToken() == null)
                                {
                                    Toast.makeText(AuthorizationActivity.this, error_conflict, Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Intent intent = new Intent(AuthorizationActivity.this, StartActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                            else
                            {
                                Toast.makeText(AuthorizationActivity.this, error_conflict, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                            Toast.makeText(AuthorizationActivity.this, error_network, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }


        });
    }


}
