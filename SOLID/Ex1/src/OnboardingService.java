import java.util.*;

/**
 * OnboardingService - Orchestrates the student registration workflow.
 * 
 * SOLID PRINCIPLE: Single Responsibility Principle (SRP)
 * 
 * Approach:
 * - Delegates parsing, validation, persistence, and printing to specialized components
 * - Acts as a workflow orchestrator that coordinates multiple responsibilities
 * - Each dependency handles exactly one concern, making the service focused and testable
 * 
 * Time Complexity: O(n) where n is the length of the input string
 * Space Complexity: O(n) for storing parsed fields and error messages
 * 
 * Benefits:
 * - Easy to test each component independently
 * - Adding new validation rules doesn't require modifying this class
 * - Clear separation of concerns improves maintainability
 */
public class OnboardingService {
    private final StudentStore store;
    private final InputParser parser;
    private final StudentValidator validator;
    private final ConfirmationPrinter printer;

    public OnboardingService(StudentStore store, InputParser parser, StudentValidator validator, ConfirmationPrinter printer) {
        this.store = store;
        this.parser = parser;
        this.validator = validator;
        this.printer = printer;
    }

    /**
     * Registers a student from raw input string.
     * 
     * Process:
     * 1. Parse the raw input into key-value pairs
     * 2. Extract student details
     * 3. Validate all fields
     * 4. Generate unique ID
     * 5. Store the record
     * 6. Print confirmation
     * 
     * @param raw Input string in format: field1=value1;field2=value2;...
     */
    public void registerFromRawInput(String raw) {
        System.out.println("INPUT: " + raw);

        Map<String, String> kv = parser.parse(raw);

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        List<String> errors = validator.validate(name, email, phone, program);

        if (!errors.isEmpty()) {
            System.out.println("ERROR: cannot register");
            for (String e : errors) {
                System.out.println("- " + e);
            }
            return;
        }

        String id = IdUtil.nextStudentId(store.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        store.save(rec);

        System.out.println("OK: created student " + id);
        System.out.println("Saved. Total students: " + store.count());
        printer.printConfirmation(rec);
    }
}
