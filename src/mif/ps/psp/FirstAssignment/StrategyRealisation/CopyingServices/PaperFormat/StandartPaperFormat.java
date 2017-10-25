package mif.ps.psp.FirstAssignment.StrategyRealisation.CopyingServices.PaperFormat;

import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.Model.BookInformation;
import java.util.List;
import java.util.Scanner;

public interface StandartPaperFormat {
    void pickPrinter(Scanner userInput, String printerModel, Boolean colored);
    void sendFileToPrinter(String printerModel);
    void calculatePrice(double totalPriceOfService, List<BookInformation> pickedBooks);
}
