package com.example.android.my_log_in_app;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private static EditText userName;
    private static EditText passWord;
    private static TextView attempts;
    private static Button login;
    int count=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        onLoginClickListener();
    }

    private void onLoginClickListener(){

        userName = (EditText)findViewById(R.id.editText);
        passWord = (EditText)findViewById(R.id.editText2);
        attempts = (TextView) findViewById(R.id.tView_attempts);
        login = (Button) findViewById(R.id.button);

        attempts.setText("Attempts Remaining : " + Integer.toString(count));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userName.getText().toString().equals("user")
                        && passWord.getText().toString().equals("pass")){
                    Intent intent = new Intent(".Details");
                    startActivity(intent);
                }else{
                    if(count == 0)
                        login.setEnabled(false);
                    else {
                        if(count == 1)
                            Toast.makeText(Login.this,"Maximum Attempts Complete",Toast.LENGTH_SHORT);

                        Intent intent = new Intent(".Retry");
                        startActivityForResult(intent, 1);
                    }

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                attempts.setText("Attempts Remaining : " + Integer.toString(--count));
            }
        }
    }
}