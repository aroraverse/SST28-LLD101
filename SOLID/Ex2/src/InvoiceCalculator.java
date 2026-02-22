import java.util.*;

public class InvoiceCalculator {
    private final Map<String, MenuItem> menu;
    
    public InvoiceCalculator(Map<String, MenuItem> menu) {
        this.menu = menu;
    }
    
    public double calculateSubtotal(java.util.List<OrderLine> lines) {
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
        }
        return subtotal;
    }
    
    public double calculateTax(String customerType, double subtotal) {
        double taxPct = TaxRules.taxPercent(customerType);
        return subtotal * (taxPct / 100.0);
    }
    
    public double calculateDiscount(String customerType, double subtotal, int distinctLines) {
        return DiscountRules.discountAmount(customerType, subtotal, distinctLines);
    }
}
