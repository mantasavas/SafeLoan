package mif.ps.psp.FirstAssignment.StrategyRealisation.AgeGroupDiscount;

import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.Model.BookInformation;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ChildrenBookRent implements AgeGroupDiscount {
    final private double DISCOUNT_FOR_FAMILY_MEMBERS = 0.2;
    final private int DISCOUNT_FOR_AGE_ELIGIBLE = 13;
    final private double DISCOUNT_FOR_AGE = 0.6;

    public ChildrenBookRent() {
        super();
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void getServiceInformation(List<BookInformation> books) {
        int numberOfBooks = books.size();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("(Strategy Method in action) Welcome to Children book section!. We have a variety of book, ranging from fiction to romance, and with over " + numberOfBooks + " of books to choose from. All same discount rules applies to renting and printing services");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("These are the basic rules of our printing and renting services: ");
        System.out.println("1) To rent a book, we charge based on time you borrowed a book and prime price of the book itself. For printing it depends on format of page and number of pages your chosen book has");
        System.out.println("2) Books can be borrowed no longer when for 3 months. After three months, you will be asked to bring it back. Printed copies of the books are all yours for infinite time");
        System.out.println("3) For all damaged books, you will have to pay full price of the book");
        System.out.println("Discount information:");
        System.out.println("1) For all children under 13 we provide 60% discount for printing and renting services.");
        System.out.println("2) If your family has more when two kids those use our services, you are eligible for 20 percent discount");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    @Override
    @SuppressWarnings("Duplicates")
    public void applyDiscount(Scanner userInput, double totalPriceOfService, List<BookInformation> pickedBooks) {
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("(Strategy Method in action) To get 20% extra discount, at least two members of your family must be under 12, and use our services");
        System.out.println("1 => Yes, we have two or more members of our family using service");
        System.out.println("2 => No, we don't have more then two family members, who use our service");
        int userinp = userInput.nextInt();
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        if(userinp > 0 && userinp < 3)
        {
            if(userinp == 1)
            {
                System.out.println("Congratulations, you are eligible for family discount, it will be applied automatically!");
                totalPriceOfService = totalPriceOfService - totalPriceOfService * DISCOUNT_FOR_FAMILY_MEMBERS;
            }
            else if(userinp == 2)
            {
                System.out.println("Sorry, you are not eligible for discount");
            }
        }

        if(DISCOUNT_FOR_AGE_ELIGIBLE < 13)
        {
            System.out.println("Congratulations, you are eligible for age under 13 discount, it will be applied automatically!");
            totalPriceOfService = totalPriceOfService - totalPriceOfService * DISCOUNT_FOR_AGE;
        }

        System.out.println("Your list of books that we are going to apply discount are: ");
        int numOfBook = 1;
        for(BookInformation booksInfo : pickedBooks)
        {
            System.out.println(numOfBook + "- " + booksInfo.getBookTitle());
            numOfBook++;
        }

        double totalPriceOfServiceWithoutPVM = totalPriceOfService - totalPriceOfService * 0.21;
        System.out.println("------------------------After Discount----------------------");
        System.out.println("Total rent price with PVM: " + totalPriceOfService);
        System.out.println("Total rent price without PVM: " + totalPriceOfServiceWithoutPVM);
        System.out.println("-------------------------------------------------------------");
    }


    @Override
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

            for(BookInformation book : pickedBooks) {
                bookList += "Book name: " + book.getBookTitle() + ", price: " + book.getBookPrimePrice() + ", book genre: " + book.getGenre() + "\n\n";
            }

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mantole.sav105281@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mantasavas@gmail.com"));
            message.setSubject("Your books are ready to take! We are waiting for you");
            message.setText("(Strategy Method in action) Dear our costumer," + "\n\n" +
                    "It's just a reminder, that you have used our services recently, and you order is ready! You can find us on Vilnius Paukštyno g. 45" +
                    "\n\n" + "The books you have requested are:" + "\n\n" + bookList + "\n\n" + "Discount information:" + "\n\n" + "*For all children under 13 we provide 60% discount for printing and renting services."
                    + "\n\n" + "*If your family has more when two kids those use our services, you are eligible for 20 percent discount");

            Transport.send(message);
            System.out.println("Reminder has been sucesfully send");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
