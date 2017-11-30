package edu.brandeis.cs.housingapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import edu.brandeis.cs.housingapplication.domainmodels.User;
import edu.brandeis.cs.housingapplication.login.SessionService;
import edu.brandeis.cs.housingapplication.utils.NetworkUtils;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private TextView tvSignup;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.username = (EditText) findViewById(R.id.editText_username);
        this.password = (EditText) findViewById(R.id.ediText_password);
        this.tvSignup = (TextView) findViewById(R.id.tv_signup_prompt);
        this.btnLogin = (Button) findViewById(R.id.btn_login);
        setOnClickListeners();
        getSupportActionBar().setTitle(R.string.btn_login);
    }

    private void setOnClickListeners() {
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginTask().execute();
            }
        });
    }

    public class LoginTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            Map<String, String> queryParams = getParams();
            URL url = NetworkUtils.createUrl("login", queryParams);
            Log.d("LOGIN URL", url.toString());
            String currentUserJson = "";
            try {
                currentUserJson = NetworkUtils.doHttpGet(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return currentUserJson;
        }

        private Map<String, String> getParams() {
            Map<String, String> params = new HashMap<>();
            params.put("username", LoginActivity.this.username.getText().toString());
            params.put("password", LoginActivity.this.password.getText().toString());
            Log.d("QUERY PARAMS", params.toString());
            return params;
        }

        @Override
        protected void onPostExecute(String s) {
            ObjectMapper mapper = new ObjectMapper();
            Log.d("SERVER RESULT LOGIN", s);
            User currentUser = null;
            try {
                currentUser = mapper.readValue(s, User.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            SessionService sessionService = new SessionService(getApplicationContext());
            sessionService.updateLoggedInUser(currentUser.getUserName(), currentUser.getUserID());
            Log.d("SOMEONE LOGGED IN?", Boolean.toString(sessionService.isSomeoneLoggedIn()));
            Log.d("CURRENT USER", SessionService.CURRENT_USER_ID);
            Intent intent = new Intent(LoginActivity.this, UserProfileActivity.class);
            intent.putExtra("CURRENT_USER", currentUser.getUserID());
            startActivity(intent);
        }
    }
}
