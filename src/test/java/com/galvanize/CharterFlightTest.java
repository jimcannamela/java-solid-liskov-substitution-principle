package com.galvanize;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.galvanize.Passenger.AgeGroup.ADULT;
import static com.galvanize.Passenger.AgeGroup.CHILD;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharterFlightTest {

    @Test
    public void charterFlightsCheckWeight() {
        Ticket ticket1 = new Ticket(new Passenger("Aaron", ADULT, 150), new BigDecimal("210.00"));
        Ticket ticket2 = new Ticket(new Passenger("Theodosia", ADULT, 150), new BigDecimal("190.00"));
        Ticket ticket3 = new Ticket(new Passenger("Peggy", CHILD), new BigDecimal("198.00"));

        Flight flight = new CharterFlight("DEN", "LGA", 2, 300);

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

    @Test
    public void charterFlightsCheckWeightPlusCatering() {
        Ticket ticket1 = new Ticket(new Passenger("Aaron", ADULT, 150), new BigDecimal("210.00"));
        Ticket ticket2 = new Ticket(new Passenger("Theodosia", ADULT, 150), new BigDecimal("190.00"));
        Ticket ticket3 = new Ticket(new Passenger("Peggy", CHILD), new BigDecimal("198.00"));

        CharterFlight flight = new CharterFlight("DEN", "LGA", 2, ticket1.getPassenger().getWeight() + ADULT.getAverageWeight() + 100);
        flight.setCatered(true);

        flight.addTicket(ticket1);
        flight.addTicket(ticket2);
        flight.addTicket(ticket3);

        assertEquals("DEN", flight.getDepartingAirport());
        assertEquals("LGA", flight.getArrivingAirport());

        assertEquals(
                Arrays.asList(ticket1),
                flight.getTickets(),
                "Tickets for flight"
        );

        assertEquals(
                new BigDecimal("210.00"),
                flight.getRevenue(),
                "Flight revenue"
        );
    }

    @Test
    public void charterFlightsChecksCapacity() {
        Ticket ticket1 = new Ticket(new Passenger("Aaron", ADULT, 150), new BigDecimal("210.00"));
        Ticket ticket2 = new Ticket(new Passenger("Theodosia", ADULT, 150), new BigDecimal("190.00"));

        CharterFlight flight = new CharterFlight("DEN", "LGA", 1, 300);
        flight.addTicket(ticket1);
        flight.addTicket(ticket2);

        assertEquals(
                Arrays.asList(ticket1),
                flight.getTickets(),
                "Tickets for flight"
        );

        assertEquals(
                new BigDecimal("210.00"),
                flight.getRevenue(),
                "Flight revenue"
        );
    }
}
