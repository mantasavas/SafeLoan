package mif.ps.psp.FirstAssignment.StrategyRealisation.AgeGroupDiscount;

import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.Model.BookInformation;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class AdultBookRent implements AgeGroupDiscount {
    final private double DISCOUNT_FOR_ELDERLY = 0.35;

    public AdultBookRent() {
        super();
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void getServiceInformation(List<BookInformation> books) {
        int numberOfBooks = books.size();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("(Strategy Method in action)Welcome to Adult book section!. We have a variety of book, ranging from fiction to romance, and with over " + numberOfBooks + " of books to choose from");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("These are the basic rules of our printing and renting services: ");
        System.out.println("1) To rent a book, we charge based on time you borrowed a book and prime price of the book itself. For printing it depends on format of page and number of pages your chosen book has");
        System.out.println("2) Books can be borrowed no longer when for 5 months. After five months, you will be asked to bring it back.");
        System.out.println("3) For all damaged books, you will have to pay full price of the book");
        System.out.println("Discount information:");
        System.out.println("1) For all adults, who have one or more kids in their family, we apply 30 discount for all book rent services");
        System.out.println("2) For all adults, who are older when 50, we have special offer! Even 40 percent discount just for you! So don't miss out");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    @Override
    @SuppressWarnings("Duplicates")
    public void applyDiscount(Scanner userInput, double totalPriceOfService, List<BookInformation> pickedBooks) {
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("(Strategy Method in action) To get 40 percent discount, you must be older when 50 years old. We only give discount if you provide identity ");
        System.out.println("1 => Yes, I can verify that I'm older when 60");
        System.out.println("2 => No, I'm not older when 60");
        int userinp = userInput.nextInt();
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        if (userinp > 0 && userinp < 3) {
            if (userinp == 1) {
                System.out.println("Congratulations, you are eligible for elderly discount, it will be applied automatically!");
                totalPriceOfService = totalPriceOfService - totalPriceOfService * DISCOUNT_FOR_ELDERLY;
            } else if (userinp == 2) {
                System.out.println("Sorry, you are not eligible for discount");

            }
        }
        System.out.println("Your list of books that we are going to apply discount are: ");
        int numOfBook = 1;
        for (BookInformation booksInfo : pickedBooks) {
            System.out.println(numOfBook + "- " + booksInfo.getBookTitle());
            numOfBook++;
        }

        double totalPriceOfServiceWithoutPVM = totalPriceOfService - totalPriceOfService * 0.21;
        System.out.println("------------------------After Discount----------------------");
        System.out.println("Total rent price with PVM: " + totalPriceOfService);
        System.out.println("Total rent price without PVM: " + totalPriceOfServiceWithoutPVM);
        System.out.println("-------------------------------------------------------------");
    }

    @SuppressWarnings("Duplicates")
    public void sendEmailWhenReady(List<BookInformation> pickedBooks) {
        final String username = "mantole.sav105281@gmail.com";
        final String password = "fortakas";
        String bookList = "";

        //Setting up properties for gmail account
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            for (BookInformation book : pickedBooks) {
                bookList += "Book name: " + book.getBookTitle() + ", price: " + book.getBookPrimePrice() + ", expiration date:" + book.getBookExpiredate() + "\n\n";
            }

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mantole.sav105281@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mantasavas@gmail.com"));
            message.setSubject("Your books are ready to take! We are waiting for you");
            message.setText("(Strategy Method in action) Dear our elder costumer," + "\n\n " +
                    "It's just a reminder, don't forget to bring books back to library. For every day you are late, you will have to pay 7 dollars fine!" +
                    "\n\n " + "Books you have taken:" + "\n\n" + bookList);

            Transport.send(message);
            System.out.println("Reminder has been sucesfully send");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
