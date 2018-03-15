package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class CreateBusinessAcitivity extends Activity{

    private EditText nameField, addressField;
    private Spinner provinceField, businessField;
    private MyApplicationData appState;

    ArrayAdapter<CharSequence> provinceAdapter, businessAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

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
        String businessID = idGenerator();
        String name = nameField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getSelectedItem().toString();
        String businessType = businessField.getSelectedItem().toString();
        Business business = new Business(businessID, name, address, businessType, province);

        appState.firebaseReference.child(businessID).setValue(business);

        finish();
    }

    public String idGenerator(){
        String zeros = "00000000", stringID, concat;
        long id = Math.round((Math.random()*1000000000)*100)/100;
        String id_num= Long.toString(id);
        if(id < 100000000){
           concat =  zeros.concat(id_num);
           stringID = concat.substring(concat.length() - 9);
        }
        else
            stringID = id_num;

        return stringID;
    }
}
