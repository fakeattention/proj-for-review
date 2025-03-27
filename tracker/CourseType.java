package tracker;
/**
 * Enumeration of course types in the system.
 */
public enum CourseType {
    JAVA("Java", 600),
    DSA("DSA", 400),
    DATABASES("Databases", 480),
    SPRING("Spring", 550);

    private final String name;
    private final int requiredCredits;

    CourseType(String name, int requiredCredits) {
        this.name = name;
        this.requiredCredits = requiredCredits;
    }

    public String getName() {
        return name;
    }

    public int getRequiredCredits() {
        return requiredCredits;
    }
}