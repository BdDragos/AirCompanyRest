package model;

import com.fasterxml.jackson.annotation.JsonView;
import controller.View;

/**
 * Created by Dragos on 5/20/2017.
 */
public class Flight
{
    @JsonView(View.Summary.class)
    private int flightId;

    @JsonView(View.Summary.class)
    private String destination;

    @JsonView(View.Summary.class)
    private String airport;

    @JsonView(View.Summary.class)
    private int freeseats;

    @JsonView(View.Summary.class)
    private String datehour;

    public Flight()
    {

    }

    public Flight(int id, String destin, String airp, int frst, String dh)
    {
        this.flightId=id;
        this.destination=destin;
        this.airport=airp;
        this.freeseats=frst;
        this.datehour=dh;
    }

    public String getDatehour() {

        return datehour;
    }

    public void setDatehour(String datehour) {

        this.datehour = datehour;
    }

    public int getFreeseats() {

        return freeseats;
    }

    public void setFreeseats(int freeseats) {

        this.freeseats = freeseats;
    }

    public String getAirport() {

        return airport;
    }

    public void setAirport(String airport) {

        this.airport = airport;
    }

    public String getDestination() {

        return destination;
    }

    public void setDestination(String destination) {

        this.destination = destination;
    }


    public int getFlightId()
    {
        return flightId;
    }

    public void setFlightId(int flightId)
    {
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "destination='" + destination + '\'' +
                ", airport='" + airport + '\'' +
                ", freeseats=" + freeseats +
                ", datehour=" + datehour +
                '}';
    }

}
