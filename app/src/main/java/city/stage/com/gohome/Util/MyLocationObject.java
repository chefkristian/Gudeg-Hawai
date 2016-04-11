package city.stage.com.gohome.Util;

import android.location.Address;

/**
 * Created by korneliussendy on 1/23/16.
 */
public class MyLocationObject {

    double latitude, longitude;

    //LOCATION DETAILS///
    String locality, subAdminArea, adminArea, postalCode;
    String countryName, countryCode;
    String throughfare;
    //ADDRESS//
    Address address;

    public MyLocationObject(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public MyLocationObject(Address address) {
        this.address = address;
        this.latitude = address.getLatitude();
        this.longitude = address.getLongitude();
    }
}