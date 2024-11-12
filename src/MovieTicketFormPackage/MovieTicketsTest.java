package MovieTicketFormPackage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTicketsTest
{
//    test to check price
    @Test
    void CalculateTotalTicketPrice_CalculatedSuccessfully()
    {
        IMovieTickets testClass = new MovieTicketData();
        int numberOfTickets = 3;
        double ticketPrice = 100;
        double VAT = 0.14;
        double expected = numberOfTickets * ticketPrice * (1 + VAT);
        double actual = testClass.CalculateTotalTicketPrice(numberOfTickets, ticketPrice);
        assertEquals(expected, actual, 0.1);
    }

//    test to check validation
    @Test
    void ValidationTest_ValidMovieName()
    {
        IMovieTickets testClass = new MovieTicketData();

        // Test with valid movieName
        MovieTicketData validMovieTicketData = new MovieTicketData("Oppenheimer", 1, 10.0);
        boolean isValid = testClass.ValidateData(validMovieTicketData);
        assertTrue(isValid);

        // Test with invalid (empty) movieName
        MovieTicketData invalidMovieTicketData = new MovieTicketData("", 1, 10.0); // Invalid movieName
        isValid = testClass.ValidateData(invalidMovieTicketData);
        assertFalse(isValid);
    }

}

