package com.example.autotrader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class CustomerDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_dashboard);

        String username = getIntent().getStringExtra("username");
        TextView tvWelcome = findViewById(R.id.tvWelcomeCustomer);
        if (tvWelcome != null && username != null) {
            tvWelcome.setText("Welcome, " + username);
        }

        MaterialButton btnViewVehicles  = findViewById(R.id.btnViewVehicles);
        MaterialButton btnViewBookings  = findViewById(R.id.btnViewBookings);
        MaterialButton btnUpdateDetails = findViewById(R.id.btnUpdateDetails);
        MaterialButton btnLogout        = findViewById(R.id.btnLogout);

        btnViewVehicles.setOnClickListener(v ->
                Toast.makeText(this, "My Vehicles – coming soon", Toast.LENGTH_SHORT).show());
        btnViewBookings.setOnClickListener(v ->
                Toast.makeText(this, "My Bookings – coming soon", Toast.LENGTH_SHORT).show());
        btnUpdateDetails.setOnClickListener(v ->
                Toast.makeText(this, "Update Details – coming soon", Toast.LENGTH_SHORT).show());

        btnLogout.setOnClickListener(v -> confirmLogout());
    }

    private void confirmLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Logout", (dialog, which) -> {
                    Intent intent = new Intent(CustomerDashboardActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        confirmLogout();
    }
}
