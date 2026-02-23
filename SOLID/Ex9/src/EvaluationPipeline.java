/**
 * EvaluationPipeline - Orchestrates code evaluation workflow with dependency injection.
 * 
 * SOLID PRINCIPLE: Dependency Inversion Principle (DIP)
 * 
 * Approach:
 * - High-level module (pipeline) depends on abstractions (interfaces)
 * - Low-level modules (concrete implementations) implement those abstractions
 * - Dependencies are injected through constructor
 * - No direct instantiation of concrete classes (no 'new' keyword)
 * 
 * Design Pattern: Dependency Injection + Pipeline Pattern
 * 
 * Time Complexity: O(s + g + w) where:
 *   - s = time for plagiarism check
 *   - g = time for code grading  
 *   - w = time for report writing
 * Space Complexity: O(1) for pipeline state + O(result) for scores
 * 
 * Benefits:
 * - Easy to test with mock implementations
 * - Simple to swap implementations (e.g., different grading algorithms)
 * - No tight coupling to concrete classes
 * - Configuration is external and flexible
 */
public class EvaluationPipeline {
    private final PlagiarismCheckService plagiarismChecker;
    private final CodeGradingService codeGrader;
    private final ReportWritingService reportWriter;
    private final Rubric rubric;

    /**
     * Constructs pipeline with all required services.
     * 
     * All dependencies must be provided; none are created internally.
     * This makes testing easy and requirements explicit.
     */
    public EvaluationPipeline(
            PlagiarismCheckService plagiarismChecker,
            CodeGradingService codeGrader,
            ReportWritingService reportWriter,
            Rubric rubric) {
        this.plagiarismChecker = plagiarismChecker;
        this.codeGrader = codeGrader;
        this.reportWriter = reportWriter;
        this.rubric = rubric;
    }

    /**
     * Executes the complete evaluation pipeline.
     * 
     * Process Flow:
     * 1. Check for plagiarism (0-100 score, lower is better)
     * 2. Grade code quality (0-100 score)
     * 3. Generate evaluation report
     * 4. Determine pass/fail based on total score >= 90
     * 
     * @param sub Student submission to evaluate
     */
    public void evaluate(Submission sub) {
        // Step 1: Plagiarism detection
        int plag = plagiarismChecker.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        // Step 2: Code grading
        int code = codeGrader.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        // Step 3: Report generation
        String reportName = reportWriter.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        // Step 4: Pass/Fail determination
        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}
