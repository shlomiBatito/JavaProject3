package il.co.bat.shlomi.javaproject.model.entities;

import android.content.ContentValues;
import android.location.Location;

import java.sql.Time;

public class Ride {
    TypeOfRide typeOfRide;
    Location startLocation;
    Location endLocation;
    Time startTime;
    Time endTime;
    String name;
    int celNumber;
    String email;
    public Ride(TypeOfRide typeOfRide, Location startLocation, Location endLocation, Time startTime, Time endTime, String name, int celNumber, String email) {
        this.typeOfRide = typeOfRide;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.celNumber = celNumber;
        this.email = email;
    }

    public Ride() {

    }

    public Location getStartLocation() {

        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }


    public TypeOfRide getTypeOfRide() {
        return typeOfRide;
    }

    public void setTypeOfRide(TypeOfRide typeOfRide) {
        this.typeOfRide = typeOfRide;
    }


    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCelNumber() {
        return celNumber;
    }

    public void setCelNumber(int celNumber) {
        this.celNumber = celNumber;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public Ride ContentValuesToStudent(ContentValues contentValues) {
        Ride ride  = new Ride();
        ride.setTypeOfRide(contentValues.get("typeOfRide"));
        ride.setStartLocation(contentValues.getAsString("startLocation"));
        ride.setEndLocation(contentValues.getAsString("endLocation"));
        ride.setStartTime(contentValues.getAsLong("startTime"));
        ride.setEndTime(contentValues.getAsTime("endTime"));
        ride.setName(contentValues.getAsString("name"));
        ride.setCelNumber(contentValues.getAsInteger("celnumber"));
        ride.setEmail(contentValues.getAsString("email"));
        return ride;
    }

    public  ContentValues CourseToContentValues(Ride ride) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("typeOfRide", ride.getTypeOfRide());
            contentValues.put("startLocation", ride.getStartLocation());
            contentValues.put("endLocation", ride.getEndLocation());
            contentValues.put("startTime", ride.getStartTime());
            contentValues.put("endTime", ride.getEndTime());
            contentValues.put("name", ride.getName());
            contentValues.put("celnumber", ride.getCelNumber());
            contentValues.put("email", ride.getEmail());
            return contentValues;
        }
