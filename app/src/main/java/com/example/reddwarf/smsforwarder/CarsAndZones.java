package com.example.reddwarf.smsforwarder;

/// This Class is here only to automate SMS parking paying for my two cars
/// in City of Zagreb
/// Renault Laguna and Fiat Punto

public class CarsAndZones {

    private Car laguna;
    private Car punto;
    private String[] zones;
    private String[] numbers;

    CarsAndZones(){
        laguna = new Car();
        punto = new Car();
        laguna.name = "laguna";
        laguna.shortName = "l";
        laguna.licencePlate = "";
        punto.name = "punto";
        punto.shortName = "p";
        punto.licencePlate = "";

        zones = new String[10];
        zones[0] = "1";
        zones[1] = "9";
        zones[2] = "2";
        zones[3] = "6";
        zones[4] = "8";
        zones[5] = "3";
        zones[6] = "5";
        zones[7] = "4";
        zones[8] = "7";
        zones[9] = "12";

        numbers = new String[10];
        numbers[0] = "700101";
        numbers[1] = "700109";
        numbers[2] = "700102";
        numbers[3] = "700106";
        numbers[4] = "700108";
        numbers[5] = "700103";
        numbers[6] = "700105";
        numbers[7] = "700104";
        numbers[8] = "700107";
        numbers[9] = "700101";
    }

    public Car GetCar(String name)
    {
        if( name.toLowerCase().equals(laguna.name) || name.toLowerCase().equals(laguna.shortName)) {
            return laguna;
        }else {
            return punto;
        }
    }

    public String GetZone(String zone)
    {
        for(int i=1; i<10; i++){
            if(zones[i].equals(zone.toLowerCase())){
                return numbers[i];
            }
        }
        return "";
    }
}