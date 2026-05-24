package com.example.autotrader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView signupText;
    EditText txtAdminName, txtAdminPass;
    Button signinBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        signupText   = findViewById(R.id.txtSignup);
        signinBtn    = findViewById(R.id.SigninBtn);
        txtAdminName = findViewById(R.id.IDtxtAdmin);
        txtAdminPass = findViewById(R.id.passtxtAdmin);

        // ── Sign In ──────────────────────────────────────────────────────────
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtAdminName.getText().toString().trim();
                String password = txtAdminPass.getText().toString().trim();

                // Basic input validation
                if (username.isEmpty()) {
                    txtAdminName.setError("Username is required");
                    txtAdminName.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    txtAdminPass.setError("Password is required");
                    txtAdminPass.requestFocus();
                    return;
                }

                // Check credentials against DB
                String role = db.loginUser(username, password);

                if (role == null) {
                    Toast.makeText(MainActivity.this,
                            "Invalid username or password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Route to the correct dashboard based on role
                Intent intent;
                switch (role) {
                    case "admin":
                        intent = new Intent(MainActivity.this, MenuActivity.class);
                        break;
                    case "mechanic":
                        intent = new Intent(MainActivity.this, MenuActivity.class);
                        break;
                    case "customer":
                        intent = new Intent(MainActivity.this, MenuActivity.class);
                        break;
                    default:
                        Toast.makeText(MainActivity.this,
                                "Unknown role, contact admin", Toast.LENGTH_SHORT).show();
                        return;
                }

                // Pass the role and username through so the next screen knows who logged in
                intent.putExtra("role", role);
                intent.putExtra("username", username);
                Toast.makeText(MainActivity.this,
                        "Welcome, " + username + "!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish(); // prevent going back to login with back button
            }
        });

        // ── Go to Sign Up ─────────────────────────────────────────────────────
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Signup.class));
            }
        });
    }
}