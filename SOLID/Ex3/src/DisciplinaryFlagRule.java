public class DisciplinaryFlagRule implements EligibilityRule {
    @Override
    public boolean evaluate(StudentProfile profile) {
        return profile.disciplinaryFlag == LegacyFlags.NONE;
    }

    @Override
    public String getReason() {
        return "disciplinary flag present";
    }
}
