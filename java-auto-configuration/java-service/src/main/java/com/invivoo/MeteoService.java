package com.invivoo;

public class MeteoService implements IMeteoService {

    private String temperature;

    public MeteoService(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public void displayMeteo() {

        System.out.println("il fait " + temperature);
    }
}
