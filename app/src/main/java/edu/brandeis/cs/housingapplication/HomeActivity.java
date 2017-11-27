package edu.brandeis.cs.housingapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private boolean flagmale = false;
    private boolean flagfemale = false;

    RadioButton House;
    RadioButton landLord;
    EditText searching;
    Button Search;
    Button Post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        House = (RadioButton)findViewById(R.id.radioButton3);
        landLord = (RadioButton) findViewById(R.id.radioButton4);
        searching = (EditText) findViewById(R.id.editText4);
        Search = (Button) findViewById(R.id.button3);
        Post = (Button) findViewById(R.id.button4);


        House.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (House.isChecked()) {
                    if (!flagmale) {
                        House.setChecked(true);
                        landLord.setChecked(false);
                        flagmale = true;
                        flagfemale = false;
                    } else {
                        flagmale = false;
                        House.setChecked(false);
                        landLord.setChecked(false);
                    }
                }
            }
        });

        landLord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (landLord.isChecked()) {
                    if (!flagfemale) {
                        landLord.setChecked(true);
                        House.setChecked(false);
                        flagfemale = true;
                        flagmale = false;
                    } else {
                        flagfemale = false;
                        landLord.setChecked(false);
                        House.setChecked(false);
                    }
                }
            }
        });

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = "";
                if(flagfemale == true){
                    temp = "Land Lord";
                    Toast.makeText(HomeActivity.this, temp + searching.getText().toString(), Toast.LENGTH_SHORT).show();
                    // Move to search results.
                } else {
                    temp = "House";
                    Toast.makeText(HomeActivity.this, temp + searching.getText().toString(), Toast.LENGTH_SHORT).show();
                    // Move to search results.
                }


            }
        });

        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Posting", Toast.LENGTH_SHORT).show();
                //Move to posting page
            }
        });




    }


}
