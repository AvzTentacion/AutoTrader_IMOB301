package com.example.autotrader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

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

        // These are LinearLayouts in your XML, NOT MaterialButtons
        LinearLayout btnAddCustomer   = findViewById(R.id.Button);
        LinearLayout btnAddVehicle    = findViewById(R.id.Button2);
        LinearLayout btnViewBookings  = findViewById(R.id.Button3);
        LinearLayout btnAddMechanic   = findViewById(R.id.Button4);
        LinearLayout btnCreateBooking = findViewById(R.id.Button5);

        // This one IS a MaterialButton in your XML
        MaterialButton btnLogout = findViewById(R.id.btnLogout);

        btnAddCustomer.setOnClickListener(v ->
                startActivity(new Intent(this, AddCustomerActivity.class)));

        btnAddVehicle.setOnClickListener(v ->
                startActivity(new Intent(this, AddVehicleActivity.class)));

        btnViewBookings.setOnClickListener(v ->
                startActivity(new Intent(this, ViewBookingsActivity.class)));

        btnAddMechanic.setOnClickListener(v ->
                startActivity(new Intent(this, AddMechanicActivity.class)));

        btnCreateBooking.setOnClickListener(v ->
                startActivity(new Intent(this, CreateBookingActivity.class)));

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