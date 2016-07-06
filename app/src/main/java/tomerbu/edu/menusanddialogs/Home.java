package tomerbu.edu.menusanddialogs;

import android.support.v7.app.AlertDialog;

/**
 * Created by stud27 on 06/07/16.
 */
public class Home {
    private String address;
    private String city;


    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static class Builder{
        private Home home = new Home();

        public Builder setAddress(String address){
            home.setAddress(address);
            return this;
        }

        public Builder setCity(String city){
            home.setCity(city);
            return this;
        }

        public Home show() {
            return home;
        }
    }
}
