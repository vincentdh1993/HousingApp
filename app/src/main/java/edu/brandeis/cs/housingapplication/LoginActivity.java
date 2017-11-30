package edu.brandeis.cs.housingapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private TextView tvSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.username = (EditText) findViewById(R.id.editText_username);
        this.password = (EditText) findViewById(R.id.ediText_password);
        this.tvSignup = (TextView) findViewById(R.id.tv_signup_prompt);
        setOnClickListener();
        //TODO: THERE'S NOT MUCH I CAN REALLY DO UNTIL I FIGURE OUT THE ASYNC TASK, SO LETS' MOVE ON
        //TODO: ONCE A USER RETURNS THEN SAVE UPDATE SQLITE
    }

    private void setOnClickListener() {
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
            }
        });
    }
}
