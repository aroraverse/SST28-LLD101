import java.util.*;

/**
 * InputParser - Responsible for parsing raw input strings into structured data.
 * 
 * SOLID PRINCIPLE: Single Responsibility Principle (SRP)
 * 
 * Approach:
 * - Handles only the parsing logic, no validation or business logic
 * - Extracts key-value pairs from semicolon-separated input format
 * - Returns structured data that can be consumed by other components
 * 
 * Time Complexity: O(n) where n is the length of the input string
 *   - Split operation: O(n)
 *   - Loop through parts: O(m) where m is number of key-value pairs
 *   - Map insertion: O(1) average case
 * Space Complexity: O(m) where m is the number of key-value pairs
 * 
 * Example:
 *   Input: "name=Riya;email=riya@sst.edu;phone=9876543210"
 *   Output: {name=Riya, email=riya@sst.edu, phone=9876543210}
 */
public class InputParser {
    /**
     * Parses a raw input string into key-value pairs.
     * 
     * Format: key1=value1;key2=value2;key3=value3
     * - Keys and values are trimmed of whitespace
     * - Uses LinkedHashMap to preserve insertion order
     * 
     * @param raw Input string with key=value pairs separated by semicolons
     * @return Map of parsed key-value pairs in insertion order
     */
    public Map<String, String> parse(String raw) {
        Map<String, String> kv = new LinkedHashMap<>();
        String[] parts = raw.split(";");
        for (String p : parts) {
            String[] t = p.split("=", 2);
            if (t.length == 2) {
                kv.put(t[0].trim(), t[1].trim());
            }
        }
        return kv;
    }
}
