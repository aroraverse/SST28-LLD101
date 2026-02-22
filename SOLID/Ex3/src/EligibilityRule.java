import java.util.*;

/**
 * EligibilityRule - Strategy interface for placement eligibility criteria.
 * 
 * SOLID PRINCIPLE: Open/Closed Principle (OCP)
 * 
 * Design Pattern: Strategy Pattern
 * 
 * Each implementation represents one eligibility criterion that can be evaluated
 * independently. This design allows:
 * - New rules to be added without modifying existing code
 * - Rules to be reused, composed, and tested in isolation
 * - Order and selection of rules to be configured externally
 * 
 * Time Complexity: O(1) per rule evaluation (see specific implementations)
 * Space Complexity: O(1) per rule (except for data being evaluated)
 */
public interface EligibilityRule {
    /**
     * Evaluates whether a student meets this eligibility criterion.
     * 
     * @param profile Student profile to evaluate
     * @return true if student meets the criterion, false otherwise
     */
    boolean evaluate(StudentProfile profile);

    /**
     * Gets the failure reason message if the criterion is not met.
     * 
     * @return Description of why a student failed this criterion
     */
    String getReason();
}
