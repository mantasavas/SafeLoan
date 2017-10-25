package mif.ps.psp.FirstAssignment.StrategyRealisation.StrategyClient;

import mif.ps.psp.FirstAssignment.StrategyRealisation.BookRentServices.BookRentServicesStrategy;
import mif.ps.psp.FirstAssignment.StrategyRealisation.CopyingServices.CopyingServices;
import mif.ps.psp.FirstAssignment.StrategyRealisation.CopyingServices.PaperFormat.A2PaperFormat;
import mif.ps.psp.FirstAssignment.StrategyRealisation.CopyingServices.PaperFormat.A4PaperFormat;
import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.BookRentServices.AdultBookRent;
import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.BookRentServices.BookRentServiceTemplate;
import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.BookRentServices.ChildrenBookRent;
import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.BookRentServices.StudentBookRent;
import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.CopyingServices.*;

import java.util.Scanner;

public class StrategyClient {
    private int serviceOption;
    private int bookSection;
    private int paperFormat;
    Scanner scannerIn;

    public StrategyClient()
    {
        serviceOption = -1;
        paperFormat = -1;
        bookSection = -1;
        scannerIn = new Scanner(System.in);
    }

    public void RunClient()
    {
        PrintOptionsOfServices();
        if(serviceOption == 1)
        {
            runBookRentService();
        }
        else if(serviceOption == 2)
        {
            runCopyingServices();
        }
    }

    @SuppressWarnings("Duplicates")
    public void PrintOptionsOfServices() {
        while(true)
        {
            try
            {
                System.out.println("----------------------------------------------------------------------------");
                System.out.println("Library provides the following services: ");
                System.out.println("1 => Book Rent Services");
                System.out.println("2 => Printing Services");
                System.out.println("To proceed, please pick the service of choice from the list above!");
                System.out.println("----------------------------------------------------------------------------");
                System.out.print("Your option: ");
                serviceOption = scannerIn.nextInt();

                if(serviceOption > 0 && serviceOption < 3) {
                    if(serviceOption == 1) {
                        System.out.println("--------------------------------------------");
                        System.out.println("Choose Book Section you belong to:");
                        System.out.println("1 => Children books section");
                        System.out.println("2 => Students books section");
                        System.out.println("3 => Adults books section");
                        System.out.println("--------------------------------------------");
                        System.out.print("Your option: ");
                        bookSection = scannerIn.nextInt();

                        if (serviceOption > 0 && serviceOption < 4)
                            break;
                        else
                            System.out.println("You entered wrong number of section, which doesn't exist! Please Try again!");

                    }
                    else
                    {
                        System.out.println("It's still not implemented! Please be patient! :) ");
                        System.out.println("---------------------------------------------");
                        System.out.println("Choose Book Section you belong to and what kind of paper format you wanna use to print a book:");
                        System.out.println("1 => A2 format for Children section");
                        System.out.println("2 => A2 format for Students books section");
                        System.out.println("3 => A2 format for Adults books section");
                        System.out.println("4 => A4 format for Children section");
                        System.out.println("5 => A4 format for Students books section");
                        System.out.println("6 => A4 format for Adults books section");
                        System.out.println("--------------------------------------------");
                        System.out.print("Your option: ");
                        bookSection = scannerIn.nextInt();

                        if (serviceOption > 0 && serviceOption < 4)
                            break;
                    }
                }else
                {
                    System.out.println("You entered wrong number of service, which doesn't exist! Please Try again!");

                }

            }
            catch (Exception ex)
            {
                System.out.println("Wrong input, try again!");
            }
        }
    }

    @SuppressWarnings("Duplicates")
    private void runBookRentService()
    {
        if(bookSection == 1)
        {
            BookRentServicesStrategy childrenSection = new BookRentServicesStrategy();
            childrenSection.fulfillBookOrder(new mif.ps.psp.FirstAssignment.StrategyRealisation.AgeGroupDiscount.ChildrenBookRent());
        }
        else if(bookSection == 2)
        {
            BookRentServicesStrategy studentSection = new BookRentServicesStrategy();
            studentSection.fulfillBookOrder(new mif.ps.psp.FirstAssignment.StrategyRealisation.AgeGroupDiscount.StudentBookRent());
        }
        else if(bookSection == 3)
        {
            BookRentServicesStrategy adultSection = new BookRentServicesStrategy();
            adultSection.fulfillBookOrder(new mif.ps.psp.FirstAssignment.StrategyRealisation.AgeGroupDiscount.AdultBookRent());
        }

    }

    @SuppressWarnings("Duplicates")
    private void runCopyingServices()
    {
        System.out.println("Copying Services are not implemented! Please be patiet and try later!");

        if(bookSection == 1)
        {
            CopyingServices childrenA2Section = new CopyingServices();
            childrenA2Section.fulfillCopyingService(new mif.ps.psp.FirstAssignment.StrategyRealisation.AgeGroupDiscount.ChildrenBookRent(), new A2PaperFormat());
        }
        else if(bookSection == 2)
        {
            CopyingServices studentA2Section = new CopyingServices();
            studentA2Section.fulfillCopyingService(new mif.ps.psp.FirstAssignment.StrategyRealisation.AgeGroupDiscount.StudentBookRent(), new A2PaperFormat());
        }
        else if(bookSection == 3)
        {
            CopyingServices adultA2Section = new CopyingServices();
            adultA2Section.fulfillCopyingService(new mif.ps.psp.FirstAssignment.StrategyRealisation.AgeGroupDiscount.AdultBookRent(), new A2PaperFormat());
        }
        else if(bookSection == 4)
        {
            CopyingServices childrenA4Section = new CopyingServices();
            childrenA4Section.fulfillCopyingService(new mif.ps.psp.FirstAssignment.StrategyRealisation.AgeGroupDiscount.ChildrenBookRent(), new A4PaperFormat());
        }
        else if(bookSection == 5)
        {
            CopyingServices studententA4Section = new CopyingServices();
            studententA4Section.fulfillCopyingService(new mif.ps.psp.FirstAssignment.StrategyRealisation.AgeGroupDiscount.ChildrenBookRent(), new A4PaperFormat());
        }
        else if(bookSection == 6)
        {
            CopyingServices adulttA4Section = new CopyingServices();
            adulttA4Section.fulfillCopyingService(new mif.ps.psp.FirstAssignment.StrategyRealisation.AgeGroupDiscount.ChildrenBookRent(), new A4PaperFormat());
        }
    }
}
