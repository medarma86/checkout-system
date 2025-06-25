package uk.sm.cs.service;

import java.util.*;
import uk.sm.cs.model.PricingRule;

public class Checkout {
	
    private final Map<String, PricingRule> pricingRules;
    private final Map<String, Integer> itemsCount;

    public Checkout(Map<String, PricingRule> pricingRules) {
        this.pricingRules = pricingRules;
        this.itemsCount = new HashMap<>();
    }
    
    public void scan(String sku) {
        if (!pricingRules.containsKey(sku)) {
            throw new IllegalArgumentException("Invalid SKU: " + sku);
        }
        itemsCount.put(sku, itemsCount.getOrDefault(sku, 0) + 1);
    }

    public int total() {
        int total = 0;
        for (Map.Entry<String, Integer> entry : itemsCount.entrySet()) {
            String sku = entry.getKey();
            int quantity = entry.getValue();
            PricingRule rule = pricingRules.get(sku);
            if (rule != null) {
                if (rule.specialQty() > 0) {
                    int specials = quantity / rule.specialQty();
                    int remainder = quantity % rule.specialQty();
                    total += specials * rule.specialPrice() + remainder * rule.unitPrice();
                } else {
                    total += quantity * rule.unitPrice();
                }
            }
        }
        return total;
    }
}
