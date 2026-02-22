import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        // Encode properly without losing data - this honors LSP
        if (req == null || req.title == null || req.body == null) {
            throw new IllegalArgumentException("Request and all fields must be non-null");
        }
        // Properly escape CSV without replacing meaningful characters
        String title = escapeCsv(req.title);
        String body = escapeCsv(req.body);
        String csv = "title,body\n" + title + "," + body + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }

    private String escapeCsv(String s) {
        if (s.contains(",") || s.contains("\"") || s.contains("\n")) {
            return "\"" + s.replace("\"", "\"\"") + "\"";
        }
        return s;
    }
}
