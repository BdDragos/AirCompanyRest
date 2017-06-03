package repository;

import model.Flight;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import services.FlightList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepo
{
    private FlightList flights = new FlightList();
    private Statement statement;
    private Connection connection;

    public FlightRepo() throws SQLException
    {
        String url = "jdbc:mysql://localhost:3306/flights";
        String user = "root";
        String password = "";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, user, password);

            statement = connection.createStatement();
            statement.execute("USE flights");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String query = "select * from routes";
        ResultSet result = statement.executeQuery(query);
        while(result.next())
        {
            Integer id = result.getInt("Id");
            String dest = result.getString("Destination");
            String airp = result.getString("Airport");
            Integer frees = result.getInt("FreeSeats");
            String dateh = result.getString("Date");
            Flight flight = new Flight(id,dest,airp,frees,dateh);

            flights.add(flight);
        }
    }

    public FlightList getAll()
    {
        return flights;
    }


    public FlightList findByDestinationAndDate(String dest, String data)
    {
        FlightList rez = new FlightList();
        for(Flight flight : flights)
            if(flight.getDestination().equals(dest) && flight.getDatehour().compareTo(data) == 0)
                rez.add(flight);
        return rez;
    }

    public FlightList findByDestination(String dest)
    {
        FlightList rez = new FlightList();
        for(Flight flight : flights)
            if(flight.getDestination().equals(dest))
                rez.add(flight);
        return rez;
    }

    public FlightList findByDate(String data)
    {
        FlightList rez = new FlightList();
        for(Flight flight : flights)
            if(flight.getDatehour().compareTo(data) == 0)
                rez.add(flight);
        return rez;
    }

    public void deleteFlight(int id)
    {
        for (Flight f: flights)
        {
            if (f.getFlightId() == id)
            {
                try
                {
                    String query = "delete from routes where Id = ?";
                    PreparedStatement preparedStmt = connection.prepareStatement(query);
                    preparedStmt.setInt(1, id);
                    preparedStmt.execute();
                    flights.remove(f);
                }
                catch (Exception e)
                {
                    System.err.println("Got an exception! ");
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public Flight findById(int id)
    {
        for (Flight f: flights)
            if (f.getFlightId() == id)
                return f;
        return null;
    }

    public void update(int id,Flight fl)
    {
        for (Flight f: flights)
        {
            if(f.getFlightId() == id)
            {
                try
                {
                    String query = "update routes set FreeSeats=? , Airport=?, Destination=? where Id = ?";
                    PreparedStatement preparedStmt = connection.prepareStatement(query);
                    preparedStmt.setInt(1, fl.getFreeseats());
                    preparedStmt.setString(2, fl.getAirport());
                    preparedStmt.setString(3, fl.getDestination());
                    preparedStmt.setInt(4, id);
                    preparedStmt.executeUpdate();

                    flights.get(f.getFlightId()-1).setFreeseats(fl.getFreeseats());
                    if (flights.get(f.getFlightId()-1).getFreeseats() == 0)
                    {
                        deleteFlight(f.getFlightId());
                    }

                }
                catch (Exception e)
                {
                    System.err.println("Got an exception! ");
                    System.err.println(e.getMessage());
                }
            }
        }
    }


    public void save(Flight fl)
    {
        try {
            String query = " insert into routes (Id,Destination, Airport, FreeSeats, Date)"
                    + " values (?,?, ?, ?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, fl.getFlightId());
            preparedStmt.setString(2, fl.getDestination());
            preparedStmt.setString(3, fl.getAirport());
            preparedStmt.setInt(4, fl.getFreeseats());
            preparedStmt.setString(5, fl.getDatehour());
            preparedStmt.execute();
            flights.add(fl);
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
