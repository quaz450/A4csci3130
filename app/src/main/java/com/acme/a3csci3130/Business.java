package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Business implements Serializable {

    public  String businessID;
    public  String name;
    public  String primaryBusiness;
    public String address;
    public String province;

    /**
     * default cunstructor
     */
    public Business() {}

    /**
     * @param businessID
     * @param name
     * @param address
     * @param primaryBusiness
     * @param province
     */
    public Business(String businessID, String name, String address, String primaryBusiness , String province){
       this.businessID = businessID;
       this.name = name;
       this.primaryBusiness = primaryBusiness;
       this.address = address;
       this.province = province;
    }

    /**
     * @return
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("businessID", businessID);
        result.put("name", name);
        result.put("primaryBusiness", primaryBusiness);
        result.put("address", address);
        result.put("province", province);
        return result;
    }
}
