package edu.brandeis.cs.housingapplication;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import edu.brandeis.cs.housingapplication.domainmodels.User;
import edu.brandeis.cs.housingapplication.login.SessionService;
import edu.brandeis.cs.housingapplication.utils.NetworkUtils;

public class SignupActivity extends AppCompatActivity {
    private static final Integer UPLOAD_PIC = 1;
    private static final Integer REQUEST_PERMISSION = 2;

    private EditText username;
    private EditText name;
    private EditText password;
    private CheckBox isLandlord;
    private Button btnPicture;
    private Button btnSignup;
    private String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getReferences();
        setButtonClickHandlers();
        getSupportActionBar().setTitle(R.string.signup);
    }

    private void getReferences() {
        this.username = (EditText) findViewById(R.id.editText_usernameSignup);
        this.name = (EditText) findViewById(R.id.editText_nameSignup);
        this.password = (EditText) findViewById(R.id.editText_pwSignup);
        this.isLandlord = (CheckBox) findViewById(R.id.isLandlord);
        this.btnPicture = (Button) findViewById(R.id.btn_uploadPic);
        this.btnSignup = (Button) findViewById(R.id.btn_signup);
    }

    private void setButtonClickHandlers() {
        this.btnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                ActivityCompat.requestPermissions(SignupActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
                startActivityForResult(intent, UPLOAD_PIC);
            }
        });

        this.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: FIGURE OUT WHAT THE FUCK TO DO WITH THE IMAGE PATH
                Log.d("THE URL", NetworkUtils.createUrl("users").toString());
                new CreateUserTask().execute(NetworkUtils.createUrl("users"));
            }
        });
    }

    //Apparently not making this static creates a memory leak. Does anyone care? No.
    public class CreateUserTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            ObjectMapper mapper = new ObjectMapper();
            URL url = urls[0];
            Log.d("URL IN ASYNCTASK", url.toString());
            String newUser = "";
            try {
                newUser = NetworkUtils.doHttpPost(url, mapper.writeValueAsString(SignupActivity.this.buildUser()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newUser;
        }

        @Override
        protected void onPostExecute(String s) {
            //Once account has been created, redirect user to login
            //Ideally we would do some fancy automatic login, but oh well
            ObjectMapper mapper = new ObjectMapper();
            SessionService sessionService = new SessionService(getApplicationContext());
            User newUser = null;
            try {
                newUser = mapper.readValue(s, User.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("DESERIALIZED USER:", newUser.toString());
            sessionService.addUser(newUser.getUserName());
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        }

    }

    private User buildUser() {
        String userName = this.username.getText().toString();
        String name = this.name.getText().toString();
        String password = this.password.getText().toString();
        boolean isTenant = !this.isLandlord.isChecked();
        return new User(name, userName, password, imgPath, isTenant);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("ACTIVITY RESULT: ", Integer.toString(requestCode));
        if (resultCode == RESULT_OK) {
            if (requestCode == UPLOAD_PIC) {
                Uri picture = data.getData();
                imgPath = getPath(picture);
                Log.d("IMAGE PATH", imgPath);
            }
        }
    }


    //More of less directly lifted from:
    //https://stackoverflow.com/questions/2169649/get-pick-an-image-from-androids-built-in-gallery-app-programmatically
    private String getPath(Uri picture) {
        String filePath = "";
        String id = DocumentsContract.getDocumentId(picture).split(":")[1];
        String[] column = {MediaStore.Images.Media.DATA};
        String selection = MediaStore.Images.Media._ID + "=?";
        Cursor cursor = this.getApplicationContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, selection, new String[]{id}, null);
        int columnIndex = cursor.getColumnIndex(column[0]);
        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }
}
