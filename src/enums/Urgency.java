package enums;

public enum Urgency {
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    CRITICAL(4);

    private final int priorityLevel;

    Urgency(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }
}