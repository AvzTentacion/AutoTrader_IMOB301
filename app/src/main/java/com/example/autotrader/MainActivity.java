package com.example.autotrader;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etUsername, etPassword;
    private Button            btnSignIn;
    private TextView          txtSignup;
    private DatabaseHelper    db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        db          = new DatabaseHelper(this);
        etUsername  = findViewById(R.id.IDtxtAdmin);
        etPassword  = findViewById(R.id.passtxtAdmin);
        btnSignIn   = findViewById(R.id.SigninBtn);
        txtSignup   = findViewById(R.id.txtSignup);

        btnSignIn.setOnClickListener(v -> attemptLogin());

        txtSignup.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Signup.class)));
    }

    private void attemptLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        String role = db.loginUser(username, password);

        if (role == null) {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Welcome, " + username + "!", Toast.LENGTH_SHORT).show();

        Intent intent;
        switch (role) {
            case "admin":
                intent = new Intent(this, MenuActivity.class);
                break;
            case "mechanic":
                intent = new Intent(this, MechanicDashboardActivity.class);
                break;
            default:
                intent = new Intent(this, CustomerDashboardActivity.class);
                intent.putExtra("linkedId", db.getLinkedId(username));
                break;
        }
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }
}
