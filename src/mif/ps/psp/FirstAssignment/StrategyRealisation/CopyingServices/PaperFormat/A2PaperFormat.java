package mif.ps.psp.FirstAssignment.StrategyRealisation.CopyingServices.PaperFormat;

import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.Model.BookInformation;

import java.util.List;
import java.util.Scanner;

public class A2PaperFormat implements StandartPaperFormat {

    @Override
    @SuppressWarnings("Duplicates")
    public void pickPrinter(Scanner userInput, String printerModel, Boolean colored) {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("(Strategy Method in action) Please choose a printer, you wanna print you books with. Keep in mind we have a wide variety to pick from, so if you don't know which one is the best for your needs, just call us: 80887323495");
        System.out.println("Our printers capable of printing in A2 format are: ");
        System.out.println("1) HP Color LaserJet CM6040");
        System.out.println("2) HP LaserJet 9000 Series");
        System.out.println("3) HP LaserJet 700 color MFP ");
        System.out.print("Choose witch one you prefer: ");
        int option = Integer.parseInt(userInput.nextLine());

        if(option > 0 && option < 4) {
            switch (option) {
                case 1:
                    printerModel = "HP Color LaserJet CM6040";
                    break;
                case 2:
                    printerModel = "HP LaserJet 9000 Series";
                    break;
                case 3:
                    printerModel = "HP LaserJet 700 color MFP";
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
    public void sendFileToPrinter(String printerModel) {
        System.out.println("(Strategy Method in action) Sending books to" + printerModel + " printer...");
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void calculatePrice(double totalPriceOfService, List<BookInformation> pickedBooks) {

        System.out.println("(Strategy Method in action) Calculating the price....");
        System.out.println("----------------------------------------------");
        System.out.println("Calculating the total A2 format book printing cost...");

        //Book rent price per day depends on the cost of book prime price. The more book cost, the bigger rent price would be
        double costPerPaper = 0.05;
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
}
