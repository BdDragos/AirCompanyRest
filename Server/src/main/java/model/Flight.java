package model;
import java.io.Serializable;

public class Flight implements Serializable
{
    private static final long serialVersionUID = 5950169519310163575L;
    private int flightId;
    private String destination;
    private String airport;
    private int freeseats;
    private String datehour;

    public Flight(int id, String destin, String airp, int frst, String dh)
    {
        this.flightId=id;
        this.destination=destin;
        this.airport=airp;
        this.freeseats=frst;
        this.datehour=dh;
    }

    public Flight()
    {

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
        return "Zboruri{" +
                "destination='" + destination + '\'' +
                ", airport='" + airport + '\'' +
                ", freeseats=" + freeseats +
                ", datehour=" + datehour +
                '}';
    }

}
