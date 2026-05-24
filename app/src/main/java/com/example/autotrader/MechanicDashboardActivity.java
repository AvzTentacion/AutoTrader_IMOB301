package com.example.autotrader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MechanicDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mechanic_dashboard);

        String username = getIntent().getStringExtra("username");
        TextView tvWelcome = findViewById(R.id.tvWelcomeMechanic);
        if (tvWelcome != null && username != null) {
            tvWelcome.setText("Welcome, " + username);
        }

        MaterialButton btnCreateBooking = findViewById(R.id.btnCreateBooking);
        MaterialButton btnViewAssigned  = findViewById(R.id.btnViewAssigned);
        MaterialButton btnUpdateStatus  = findViewById(R.id.btnUpdateStatus);
        MaterialButton btnLogout        = findViewById(R.id.btnLogout);

        btnCreateBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MechanicDashboardActivity.this, Add_booking.class);
                startActivity(intent);
            }
        });

                //Toast.makeText(this, "Create Booking – coming soon", Toast.LENGTH_SHORT).show());
        btnViewAssigned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MechanicDashboardActivity.this, View_Booking.class);
                startActivity(intent);
            }
        });
                //Toast.makeText(this, "View Assigned – coming soon", Toast.LENGTH_SHORT).show());
        btnUpdateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MechanicDashboardActivity.this, Update_service.class);
                startActivity(intent);
            }
        });
                //Toast.makeText(this, "Update Status – coming soon", Toast.LENGTH_SHORT).show());

        btnLogout.setOnClickListener(v -> confirmLogout());
    }

    private void confirmLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Logout", (dialog, which) -> {
                    Intent intent = new Intent(MechanicDashboardActivity.this, MainActivity.class);
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
