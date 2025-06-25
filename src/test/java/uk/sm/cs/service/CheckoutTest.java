package uk.sm.cs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.sm.cs.model.PricingRule;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest {

    private Checkout checkout;

    @BeforeEach
    public void setup() {
        Map<String, PricingRule> rules = new HashMap<>();
        rules.put("A", new PricingRule("A", 50, 3, 130));
        rules.put("B", new PricingRule("B", 30, 2, 45));
        rules.put("C", new PricingRule("C", 20, 0, 0));
        rules.put("D", new PricingRule("D", 15, 0, 0));

        checkout = new Checkout(rules);
    }

    @Test
    public void testSingleItems() {
        checkout.scan("A");
        assertEquals(50, checkout.total());

        checkout.scan("B");
        assertEquals(80, checkout.total());

        checkout.scan("C");
        assertEquals(100, checkout.total());
    }

    @Test
    public void testSpecialOfferA() {
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        assertEquals(130, checkout.total());
    }

    @Test
    public void testSpecialOfferB() {
        checkout.scan("B");
        checkout.scan("B");
        assertEquals(45, checkout.total());
    }

    @Test
    public void testMixedItems() {
        checkout.scan("A");
        checkout.scan("B");
        checkout.scan("A");
        checkout.scan("A"); // triggers 3 for 130
        checkout.scan("B"); // triggers 2 for 45
        checkout.scan("C");

        // 3 A = 130, 2 B = 45, 1 C = 20
        assertEquals(195, checkout.total());
    }

    @Test
    public void testInvalidItemThrowsException() {
        try {
            checkout.scan("X");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid SKU: X", e.getMessage());
        }
    }
}
