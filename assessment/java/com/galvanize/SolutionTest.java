package com.galvanize;

import com.galvanize.util.ClassProxy;
import com.galvanize.util.InstanceProxy;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.galvanize.Passenger.AgeGroup.ADULT;
import static com.galvanize.Passenger.AgeGroup.CHILD;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

    static <V> TypeToken<List<V>> listToken(TypeToken<V> itemToken) {
        return new TypeToken<List<V>>() {}
                .where(new TypeParameter<V>() {}, itemToken);
    }

    @Test
    public void aTest() {
        ClassProxy _Passenger = ClassProxy.classNamed("com.galvanize.Passenger")
                .ensureConstructor(String.class, Passenger.AgeGroup.class, Integer.TYPE);

        ClassProxy _Ticket = ClassProxy.classNamed("com.galvanize.Ticket")
                .ensureConstructor(_Passenger, BigDecimal.class);

        ClassProxy _Flight = ClassProxy.classNamed("com.galvanize.Flight")
                .ensureConstructor(String.class, String.class, Integer.TYPE, Integer.TYPE)
                .ensureMethod(m -> m
                        .isPublic()
                        .returns(Void.TYPE)
                        .withParameters(_Ticket)
                        .named("addTicket")
                )
                .ensureMethod(m -> m
                        .isPublic()
                        .returns(listToken(TypeToken.of(_Ticket.getDelegate())))
                        .named("getTickets")
                );

        ClassProxy _CharterFlight = ClassProxy.classNamed("com.galvanize.CharterFlight")
                .ensureConstructor(String.class, String.class, Integer.TYPE, Integer.TYPE)
                .ensureMethod(m -> m
                        .isProtected()
                        .returns(Integer.TYPE)
                        .named("totalWeight")
                )
                .ensureMethod(m -> m
                        .isPublic()
                        .returns(Void.TYPE)
                        .withParameters(Boolean.TYPE)
                        .named("setCatered")
                );

        InstanceProxy passenger1 = _Passenger.newInstance("Abe", ADULT, 250);
        InstanceProxy passenger2 = _Passenger.newInstance("Ben", CHILD, 250);

        InstanceProxy ticket1 = _Ticket.newInstance(passenger1, new BigDecimal("10.00"));
        InstanceProxy ticket2 = _Ticket.newInstance(passenger2, new BigDecimal("10.00"));

        InstanceProxy flight = _Flight.newInstance("DEN", "LGA", 10, 400);

        flight.invoke("addTicket", ticket1);
        flight.invoke("addTicket", ticket2);

        List<Object> tickets = (List<Object>) flight.invoke("getTickets");

        assertEquals(
                1,
                tickets.size(),
                "Expected `Flight` to not add tickets that exceed the weight limit.\n\n" +
                        "Passenger 1 = 250\n" +
                        "Adding Passenger 2 (250) would take it over the limit of 400\n"
        );

        InstanceProxy rawCharterFlight = _CharterFlight.newInstance("DEN", "LGA", 10, 500);
        rawCharterFlight.invoke("setCatered", true);
        InstanceProxy charterFlight = new InstanceProxy(rawCharterFlight.getDelegate(), _Flight);

        charterFlight.invoke("addTicket", ticket1);
        charterFlight.invoke("addTicket", ticket2);

        List<Object> charterTickets = (List<Object>) charterFlight.invoke("getTickets");

        assertEquals(
                1,
                charterTickets.size(),
                "Expected `CharterFlight` to not add tickets that exceed the weight limit.\n\n" +
                        "Passenger 1 = 250\n" +
                        "Catering weight = Average Adult (173) + Food (50)\n" +
                        "Total = 473\n\n" +
                        "Adding Passenger 2 (250) would take it over the limit of 500\n"
        );

    }
}
