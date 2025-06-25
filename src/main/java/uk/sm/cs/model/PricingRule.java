package uk.sm.cs.model;

public record PricingRule(
    String sku,
    int unitPrice,
    int specialQty,
    int specialPrice
) {}