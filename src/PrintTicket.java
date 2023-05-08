import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

public class PrintTicket implements Printable {
    Ticket ticket;

    public PrintTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) {
        if (pageIndex != 0) {
            return NO_SUCH_PAGE;
        }

        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.BLACK);

        int x = (int) pf.getImageableX();
        int y = (int) pf.getImageableY();

        int textX = x + 50;
        int textY = y + 50;

        String tab = "    ";

        if(ticket!=null){
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            FontMetrics fm = g.getFontMetrics();
            g.drawString(tab + "Ticket Issue time:\n"+ ticket.getIssueTime(), textX, textY);
            textY += fm.getHeight() + 5;
            g.setFont(new Font("Arial", Font.PLAIN, 14));
            fm = g.getFontMetrics();
            g.drawString(tab + "Ticket ID: "+ticket.getID(), textX, textY);
            textY += fm.getHeight() + 5;
            g.setFont(new Font("Arial", Font.BOLD, 14));
            fm = g.getFontMetrics();
            g.drawString(tab + "Ticket Holder Name:\n"+ticket.getPassengerName(), textX, textY);
            textY += fm.getHeight() + 5;
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            fm = g.getFontMetrics();
            g.drawString(tab + "From:\n"+ ticket.getTrain().getSourceStation().getStationName()+", City: "+ticket.getTrain().getSourceStation().getCity(), textX, textY);
            textY += fm.getHeight() + 5;
            g.drawString(tab + "To:\n"+ ticket.getTrain().getDestinationStation().getStationName()+", City: "+ticket.getTrain().getDestinationStation().getCity(), textX, textY);
            textY += fm.getHeight() + 5;
            g.drawString(tab + "Train Departure time:\n"+ ticket.getDepartureTime(), textX, textY);
            textY += fm.getHeight() + 5;
            g.drawString(tab + "Train Arrival time:\n"+ ticket.getArrivalTime(), textX, textY);
            textY += fm.getHeight() + 5;
            g.drawString(tab + "Seat ID:\n"+ ticket.getSeatID(), textX, textY);
            textY += fm.getHeight() + 5;
            g.drawString(tab + "Departure time:\n"+ ticket.getTrain().getDepartureTime(), textX, textY);
            textY += fm.getHeight() + 5;
            g.drawString(tab + "Arrival time:\n"+ ticket.getTrain().getArrivalTime(), textX, textY);
            textY += fm.getHeight() + 5;
            g.drawString(tab + "Price:\n"+ ticket.getPrice(), textX, textY);
        }


        return PAGE_EXISTS;
    }


}
