package com.example.autotrader;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class Signup extends AppCompatActivity {

    private TextInputEditText etName, etSurname, etPhone, etEmail,
                              etUsername, etPassword, etConfirmPass;
    private Button   btnSignup;
    private TextView txtSignin;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        db            = new DatabaseHelper(this);
        etName        = findViewById(R.id.etName);
        etSurname     = findViewById(R.id.etSurname);
        etPhone       = findViewById(R.id.etPhone);
        etEmail       = findViewById(R.id.etEmail);
        etUsername    = findViewById(R.id.etUsername);
        etPassword    = findViewById(R.id.etPassword);
        etConfirmPass = findViewById(R.id.etConfirmPass);
        btnSignup     = findViewById(R.id.SignupBtn);
        txtSignin     = findViewById(R.id.txtSignin);

        btnSignup.setOnClickListener(v -> attemptRegister());

        txtSignin.setOnClickListener(v -> {
            startActivity(new Intent(Signup.this, MainActivity.class));
            finish();
        });
    }

    private void attemptRegister() {
        String name        = etName.getText().toString().trim();
        String surname     = etSurname.getText().toString().trim();
        String phone       = etPhone.getText().toString().trim();
        String email       = etEmail.getText().toString().trim();
        String username    = etUsername.getText().toString().trim();
        String password    = etPassword.getText().toString().trim();
        String confirmPass = etConfirmPass.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            etName.setError("Name is required"); etName.requestFocus(); return;
        }
        if (TextUtils.isEmpty(surname)) {
            etSurname.setError("Surname is required"); etSurname.requestFocus(); return;
        }
        if (TextUtils.isEmpty(phone) || phone.length() < 10) {
            etPhone.setError("Enter a valid 10-digit phone number"); etPhone.requestFocus(); return;
        }
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter a valid email address"); etEmail.requestFocus(); return;
        }
        if (TextUtils.isEmpty(username) || username.length() < 4) {
            etUsername.setError("Username must be at least 4 characters"); etUsername.requestFocus(); return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters"); etPassword.requestFocus(); return;
        }
        if (!password.equals(confirmPass)) {
            etConfirmPass.setError("Passwords do not match"); etConfirmPass.requestFocus(); return;
        }
        if (db.usernameExists(username)) {
            etUsername.setError("Username already taken"); etUsername.requestFocus(); return;
        }

        boolean success = db.registerCustomer(name, surname, phone, email, username, password);

        if (success) {
            Toast.makeText(this, "Account created! Please sign in.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Signup.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
