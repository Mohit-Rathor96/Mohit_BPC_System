public class TreatmentAppointment {
    private static int nextId = 1; // Static counter to generate unique appointment IDs
    private int id; // Unique appointment ID
    private Patient patient; // The patient for this appointment
    private Physiotherapist physiotherapist;  // The physiotherapist assigned to this appointment
    private String expertise;  // The expertise area (e.g., "Physiotherapy")
    private TreatmentSlot treatmentSlot; // The treatment slot for the appointment
    private AppointmentStatus status; // The current status of the appointment (e.g., BOOKED, ATTENDED, CANCELLED)

    // Constructor to initialize a new TreatmentAppointment
    public TreatmentAppointment(Patient patient, Physiotherapist physiotherapist, String expertise, TreatmentSlot treatmentSlot) {
        this.id = nextId++; // Assign a unique ID to each new appointment
        this.patient = patient; // Assign the patient to the appointment
        this.physiotherapist = physiotherapist;  // Assign the physiotherapist to the appointment
        this.expertise = expertise; // Assign the expertise area to the appointment
        this.treatmentSlot = treatmentSlot; // Assign the treatment slot to the appointment
        this.status = AppointmentStatus.BOOKED;  // Default status is "BOOKED"
    }

    // Getter method to retrieve the appointment ID
    public int getId() {
        return id;
    }

    // Getter method to retrieve the patient associated with this appointment
    public Patient getPatient() {
        return patient;
    }

    // Getter method to retrieve the physiotherapist associated with this appointment
    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }

    // Getter method to retrieve the expertise area associated with this appointment
    public String getExpertise() {
        return expertise;
    }

    // Getter method to retrieve the treatment slot associated with this appointment
    public TreatmentSlot getTreatmentSlot() {
        return treatmentSlot;
    }

    // Getter method to retrieve the current status of the appointment
    public AppointmentStatus getStatus() {
        return status;
    }

    // Method to mark the appointment as attended
    public void markAsAttended() {
        this.status = AppointmentStatus.ATTENDED; // Change status to ATTENDED
    }

    // Method to cancel the appointment
    public void cancel() {
        this.status = AppointmentStatus.CANCELLED; // Change status to CANCELLED
    }

    // Method to reschedule the appointment to a new treatment slot
    public void reschedule(TreatmentSlot newSlot) {
        this.treatmentSlot = newSlot; // Update the treatment slot for the appointment
    }

    // Override the toString method to provide a string representation of the appointment
    @Override
    public String toString() {
        return "Appointment ID: " + id + ", Patient: " + patient.getFullName() +
                ", Physiotherapist: " + physiotherapist.getFullName() +
                ", Expertise: " + expertise + ", Time: " + treatmentSlot.getStartTime() +
                ", Status: " + status; // Returns a detailed string of the appointment details
    }
}