package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    RadioGroup sizeRadioGroup;
    Spinner toppingSpinner;
    CheckBox extraCheeseCheckBox;
    CheckBox includeDeliveryCheckBox;
    EditText deliveryInstructionEditText;
    EditText nameEditTxt;
    EditText addressEditTxt;
    EditText phoneNumEditTxt;
    EditText emailEditTxt;
    Button submitButton;
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sizeRadioGroup = findViewById(R.id.sizeOfPizza);
        toppingSpinner = findViewById(R.id.toppingSpinner);
        extraCheeseCheckBox = findViewById(R.id.extraCheeseCheckBox);
        includeDeliveryCheckBox = findViewById(R.id.includeDeliveryCheckBox);
        nameEditTxt = findViewById(R.id.nameEditTxt);
        addressEditTxt = findViewById(R.id.addressEditTxt);
        phoneNumEditTxt = findViewById(R.id.phoneNumEditTxt);
        emailEditTxt = findViewById(R.id.emailEditTxt);
        submitButton = findViewById(R.id.submitButton);
        deliveryInstructionEditText = findViewById(R.id.specialInstructionsEditTxt);

        ArrayAdapter<CharSequence> toppingArrayAdapter = ArrayAdapter.createFromResource(this, R.array.topping_array, android.R.layout.simple_spinner_item);
        toppingArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toppingSpinner.setAdapter(toppingArrayAdapter);

        /**
         * When the submit button is clicked firstly validate if all required fields are filled
         * When validated create a new Intent passing all necessary data such as name, address,
         * phone number, email, delivery instructions and the total calculate price
         */
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validated = true;
                String name = nameEditTxt.getText().toString();
                String address = addressEditTxt.getText().toString();
                String phoneNum = phoneNumEditTxt.getText().toString();
                String email = emailEditTxt.getText().toString();
                String deliveryInstruction = deliveryInstructionEditText.getText().toString();
                total = calculateTotal();

                if (name.isEmpty()) {
                    nameEditTxt.setError("Enter Name");
                    validated = false;
                }
                if (address.isEmpty()) {
                    addressEditTxt.setError("Enter address");
                    validated = false;
                }
                if (phoneNum.isEmpty()) {
                    phoneNumEditTxt.setError("Enter phone number");
                    validated = false;
                }
                if (email.isEmpty()) {
                    emailEditTxt.setError("Enter email");
                    validated = false;
                }
                if (total == 0) {
                    validated = false;
                }

                if (validated) {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("name", name);
                    intent.putExtra("address", address);
                    intent.putExtra("phoneNum", phoneNum);
                    intent.putExtra("email", email);
                    intent.putExtra("deliveryInstruction", deliveryInstruction);
                    intent.putExtra("total", total);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Calculate the pizza price
     * @return total price for the pizza purchase
     */
    private double calculateTotal() {
        total = 0;
        int selectedSizeId = sizeRadioGroup.getCheckedRadioButtonId();
        RadioButton sizeRadioButton = findViewById(selectedSizeId);
        String size = sizeRadioButton.getText().toString();

        if (size.startsWith("Round Pizza 6")) {
            total += 5.5;
        } else if (size.startsWith("Round Pizza 8")) {
            total += 7.99;
        } else if (size.startsWith("Round Pizza 10")) {
            total += 9.5;
        } else if (size.startsWith("Round Pizza 12")) {
            total += 11.38;
        }

        String topping = toppingSpinner.getSelectedItem().toString();
        if (topping.startsWith("Mushroom")) {
            total += 5;
        } else if (topping.startsWith("Sun Dried Tomatoes")) {
            total += 5;
        } else if (topping.startsWith("Chicken")) {
            total += 7;
        } else if (topping.startsWith("Ground Beef")) {
            total += 8;
        } else if (topping.startsWith("Shrimps")) {
            total += 10;
        } else if (topping.startsWith("Pineapple")) {
            total += 5;
        } else if (topping.startsWith("Steak")) {
            total += 9;
        } else if (topping.startsWith("Avocado")) {
            total += 5;
        } else if (topping.startsWith("Tuna")) {
            total += 5;
        } else if (topping.startsWith("Broccoli")) {
            total += 8;
        }

        if (extraCheeseCheckBox.isChecked()) total += 5;
        if (includeDeliveryCheckBox.isChecked()) total += 5;

        return total;
    }
}