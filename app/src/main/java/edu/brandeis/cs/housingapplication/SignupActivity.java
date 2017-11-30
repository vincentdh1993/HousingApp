package edu.brandeis.cs.housingapplication;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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

public class SignupActivity extends AppCompatActivity {
    private static final Integer UPLOAD_PIC = 1;
    private static final Integer REQUEST_PERMISSION = 2;

    private EditText username;
    private EditText name;
    private EditText password;
    private CheckBox isLandlord;
    private Button btnPicture;
    private String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getReferences();
        setButtonClickHandler();
    }

    private void getReferences() {
        this.username = (EditText) findViewById(R.id.editText_usernameSignup);
        this.name = (EditText) findViewById(R.id.editText_nameSignup);
        this.password = (EditText) findViewById(R.id.editText_pwSignup);
        this.isLandlord = (CheckBox) findViewById(R.id.isLandlord);
        this.btnPicture = (Button) findViewById(R.id.btn_uploadPic);
    }

    private void setButtonClickHandler() {
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
