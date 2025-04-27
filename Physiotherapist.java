import java.util.ArrayList;
import java.util.List;

public class Physiotherapist extends Person {
    private List<String> expertiseAreas; // Areas of expertise, e.g., "Rehabilitation", "Osteopathy"
    private List<TreatmentSlot> timetable; // A 4-week schedule of treatment slots

    // Constructor to initialize a physiotherapist with basic details and empty expertise areas and timetable
    public Physiotherapist(int id, String fullName, String address, String phone) {
        super(id, fullName, address, phone);
        this.expertiseAreas = new ArrayList<>();
        this.timetable = new ArrayList<>();
    }

    // Add an expertise area to the physiotherapist (if not already added)
    public void addExpertise(String expertise) {
        if (!expertiseAreas.contains(expertise)) {
            expertiseAreas.add(expertise);
        }
    }

    // Getter method to retrieve the list of expertise areas
    public List<String> getExpertiseAreas() {
        return expertiseAreas;
    }

    // Add a new treatment slot to the physiotherapist's timetable
    public void addTreatmentSlot(TreatmentSlot slot) {
        timetable.add(slot);
    }

    // Getter method to retrieve the list of all treatment slots
    public List<TreatmentSlot> getTimetable() {
        return timetable;
    }

    // Get available treatment slots for a given expertise area
    public List<TreatmentSlot> getAvailableSlotsByExpertise(String expertise) {
        List<TreatmentSlot> available = new ArrayList<>();
        // If expertise area does not exist, return an empty list
        if (!expertiseAreas.contains(expertise)) return available;

        // Iterate through the timetable and check for available slots matching the expertise
        for (TreatmentSlot slot : timetable) {
            if (!slot.isBooked() && slot.getTreatment().getExpertise().equalsIgnoreCase(expertise)) {
                available.add(slot);
            }
        }
        return available;
    }

    // Get all available treatment slots (regardless of expertise area)
    public List<TreatmentSlot> getAllAvailableSlots() {
        List<TreatmentSlot> available = new ArrayList<>();
        // Iterate through the timetable and add slots that are not booked
        for (TreatmentSlot slot : timetable) {
            if (!slot.isBooked()) {
                available.add(slot);
            }
        }
        return available;
    }

    // Helper method to book a treatment slot
    public boolean bookSlot(TreatmentSlot slot) {
        if (slot.isBooked()) {
            System.out.println("The slot is already booked.");
            return false; // Slot is already booked
        } else {
            slot.bookSlot(); // Mark the slot as booked
            System.out.println("The slot has been successfully booked.");
            return true;
        }
    }

    // Helper method to cancel a booked treatment slot
    public boolean cancelSlot(TreatmentSlot slot) {
        if (!slot.isBooked()) {
            System.out.println("This slot is not booked.");
            return false; // Slot is not booked yet
        } else {
            slot.cancelSlot(); // Cancel the booking
            System.out.println("The booking has been successfully canceled.");
            return true;
        }
    }
}