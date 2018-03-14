package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateBusinessAcitivity extends Activity{

    private Button submitButton;
    private EditText nameField, addressField;
    private Spinner provinceField, businessField;
    private MyApplicationData appState;

    ArrayAdapter<CharSequence> provinceAdapter, businessAdapter;


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (Spinner) findViewById(R.id.province);
        businessField = (Spinner) findViewById(R.id.primary_business);

        provinceAdapter = ArrayAdapter.createFromResource(this,
                R.array.province_array, android.R.layout.simple_spinner_item);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceField.setAdapter(provinceAdapter);


        businessAdapter = ArrayAdapter.createFromResource(this,
                R.array.primary_business_array, android.R.layout.simple_spinner_item);
        businessAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        businessField.setAdapter(businessAdapter);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String businessID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getSelectedItem().toString();
        String businessType = businessField.getSelectedItem().toString();
        Business business = new Business(businessID, name, address, businessType, province);

        appState.firebaseReference.child(businessID).setValue(business);

        finish();
    }
}
