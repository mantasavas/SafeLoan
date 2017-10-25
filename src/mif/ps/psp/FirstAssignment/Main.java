package mif.ps.psp.FirstAssignment;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;
import mif.ps.psp.FirstAssignment.StrategyRealisation.StrategyClient.StrategyClient;
import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.TemplateMethodClient.TemplateMethodClient;

import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("What kind of technique you wanna use?");
        System.out.println("1 => Template method");
        System.out.println("2 => Strategy");

        Scanner userInput = new Scanner(System.in);
        int option = Integer.parseInt(userInput.nextLine());
        if((option == 1)) {
            TemplateMethodClient templateClient = new TemplateMethodClient();
            templateClient.RunClient();
        }
        else if(option == 2)
        {
            StrategyClient strategyClient = new StrategyClient();
            strategyClient.RunClient();
        }
    }
}
