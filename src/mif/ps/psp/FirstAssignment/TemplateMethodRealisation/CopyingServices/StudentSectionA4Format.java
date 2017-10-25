package mif.ps.psp.FirstAssignment.TemplateMethodRealisation.CopyingServices;

import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.Model.BookInformation;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class StudentSectionA4Format extends CopyingServicesTemplate {
    final private double DISCOUNT_FOR_LOAYL_STUDENS = 0.1;
    final private double DISCOUNT_FOR_GOOD_STUDENTS = 0.30;

    public StudentSectionA4Format() {
        super();
    }

    @Override
    public void fulfillCopyingService() {
        super.fulfillCopyingService();
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void getServiceInformation() {
        int numberOfBooks = books.size();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Welcome to Student book section!. We have a variety of books (what student could need more?), ranging from fiction to romance, and with over " + numberOfBooks + " of books to choose from. All same discount rules applies to renting and printing services");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("These are the basic rules of our printing and renting services: ");
        System.out.println("1) To rent a book, we charge based on time you borrowed a book and prime price of the book itself. For printing it depends on format of page and number of pages your chosen book has");
        System.out.println("2) Books for students can be borrowed no longer when for a semester (~5 months). After semester has finished, you will be asked to bring it back. Printed copies of the books are all yours for infinite time");
        System.out.println("3) For all damaged books, you will have to pay full price of the book");
        System.out.println("Discount information:");
        System.out.println("1) For all Students, who will provide student identification, will receive 40 percent discount! Without it, we don't issue discounts.");
        System.out.println("2) For all Students who achieved amazing results in academics, will receive extra 30 percent discount. To obtain this your average grade must be above 8.5. If it's bellow, sorry we are not issuing any discounts!");
        System.out.println("3) For all students who have used our services for more when 2 years, will receive even more discount. We issue for this 10 percent");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    @Override
    @SuppressWarnings("Duplicates")
    public void pickPrinter() {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Please choose a printer, you wanna print you books with. Keep in mind we have a wide variety to pick from, so if you don't know which one is the best for your needs, just call us: 80887323495");
        System.out.println("Our printers capable of printing in A4 format are: ");
        System.out.println("1) PIXMA TS8120 Black Wireless");
        System.out.println("2) PIXMA TS6120 Black Wireless");
        System.out.println("3) PIXMA MG3020 Black Wireless Photo All-in-One ");
        System.out.println("4) PIXMA TS5020 Black Wireless ");
        System.out.print("Choose witch one you prefer: ");
        int option = Integer.parseInt(userInput.nextLine());

        if(option > 0 && option < 4) {
            switch (option) {
                case 1:
                    printerModel = "PIXMA TS8120 Black Wireless";
                    break;
                case 2:
                    printerModel = "PIXMA TS6120 Black Wireless";
                    break;
                case 3:
                    printerModel = "PIXMA MG3020 Black Wireless Photo All-in-One";
                    break;
                case 4:
                    printerModel = "PIXMA TS5020 Black Wireless";
                    break;
                default:
                    System.out.println("Nėra tokio printerio varianto!");
            }

            System.out.println("Do you want print with colors or not?");
            System.out.println("1 => Yes");
            System.out.println("2 => No");
            option = Integer.parseInt(userInput.nextLine());
            if(option == 1)
                colored = true;
            else colored = false;
        }
    }

    @Override
    public void sendFileToPrinter() {
        System.out.println("Sending books to" + printerModel + " printer...");
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void applyDiscount() {
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("All students who are signed up with our book rent services for more then 2 years, receives extra 10 percent discount.");
        System.out.println("1 => Yes, I have been loyal for 2 years");
        System.out.println("2 => No, I still need some time!");
        int userinp = userInput.nextInt();
        System.out.println("We encourage great student achievements. So for all students who have an average grade of 8.5 in a previous year, will receive more 30 percent discount.");
        System.out.println("1 => Yes, my average grade is higher.");
        System.out.println("2 => No, my average grade is lower.");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        int userinpgrades = userInput.nextInt();

        if((userinp > 0 && userinp < 3) && (userinpgrades > 0 && userinpgrades < 3))
        {
            if(userinp == 1)
            {
                System.out.println("Congratulations, you are eligible for loyal user discount, it will be applied automatically!");
                totalPriceOfService = totalPriceOfService - totalPriceOfService * DISCOUNT_FOR_LOAYL_STUDENS;
            }
            else if(userinp == 2)
            {
                System.out.println("Sorry, you are not eligible for loyal user discount. But it's never late to become one");

            }

            if(userinpgrades == 1)
            {
                System.out.println("Congratulations, you are eligible for academic discount, it will be applied automatically!");
                totalPriceOfService = totalPriceOfService - totalPriceOfService * DISCOUNT_FOR_GOOD_STUDENTS;
            }
            else if(userinpgrades == 2)
            {
                System.out.println("Sorry, you are not eligible for good student discount. Try the next year");
            }
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
    public void calculatePrice() {
        System.out.println("Calculating the price....");
        System.out.println("----------------------------------------------");
        System.out.println("Calculating the total A4 format book printing cost...");

        //Book rent price per day depends on the cost of book prime price. The more book cost, the bigger rent price would be
        double costPerPaper = 0.17;
        //Total price of renting all the books that user have picked
        totalPriceOfService = 0;


        for(BookInformation bookInfo : pickedBooks)
        {

            System.out.println("Book has a number of pages: " + bookInfo.getPageNumber() + " Per page printing cost: " + costPerPaper);
            double costAfterPercent =  bookInfo.getPageNumber() * costPerPaper;
            totalPriceOfService += costAfterPercent;
        }

        double totalPriceOfServiceWithoutPVM = totalPriceOfService - totalPriceOfService * 0.21;

        //Printing Prices of rent service with and without PVM
        System.out.println("------------------------Before Discount----------------------");
        System.out.println("Total rent price with PVM: " + totalPriceOfService);
        System.out.println("Total rent price without PVM: " + totalPriceOfServiceWithoutPVM);
        System.out.println("-------------------------------------------------------------");
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void sendEmailWhenReady() {
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
                bookList += "Book name: " + book.getBookTitle() + ", price: " + book.getBookPrimePrice() + ", expiration date:" + book.getBookExpiredate() + "\n\n";
            }

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mantole.sav105281@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mantasavas@gmail.com"));
            message.setSubject("Your books are ready to take! We are waiting for you");
            message.setText("Dear Student," + "\n\n " +
                    "It's just a reminder, that you have used our services recently, and you order is ready! You can find us on Vilnius Paukštyno g. 45" +
                    "\n\n " + "Books you have taken:" + "\n\n" + bookList);

            Transport.send(message);
            System.out.println("Reminder has been sucesfully send");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
