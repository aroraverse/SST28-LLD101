import java.util.*;

public class InvoicePrinter {
    private final Map<String, MenuItem> menu;
    
    public InvoicePrinter(Map<String, MenuItem> menu) {
        this.menu = menu;
    }
    
    public String formatInvoice(String invId, List<OrderLine> lines, 
                                double subtotal, double tax, double discount, double total) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invId).append("\n");
        
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }
        
        double taxPct = TaxRules.taxPercent("");  // Will need customer type
        out.append(String.format("Subtotal: %.2f\n", subtotal));
        out.append(String.format("Tax(5%%): %.2f\n", tax));
        out.append(String.format("Discount: -%.2f\n", discount));
        out.append(String.format("TOTAL: %.2f\n", total));
        
        return out.toString();
    }
    
    public String formatInvoiceForCustomer(String invId, List<OrderLine> lines, String customerType,
                                           double subtotal, double tax, double discount, double total) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invId).append("\n");
        
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }
        
        double taxPct = TaxRules.taxPercent(customerType);
        out.append(String.format("Subtotal: %.2f\n", subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        out.append(String.format("Discount: -%.2f\n", discount));
        out.append(String.format("TOTAL: %.2f\n", total));
        
        return out.toString();
    }
}
