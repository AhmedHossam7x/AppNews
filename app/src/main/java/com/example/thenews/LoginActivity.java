package com.example.thenews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    EditText editText_email, editText_password;
    CircleImageView circleImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

    }

    private void initView() {
        button= findViewById(R.id.btn_login_activity);
        editText_email= findViewById(R.id.eT_name_login_activity);
        editText_password= findViewById(R.id.eT_pass_login_activity);
        circleImageView= findViewById(R.id.CIV_login_activity);
        textView= findViewById(R.id.tv_signUp_login_activity);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            circleImageView.setImageURI(uri);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences= getSharedPreferences("ShareLoginData", MODE_PRIVATE);
        sharedPreferences.edit().putString("userName", editText_email.getText().toString()).apply();
        sharedPreferences.edit().putString("password", editText_password.getText().toString()).apply();
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences=getSharedPreferences("ShareLoginData", MODE_PRIVATE);
        String user= sharedPreferences.getString("userName", "");
        String pass= sharedPreferences.getString("password", "");
        editText_email.setText(user);
        editText_password.setText(pass);
    }
}