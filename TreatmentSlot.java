import java.time.LocalDateTime;

public class TreatmentSlot {
    private Treatment treatment; // The treatment associated with this slot (e.g., "Physiotherapy")
    private LocalDateTime startTime; // Start time of the treatment slot
    private LocalDateTime endTime; // End time of the treatment slot
    private Physiotherapist physiotherapist; // The physiotherapist assigned to this treatment slot
    public boolean isBooked; // Boolean to check if the slot is booked

    // Constructor to initialize a treatment slot with a treatment, start time, end time, and physiotherapist
    public TreatmentSlot(Treatment treatment, LocalDateTime startTime, LocalDateTime endTime, Physiotherapist physiotherapist) {
        this.treatment = treatment; // Initialize treatment for the slot
        this.startTime = startTime; // Initialize start time
        this.endTime = endTime; // Initialize end time
        this.physiotherapist = physiotherapist; // Initialize the assigned physiotherapist
    }

    // Getter method to check if the treatment slot is booked
    public boolean isBooked() {
        return isBooked;
    }

    // Method to mark the slot as booked
    public void bookSlot() {
        this.isBooked = true; // Mark the slot as booked
    }

    // Method to cancel the booking of the slot
    public void cancelSlot() {
        this.isBooked = false; // Mark the slot as available (not booked)
    }

    // Getter method to retrieve the treatment associated with this slot
    public Treatment getTreatment() {
        return treatment;
    }

    // Getter method to retrieve the start time of the treatment slot
    public LocalDateTime getStartTime() {
        return startTime;
    }

    // Getter method to retrieve the end time of the treatment slot
    public LocalDateTime getEndTime() {
        return endTime;
    }

    // Getter method to retrieve the physiotherapist assigned to this treatment slot
    public Physiotherapist getPhysiotherapist() {
        return physiotherapist; // Return the physiotherapist assigned to this slot
    }

    // Override toString method to provide a string representation of the treatment slot
    @Override
    public String toString() {
        return "Treatment: " + treatment.getName() + ", Physiotherapist: " + physiotherapist.getFullName() +
                ", Time: " + startTime + " to " + endTime; // Return a detailed string representation of the treatment slot
    }
}