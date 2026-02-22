import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final InvoiceCalculator calculator;
    private final InvoicePrinter printer;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStore store, InvoiceCalculator calculator, InvoicePrinter printer) {
        this.store = store;
        this.calculator = calculator;
        this.printer = printer;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        double subtotal = calculator.calculateSubtotal(lines);
        double tax = calculator.calculateTax(customerType, subtotal);
        double discount = calculator.calculateDiscount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        String invoiceText = printer.formatInvoiceForCustomer(invId, lines, customerType, subtotal, tax, discount, total);
        System.out.print(invoiceText);

        store.save(invId, invoiceText);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
