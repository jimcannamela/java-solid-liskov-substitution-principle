package com.galvanize;

import static com.galvanize.Passenger.AgeGroup.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.galvanize.Passenger.AgeGroup;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class FlightTest {

    @Test
    public void flightsWork() {
        Ticket ticket1 = new Ticket(new Passenger("Aaron", ADULT), new BigDecimal("210.00"));
        Ticket ticket2 = new Ticket(new Passenger("Theodosia", ADULT), new BigDecimal("190.00"));
        Ticket ticket3 = new Ticket(new Passenger("Peggy", CHILD), new BigDecimal("198.00"));

        Flight flight = new Flight("DEN", "LGA", 2);

        flight.addTicket(ticket1);
        flight.addTicket(ticket2);
        flight.addTicket(ticket3);

        assertEquals("DEN", flight.getDepartingAirport());
        assertEquals("LGA", flight.getArrivingAirport());

        assertEquals(
                Arrays.asList(ticket1, ticket2),
                flight.getTickets(),
                "Tickets for flight"
        );

        assertEquals(
                new BigDecimal("400.00"),
                flight.getRevenue(),
                "Flight revenue"
        );
    }
}
