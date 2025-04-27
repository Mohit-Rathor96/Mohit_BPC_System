import java.util.*;
import java.time.LocalDateTime;

public class Clinic {
    private List<Patient> patients = new ArrayList<>();
    private List<Physiotherapist> physiotherapists;
    private List<TreatmentAppointment> appointments;

    // Getter for the list of physiotherapists
    public List<Physiotherapist> getPhysiotherapists() {
        return physiotherapists;
    }

    // Constructor to initialize the lists
    public Clinic() {
        this.patients = new ArrayList<>();
        this.physiotherapists = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    // Check if a patient ID exists in the clinic's records
    public boolean isPatientIdExists(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return true; // Patient ID found
            }
        }
        return false; // Patient ID not found
    }

    // Add a new patient to the clinic
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    // Remove a patient by their ID
    public boolean removePatient(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                patients.remove(patient);
                return true; // Patient removed successfully
            }
        }
        return false; // Patient not found
    }

    // Find and return a patient by their ID
    public Patient findPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null; // Return null if patient is not found
    }

    // Find and return a physiotherapist by their name
    public Physiotherapist findPhysiotherapistByName(String name) {
        for (Physiotherapist physio : physiotherapists) {
            if (physio.getFullName().equalsIgnoreCase(name)) {
                return physio;
            }
        }
        return null; // Return null if no match is found
    }

    // Add a new physiotherapist to the clinic
    public void addPhysiotherapist(Physiotherapist physiotherapist) {
        physiotherapists.add(physiotherapist);
    }

    // Book an appointment based on the provided request
    public void bookAppointment(AppointmentRequest request) {
        Scanner scanner = new Scanner(System.in);
        int patientId = request.getPatientId();
        Patient patient = findPatientById(patientId);

        // If patient is not found, terminate the method
        if (patient == null) {
            System.out.println("Patient not found!");
            return;
        }

        // List to store matching physiotherapists
        List<Physiotherapist> matchingPhysios = new ArrayList<>();

        // Search by expertise or physiotherapist name
        if (request.isSearchByExpertise()) {
            String requestedExpertise = request.getExpertiseOrName().toLowerCase();

            // Search for physiotherapists by expertise area
            for (Physiotherapist p : physiotherapists) {
                for (String exp : p.getExpertiseAreas()) {
                    if (exp.toLowerCase().equals(requestedExpertise)) {
                        matchingPhysios.add(p);
                        break; // Stop after first match
                    }
                }
            }
        } else {
            // Search by physiotherapist's full name
            for (Physiotherapist p : physiotherapists) {
                if (p.getFullName().equalsIgnoreCase(request.getExpertiseOrName())) {
                    matchingPhysios.add(p);
                }
            }
        }

        // If no matching physiotherapists are found
        if (matchingPhysios.isEmpty()) {
            System.out.println("No physiotherapists found matching the search criteria.");
            return;
        }

        // List to store available treatment slots
        List<TreatmentSlot> availableSlots = new ArrayList<>();

        // Check each physiotherapist's slots for availability
        for (Physiotherapist p : matchingPhysios) {
            for (TreatmentSlot slot : p.getTimetable()) {
                // Only show slot if it is not booked
                if (!slot.isBooked()) {
                    // If searching by expertise, also check expertise match
                    if (!request.isSearchByExpertise() ||
                            slot.getTreatment().getExpertise().equalsIgnoreCase(request.getExpertiseOrName())) {
                        availableSlots.add(slot);
                    }
                }
            }
        }

        // If no available slots are found
        if (availableSlots.isEmpty()) {
            System.out.println("No available treatment slots found.");
            return;
        }

        // Display available treatment slots to the user
        System.out.println("Available Treatment Slots:");
        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.println((i + 1) + ". " + availableSlots.get(i));
        }

        // Prompt the user to select a slot to book
        System.out.print("Select a slot number to book: ");
        int selected = scanner.nextInt();
        scanner.nextLine();

        // Check if the selected slot number is valid
        if (selected < 1 || selected > availableSlots.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        // Book the selected slot
        TreatmentSlot chosenSlot = availableSlots.get(selected - 1);
        chosenSlot.bookSlot(); // Mark slot as booked

        // Create a new treatment appointment
        TreatmentAppointment appointment = new TreatmentAppointment(
                patient,
                chosenSlot.getPhysiotherapist(),
                chosenSlot.getTreatment().getExpertise(),
                chosenSlot
        );

        // Add the appointment to the list and associate it with the patient
        appointments.add(appointment);
        patient.addAppointmentId(appointment.getId());

        // Display the booked appointment details
        System.out.println("Appointment booked successfully:");
        System.out.println(appointment);
    }

    // Cancel an appointment by ID
    public void cancelAppointment(int appointmentId) {
        for (TreatmentAppointment appointment : appointments) {
            if (appointment.getId() == appointmentId) {
                appointment.cancel(); // Mark appointment as cancelled
                System.out.println("Appointment cancelled successfully.");
                return;
            }
        }
        System.out.println("Appointment not found!"); // If appointment is not found
    }

    // Mark an appointment as attended by ID
    public void attendAppointment(int appointmentId) {
        for (TreatmentAppointment appointment : appointments) {
            if (appointment.getId() == appointmentId) {
                appointment.markAsAttended(); // Mark appointment as attended
                System.out.println("Appointment marked as attended.");
                return;
            }
        }
        System.out.println("Appointment not found!"); // If appointment is not found
    }

    // Find and return an appointment by ID
    public TreatmentAppointment findAppointmentById(int appointmentId) {
        for (TreatmentAppointment appointment : appointments) {
            if (appointment.getId() == appointmentId) {
                return appointment;
            }
        }
        return null; // Return null if appointment is not found
    }

    // Generate and print a report of all treatment appointments and attended appointments
    public void generateReport() {
        System.out.println("\n--- Treatment Appointments Report ---");
        // Report for each physiotherapist and their appointments
        for (Physiotherapist physiotherapist : physiotherapists) {
            System.out.println("\nPhysiotherapist: " + physiotherapist.getFullName());
            for (TreatmentAppointment appointment : appointments) {
                if (appointment.getPhysiotherapist() == physiotherapist) {
                    System.out.println(appointment); // Print each appointment
                }
            }
        }

        // Sort physiotherapists by the number of attended appointments
        System.out.println("\n--- Physiotherapists by Attended Appointments ---");
        physiotherapists.sort((a, b) -> {
            int countA = (int) appointments.stream().filter(app -> app.getPhysiotherapist() == a && app.getStatus() == AppointmentStatus.ATTENDED).count();
            int countB = (int) appointments.stream().filter(app -> app.getPhysiotherapist() == b && app.getStatus() == AppointmentStatus.ATTENDED).count();
            return Integer.compare(countB, countA); // Sort in descending order
        });

        // Print the number of attended appointments for each physiotherapist
        for (Physiotherapist physiotherapist : physiotherapists) {
            int attendedCount = (int) appointments.stream().filter(app -> app.getPhysiotherapist() == physiotherapist && app.getStatus() == AppointmentStatus.ATTENDED).count();
            System.out.println(physiotherapist.getFullName() + " - Attended: " + attendedCount);
        }
    }
}