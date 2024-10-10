package services;
import com.google.gson.Gson;

import io.restassured.response.Response;
import models.User;
import utilities.ApiUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UserPage {
    
    // Fetch users from the API
    public Response getUsers() {
        return ApiUtils.getUsers();
    }

    // Filter users based on latitude and longitude (FanCode city)
    public List<User> getFanCodeUsers(Response usersResponse) {
        Gson gson = new Gson();
       try {
        User[] users = gson.fromJson(usersResponse.asString(), User[].class);

        // Filter users based on geo coordinates for "FanCode"
        return List.of(users).stream()
                .filter(user ->{
                    double lat = Double.parseDouble(user.getAddress().geo.lat);
                    double lng = Double.parseDouble(user.getAddress().geo.lng);
                    return lat > -40 && lat < 5 && lng > 5 && lng < 100;
                })
                .collect(Collectors.toList());
       }
       catch(Exception e) {
    	   
    	   e.printStackTrace();
    	   return null;
    }
}
    }
