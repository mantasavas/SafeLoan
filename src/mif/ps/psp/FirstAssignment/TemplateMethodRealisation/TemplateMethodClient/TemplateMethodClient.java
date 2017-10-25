package mif.ps.psp.FirstAssignment.TemplateMethodRealisation.TemplateMethodClient;

import mif.ps.psp.FirstAssignment.StrategyRealisation.CopyingServices.CopyingServices;
import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.BookRentServices.AdultBookRent;
import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.BookRentServices.BookRentServiceTemplate;
import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.BookRentServices.ChildrenBookRent;
import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.BookRentServices.StudentBookRent;
import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.CopyingServices.*;

import java.io.IOException;
import java.util.*;

public class TemplateMethodClient {
    private int serviceOption;
    private int bookSection;
    private int paperFormat;
    Scanner scannerIn;

    public TemplateMethodClient()
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
            BookRentServiceTemplate childrenSection = new ChildrenBookRent();
            childrenSection.fulfillBookOrder();
        }
        else if(bookSection == 2)
        {
            BookRentServiceTemplate studentSection = new StudentBookRent();
            studentSection.fulfillBookOrder();
        }
        else if(bookSection == 3)
        {
            BookRentServiceTemplate adultSection = new AdultBookRent();
            adultSection.fulfillBookOrder();
        }

    }

    @SuppressWarnings("Duplicates")
    private void runCopyingServices()
    {
        if(bookSection == 1)
        {
            CopyingServicesTemplate copyA2Children = new ChildrenSectionA2Format();
            copyA2Children.fulfillCopyingService();
        }
        else if(bookSection == 2)
        {
            CopyingServicesTemplate copyA2Student = new StudentSectionA2Format();
            copyA2Student.fulfillCopyingService();
        }
        else if(bookSection == 3)
        {
            CopyingServicesTemplate copyA2Adult = new AdultSectionA2Format();
            copyA2Adult.fulfillCopyingService();
        }
        else if(bookSection == 4)
        {
            CopyingServicesTemplate copyA4Children = new ChildrenSectionA4Format();
            copyA4Children.fulfillCopyingService();
        }
        else if(bookSection == 5)
        {
            CopyingServicesTemplate copyA4Student = new StudentSectionA4Format();
            copyA4Student.fulfillCopyingService();
        }
        else if(bookSection == 6)
        {
            CopyingServicesTemplate copyA4Adult = new AdultSectionA4Format();
            copyA4Adult.fulfillCopyingService();
        }
    }
}
