package uk.sm.cs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import uk.sm.cs.model.PricingRule;
import uk.sm.cs.service.Checkout;

public class Main {
    public static void main(String[] args) {

    	   Map<String, PricingRule> rules = new HashMap<>();
    	   rules.put("A", new PricingRule("A", 50, 3, 130));
           rules.put("B", new PricingRule("B", 30, 2, 45));
           rules.put("C", new PricingRule("C", 20, 0, 0));
           rules.put("D", new PricingRule("D", 15, 0, 0));

        Checkout checkout = new Checkout(rules);
        try (Scanner scanner = new Scanner(System.in)) {

        System.out.println("Enter items (A, B, C, D). Type 'TOTAL' to finish:");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("TOTAL")) {
                int total = checkout.total();
                System.out.printf("Final total: £%.2f%n", total / 100.0);
                break;
            }

            if (input.matches("[A-D]")) {
                checkout.scan(input);
                int runningTotal = checkout.total();
                System.out.printf("Running total: £%.2f%n", runningTotal / 100.0);
            } else {
                System.out.println("Invalid input. Please enter A, B, C, D or TOTAL.");
            }
        }
    }
  }
}
