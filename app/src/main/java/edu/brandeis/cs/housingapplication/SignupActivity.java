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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import edu.brandeis.cs.housingapplication.domainmodels.User;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

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
                //TODO: BUNDLE UP THE DATA, POST TO BACKEND
                //TODO: FIGURE OUT WHAT THE FUCK TO DO WITH THE IMAGE PATH
                Log.d("THE URL", createUrl().toString());
                new CreateUserTask().execute(createUrl());
            }
        });
    }

    public class CreateUserTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            Log.d("URL IN ASYNCTASK", url.toString());
            String newUser = "";
            try {
                newUser = doHttp(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newUser;
        }

        @Override
        protected void onPostExecute(String s) {
            //todo: use this info to update db for current user
            Log.d("RESULT FROM BACKGROUND", s);
        }

        private String doHttp(URL url) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            OkHttpClient client = new OkHttpClient();
            RequestBody body = null;
            try {
                body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                        mapper.writeValueAsString(buildUser()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            Request request = new Request.Builder().url(url).post(body).addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .build();
            printBody(request);
            Log.d("REQUEST", request.toString());
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.body().string();
        }

        private void printBody(Request request) {
            try {
                Request copy = request.newBuilder().build();
                Buffer buffer = new Buffer();
                copy.body().writeTo(buffer);
                Log.d("REQUEST BODY", buffer.readUtf8());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private URL createUrl() {
        Uri uri = Uri.parse("http://10.0.2.2:8080").buildUpon()
                .appendPath("users").build();
        try {
            return new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null; //you're fucked
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
