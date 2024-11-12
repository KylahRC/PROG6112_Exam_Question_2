package MovieTicketFormPackage;

public class MovieTicketData implements IMovieTickets {
    private String movieName;
    private int numberOfTickets;
    private double ticketPrice;

//    I have no idea why this has to be here, but it does
    public MovieTicketData() { }

//    constructor
    public MovieTicketData(String movieName, int numberOfTickets, double ticketPrice) {
        this.movieName = movieName;
        this.numberOfTickets = numberOfTickets;
        this.ticketPrice = ticketPrice;
    }

//    total price calculator
    @Override
    public double CalculateTotalTicketPrice(int numberOfTickets, double ticketPrice) {
        double VAT = 0.14;
        return numberOfTickets * ticketPrice * (1 + VAT);
    }

//    validation
    @Override
    public boolean ValidateData(MovieTicketData movieTicketData) {
        if (movieTicketData.movieName == null || movieTicketData.movieName.isEmpty()) return false;
        if (movieTicketData.numberOfTickets <= 0) return false;
        if (movieTicketData.ticketPrice <= 0) return false;
        return true;
    }
}
