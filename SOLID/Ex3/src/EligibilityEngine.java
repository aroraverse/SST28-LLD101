import java.util.*;

/**
 * EligibilityEngine - Evaluates student placement eligibility using a rule-based pattern.
 * 
 * SOLID PRINCIPLE: Open/Closed Principle (OCP)
 * 
 * Approach:
 * - Uses a list of rule objects instead of hard-coded conditionals
 * - New rules can be added by creating new implementations of EligibilityRule
 * - No need to modify this class when adding/removing rules
 * - Follows the Strategy pattern for flexible rule composition
 * 
 * Time Complexity: O(r) where r is the number of rules
 *   - Each rule evaluation is O(1)
 *   - Rules are evaluated in order until first failure
 * Space Complexity: O(r) for storing rules list, O(n) for reasons list
 * 
 * Benefits:
 * - Adding a new eligibility criterion doesn't require editing the engine
 * - Rules are independently testable
 * - Order of rule evaluation is explicit and configurable
 */
public class EligibilityEngine {
    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules;

    /**
     * Initializes the engine with default placement eligibility rules.
     * 
     * Default Rules (in order):
     * 1. No disciplinary flag
     * 2. CGR >= 8.0
     * 3. Attendance >= 75%
     * 4. Credits >= 20
     */
    public EligibilityEngine(FakeEligibilityStore store) {
        this.store = store;
        this.rules = new ArrayList<>();
        this.rules.add(new DisciplinaryFlagRule());
        this.rules.add(new CgrRule(8.0));
        this.rules.add(new AttendanceRule(75));
        this.rules.add(new CreditsRule(20));
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    /**
     * Evaluates student eligibility against all configured rules.
     * 
     * Algorithm:
     * - Iterates through rules in order
     * - Stops at first failure (short-circuit evaluation)
     * - Returns status and list of failure reasons
     * 
     * @param s Student profile to evaluate
     * @return EligibilityEngineResult with status (ELIGIBLE/NOT_ELIGIBLE) and reasons
     */
    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        String status = "ELIGIBLE";

        for (EligibilityRule rule : rules) {
            if (!rule.evaluate(s)) {
                status = "NOT_ELIGIBLE";
                reasons.add(rule.getReason());
                break;  // Short-circuit: stop checking once a rule fails
            }
        }

        return new EligibilityEngineResult(status, reasons);
    }
}

/**
 * Result object for eligibility evaluation.
 * 
 * Time Complexity: O(1) for construction
 * Space Complexity: O(n) for storing reasons list
 */
class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;
    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}
