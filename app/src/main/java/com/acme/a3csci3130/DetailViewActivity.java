package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class DetailViewActivity extends Activity {

    private EditText nameField, addressField;
    private Spinner provinceField, businessField;
    private MyApplicationData appState;
    ArrayAdapter<CharSequence> provinceAdapter, businessAdapter;

    Business receivedBusinessInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (Business)getIntent().getSerializableExtra("Business");

        appState = ((MyApplicationData) getApplicationContext());


        nameField = (EditText) findViewById(R.id.name);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (Spinner) findViewById(R.id.province);
        businessField = (Spinner) findViewById(R.id.primary_business);

        provinceAdapter = ArrayAdapter.createFromResource(this,
                R.array.province_array, android.R.layout.simple_spinner_item);
        businessAdapter = ArrayAdapter.createFromResource(this,
                R.array.primary_business_array, android.R.layout.simple_spinner_item);

        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        businessAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        provinceField.setAdapter(provinceAdapter);
        businessField.setAdapter(businessAdapter);

        if(receivedBusinessInfo != null){
            nameField.setText(receivedBusinessInfo.name);
            addressField.setText(receivedBusinessInfo.address);
            provinceField.setSelection(provinceAdapter.getPosition(receivedBusinessInfo.province));
            businessField.setSelection(businessAdapter.getPosition(receivedBusinessInfo.primaryBusiness));
        }
    }

    public void updateBusiness(View v){
        String name = nameField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getSelectedItem().toString();
        String primaryBusiness = businessField.getSelectedItem().toString();

        appState.firebaseReference.child(receivedBusinessInfo.businessID)
                .child("name").setValue(name);
        appState.firebaseReference.child(receivedBusinessInfo.businessID)
                .child("address").setValue(address);
        appState.firebaseReference.child(receivedBusinessInfo.businessID)
                .child("province").setValue(province);
        appState.firebaseReference.child(receivedBusinessInfo.businessID)
                .child("primaryBusiness").setValue(primaryBusiness);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void eraseBusiness(View v) {
        appState.firebaseReference.child(receivedBusinessInfo.businessID).removeValue();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
