package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView nameTxtView, addressTxtView, phoneNumTxtView, emailTxtView, totalTxtView, deliveryInstructionTxt;
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nameTxtView = findViewById(R.id.nameTxt);
        addressTxtView = findViewById(R.id.addressTxt);
        phoneNumTxtView = findViewById(R.id.phoneNumTxt);
        emailTxtView = findViewById(R.id.emailTxt);
        totalTxtView = findViewById(R.id.totalTxt);
        deliveryInstructionTxt = findViewById(R.id.deliveryInstructionTxt);
        confirmButton = findViewById(R.id.confirmButton);

        // Get passed extras and print render on screen with format
        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("address");
        String phoneNum = getIntent().getStringExtra("phoneNum");
        String email = getIntent().getStringExtra("email");
        String deliveryInstruction = getIntent().getStringExtra("deliveryInstruction");
        double total = getIntent().getDoubleExtra("total", 0);

        nameTxtView.setText(name);
        addressTxtView.setText(address);
        phoneNumTxtView.setText(phoneNum);
        emailTxtView.setText(email);
        totalTxtView.setText("$" + String.format("%.2f", total));
        // if no delivery instruction is passed print "N/A" otherwise print the
        if (!deliveryInstruction.isEmpty()) {
            deliveryInstructionTxt.setText(deliveryInstruction);
        } else {
            deliveryInstructionTxt.setText("N/A");
        }

        /**
         * When "Confirm" is clicked return back to the MainActivity
         */
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}