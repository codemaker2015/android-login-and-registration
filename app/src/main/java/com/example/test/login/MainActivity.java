package com.example.test.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView txtSignUp;
    private EditText editTextEmail,editTextPassword;
    private Button btnLogin;

    private SQLiteDatabase db;
    private SQLiteOpenHelper openHelper;
    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        txtSignUp = findViewById(R.id.txtSignUp);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter your Email and Password to login", Toast.LENGTH_SHORT).show();
                } else {
                    cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_4 + "=? AND " + DatabaseHelper.COL_5 + "=?", new String[]{email, password});
                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            String username = "";
                            while (cursor.moveToNext())
                                username = cursor.getString(1);

                            Intent intent = new Intent(MainActivity.this, LoginSucess.class);
                            intent.putExtra("username", username);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                finish();
            }
        });

    }
}
