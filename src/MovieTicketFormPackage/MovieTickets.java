package MovieTicketFormPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MovieTickets extends JFrame implements ActionListener {
    // Declarations
    private JComboBox<String> movieTicketName;
    private JTextField numTickets, price;
    private JTextArea reportArea;
    private MovieTicketData movieTicketData;

    // Actual form buildy thing
    public MovieTickets() {
        movieTicketData = new MovieTicketData();
        setTitle("Movie Tickets");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Making the menu part
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu toolsMenu = new JMenu("Edit");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem processMenuItem = new JMenuItem("Process");
        JMenuItem clearMenuItem = new JMenuItem("Clear");

        exitMenuItem.addActionListener(this);
        processMenuItem.addActionListener(this);
        clearMenuItem.addActionListener(this);

        toolsMenu.add(processMenuItem);
        toolsMenu.add(clearMenuItem);
        toolsMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);


        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Movie:"));
        movieTicketName = new JComboBox<>(new String[]{"Napoleon", "Oppenheimer", "Damsel"});
        inputPanel.add(movieTicketName);
        inputPanel.add(new JLabel("Number of tickets:"));
        numTickets = new JTextField();
        inputPanel.add(numTickets);
        inputPanel.add(new JLabel("Ticket Price:"));
        price = new JTextField();
        inputPanel.add(price);

        // This is to make the report area uneditable (read only)
        reportArea = new JTextArea();
        reportArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(reportArea), BorderLayout.CENTER);

        setVisible(true);
    }

    // Make something happen when menu is interacted with
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Exit":
                System.exit(0);
                break;
            case "Process":
                processReport();
                break;
            case "Clear":
                clearFields();
                break;
        }
    }

    // Adds text to text area and saves it to report.txt
    private void processReport() {
        String movieName = (String) movieTicketName.getSelectedItem();
        int numberOfTickets = Integer.parseInt(this.numTickets.getText());
        double ticketPrice = Double.parseDouble(this.price.getText());

        MovieTicketData movieTicketData = new MovieTicketData(movieName, numberOfTickets, ticketPrice);

        if (this.movieTicketData.ValidateData(movieTicketData)) {
            // Printing part
            double totalPrice = this.movieTicketData.CalculateTotalTicketPrice(numberOfTickets, ticketPrice);
            reportArea.setText(String.format("Movie: %s%nNumber of Tickets: %d%nTicket Price: %.2f%nTotal Price: %.2f",
                    movieName, numberOfTickets, ticketPrice, totalPrice));

            // Saving part
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"))) {
                writer.write(reportArea.getText());
                JOptionPane.showMessageDialog(this, "Report saved to report.txt", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving report", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            reportArea.setText("Invalid data. Please check the inputs.");
        }
    }

    // This clears everything
    private void clearFields() {
        movieTicketName.setSelectedIndex(0);
        numTickets.setText("");
        price.setText("");

        reportArea.setText("");
    }

    // Runs the program
    public static void main(String[] args) {
        new MovieTickets();
    }
}
