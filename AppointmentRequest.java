public class AppointmentRequest {
    private int patientId;
    private String expertise; // Expertise or the physiotherapist's name
    private boolean searchByExpertise; // Flag to indicate if the search is by expertise

    // Constructor to initialize the appointment request with patient ID, expertise (or physiotherapist's name), and search preference
    public AppointmentRequest(int patientId, String expertiseOrName, boolean searchByExpertise) {
        this.patientId = patientId;
        this.expertise = expertiseOrName;
        this.searchByExpertise = searchByExpertise;
    }

    // Getter method to retrieve the patient ID
    public int getPatientId() {
        return patientId;
    }

    // Getter method to retrieve the expertise or physiotherapist's name
    public String getExpertiseOrName() {
        return expertise;
    }

    // Getter method to check if the search is based on expertise
    public boolean isSearchByExpertise() {
        return searchByExpertise;
    }
}
