package com.example.autotrader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        MaterialButton btnAddCustomer   = findViewById(R.id.button);
        MaterialButton btnAddVehicle    = findViewById(R.id.button2);
        MaterialButton btnAddMechanic   = findViewById(R.id.button3);
        MaterialButton btnCreateBooking = findViewById(R.id.button4);
        MaterialButton btnViewBookings  = findViewById(R.id.button5);
        MaterialButton btnLogout        = findViewById(R.id.btnLogout);

        btnAddCustomer.setOnClickListener(v ->
                Toast.makeText(this, "Add Customer – coming soon", Toast.LENGTH_SHORT).show());
        btnAddVehicle.setOnClickListener(v ->
                Toast.makeText(this, "Add Vehicle – coming soon", Toast.LENGTH_SHORT).show());
        btnAddMechanic.setOnClickListener(v ->
                Toast.makeText(this, "Add Mechanic – coming soon", Toast.LENGTH_SHORT).show());
        btnCreateBooking.setOnClickListener(v ->
                Toast.makeText(this, "Create Booking – coming soon", Toast.LENGTH_SHORT).show());
        btnViewBookings.setOnClickListener(v ->
                Toast.makeText(this, "View Bookings – coming soon", Toast.LENGTH_SHORT).show());

        btnLogout.setOnClickListener(v -> confirmLogout());
    }

    private void confirmLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Logout", (dialog, which) -> {
                    Intent intent = new Intent(MenuActivity.this, MainActivity.class);
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
