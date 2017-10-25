package mif.ps.psp.FirstAssignment.StrategyRealisation.AgeGroupDiscount;

import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.Model.BookInformation;

import java.util.List;
import java.util.Scanner;

public interface AgeGroupDiscount {
    void getServiceInformation(List<BookInformation> books);
    void applyDiscount(Scanner userInput, double totalPriceOfService, List<BookInformation> pickedBooks);
    void sendEmailWhenReady(List<BookInformation> pickedBooks);
}
