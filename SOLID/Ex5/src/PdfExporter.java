import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    private static final int MAX_CONTENT_SIZE = 20;
    
    @Override
    public ExportResult export(ExportRequest req) {
        // Check size constraint - this is a delivery constraint, not part of base contract
        if (req.body != null && req.body.length() > MAX_CONTENT_SIZE) {
            throw new IllegalArgumentException("PDF cannot handle content > 20 chars");
        }
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
