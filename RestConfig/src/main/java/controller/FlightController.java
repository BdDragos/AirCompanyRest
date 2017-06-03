package controller;

import com.fasterxml.jackson.annotation.JsonView;
import model.Flight;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import repository.FlightRepo;
import services.FlightList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 5/20/2017.
 */
@EnableAsync
@RestController
@RequestMapping("/company/flight")
public class FlightController
{
    private static final String template = "Hello, %s!";

    @Autowired
    private FlightRepo repo;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, name);
    }

    @JsonView(View.Summary.class)
    @RequestMapping( method= RequestMethod.GET)
    public FlightList getAll(){
        return repo.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable int id){

        Flight fl = repo.findById(id);
        if (fl==null)
            return new ResponseEntity<String>("Flight not found",HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Flight>(fl, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Flight create(@RequestBody Flight fl){
        System.out.println(fl);
        System.out.println("*****************");
        repo.save(fl);
        return fl;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Flight update(@RequestBody Flight fl) {
        System.out.println("Updating flight ...");
        repo.update(fl.getFlightId(),fl);
        return fl;

    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id){
        System.out.println("Deleting flight ... " + id);
        repo.deleteFlight(id);
        return new ResponseEntity<User>(HttpStatus.OK);

    }


    @RequestMapping("/{flight}/id")
    public String name(@PathVariable int id){
        Flight result=repo.findById(id);
        System.out.println("Result ..." + result);

        return result.getAirport();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String flightError(Exception e) {
        return e.getMessage();
    }


}
