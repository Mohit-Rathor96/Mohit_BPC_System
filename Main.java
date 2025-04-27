import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    Clinic clinic = new Clinic();
    static int nextPatientId = 1; // Start from 1 for patient ID

    // Sample data initialization for physiotherapists, treatments, and patients
    public void sampleData() {
        // Create physiotherapists with their expertise
        Physiotherapist Harry = new Physiotherapist(1, "Harry", "256 London", "123456");
        Harry.addExpertise("Physiotherapy");

        Physiotherapist Julia = new Physiotherapist(2, "Julia", "257 London", "654321");
        Julia.addExpertise("Rehabilitation");

        Physiotherapist Sophia = new Physiotherapist(3, "Sophia", "258 London", "71727374");
        Sophia.addExpertise("Osteopathy");

        // Add physiotherapists to the clinic
        clinic.addPhysiotherapist(Harry);
        clinic.addPhysiotherapist(Julia);
        clinic.addPhysiotherapist(Sophia);

        // Create treatments with their expertise areas
        Treatment massage = new Treatment("Massage", "Physiotherapy");
        Treatment acupuncture = new Treatment("Acupuncture", "Rehabilitation");
        Treatment mobilisation = new Treatment("Mobilisation of spine and joints", "Physiotherapy");
        Treatment pool = new Treatment("Pool Rehabilitation", "Rehabilitation");
        Treatment neural = new Treatment("Neural Mobilisation", "Osteopathy");

        // Assign treatment slots to physiotherapists for a period of 4 weeks
        LocalDate baseDate = LocalDate.of(2025, 5, 1);
        int sessionDurationMinutes = 60;

        for (int week = 0; week < 4; week++) {
            for (int i = 0; i < 6; i++) {
                int hour = 9 + (i % 3); // Slot times: 9am, 10am, 11am
                LocalDate day = baseDate.plusDays(week * 7 + i);
                LocalDateTime start = LocalDateTime.of(day, LocalTime.of(hour, 0));
                LocalDateTime end = start.plusMinutes(sessionDurationMinutes);

                // Add treatment slots for each physiotherapist
                Harry.addTreatmentSlot(new TreatmentSlot(massage, start, end, Harry));
                Julia.addTreatmentSlot(new TreatmentSlot(acupuncture, start, end, Julia));
                Sophia.addTreatmentSlot(new TreatmentSlot(neural, start, end, Sophia));
            }
        }

        // Adding patients to the clinic
        clinic.addPatient(new Patient(1, "Mohit", "123 London", "71727374"));
        clinic.addPatient(new Patient(2, "SAM", "124 London", "71727375"));
        clinic.addPatient(new Patient(3, "MAN", "125 London", "71727376"));
        clinic.addPatient(new Patient(4, "RAM", "126 London", "71727377"));
        clinic.addPatient(new Patient(5, "FAM", "127 London", "71727378"));
        clinic.addPatient(new Patient(6, "JAM", "128 London", "71727379"));
        clinic.addPatient(new Patient(7, "TAM", "129 London", "71727380"));
        clinic.addPatient(new Patient(8, "KAM", "130 London", "71727381"));
        clinic.addPatient(new Patient(9, "LAM", "131 London", "717273782"));
        clinic.addPatient(new Patient(10, "MAM", "132 London", "717273783"));
        clinic.addPatient(new Patient(11, "NAM", "133 London", "717273784"));
        clinic.addPatient(new Patient(12, "TONY", "134 London", "717273785"));
    }

    // Display the main menu for user input
    public void MainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Display the menu options
            System.out.println("\n--- Boost Physio Clinic ---");
            System.out.println("1. Add/Remove Patient");
            System.out.println("2. Book Treatment Appointment");
            System.out.println("3. Change or Cancel Appointment");
            System.out.println("4. Attend Appointment");
            System.out.println("5. Generate Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine()); // Capture user input

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue; // Retry on invalid input
            }

            // Switch case to handle different menu options
            switch (choice) {
                case 1:
                    // Add or Remove Patient
                    System.out.println("1. Add Patient");
                    System.out.println("2. Remove Patient");
                    int subChoice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline character

                    // Adding a new patient
                    if (subChoice == 1) {
                        while (clinic.isPatientIdExists(nextPatientId)) {
                            nextPatientId++; // Ensure unique patient IDs
                        }
                        int patientId = nextPatientId++;
                        System.out.print("Enter patient name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter patient address: ");
                        String address = scanner.nextLine();
                        System.out.print("Enter patient phone: ");
                        String phone = scanner.nextLine();
                        clinic.addPatient(new Patient(patientId, name, address, phone));
                        System.out.println("Patient added with ID: " + patientId);
                        System.out.println("Kindly remember your ID for future reference.");
                    } else if (subChoice == 2) {
                        // Remove an existing patient
                        System.out.print("Enter patient ID to remove: ");
                        int patientId = scanner.nextInt();
                        clinic.removePatient(patientId);
                        System.out.println("Patient removed from the clinic.");
                        scanner.nextLine(); // Consume newline
                    }
                    break;

                case 2:
                    // Book Treatment Appointment
                    System.out.print("Enter patient ID: ");
                    int patientId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline character

                    System.out.print("Search by Expertise (1) or Physiotherapist Name (2): ");
                    int searchChoice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline character

                    boolean searchByExpertise = (searchChoice == 1);
                    String expertise = null;
                    String physioName = null;

                    // Collect search criteria
                    if (searchByExpertise) {
                        System.out.print("Enter expertise: ");
                        expertise = scanner.nextLine();
                    } else {
                        System.out.print("Enter physiotherapist's name: ");
                        physioName = scanner.nextLine();
                    }

                    // Create the appointment request
                    String searchTerm = searchByExpertise ? expertise : physioName;
                    AppointmentRequest request = new AppointmentRequest(patientId, searchTerm, searchByExpertise);
                    clinic.bookAppointment(request); // Attempt to book the appointment
                    break;

                case 3:
                    // Change or Cancel Appointment
                    System.out.print("Enter appointment ID to cancel or change: ");
                    int appointmentId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline character

                    System.out.print("1. Cancel Appointment\n2. Change Appointment\nEnter choice: ");
                    int actionChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    // Cancel the appointment
                    if (actionChoice == 1) {
                        clinic.cancelAppointment(appointmentId);
                    } else if (actionChoice == 2) {
                        TreatmentAppointment appointment = clinic.findAppointmentById(appointmentId);

                        if (appointment == null) {
                            System.out.println("Appointment not found.");
                            break;
                        }

                        System.out.println("Choose how you want to search new slots:");
                        System.out.println("1. By Expertise");
                        System.out.println("2. By Physiotherapist Name");
                        int searchChoiceForReschedule = scanner.nextInt();
                        scanner.nextLine();

                        // Collect available slots for rescheduling
                        List<TreatmentSlot> availableSlots = new ArrayList<>();
                        if (searchChoiceForReschedule == 1) {
                            System.out.print("Enter expertise: ");
                            expertise = scanner.nextLine();

                            for (Physiotherapist physio : clinic.getPhysiotherapists()) {
                                for (TreatmentSlot slot : physio.getTimetable()) {
                                    if (!slot.isBooked() &&
                                            slot.getTreatment().getExpertise().equalsIgnoreCase(expertise) &&
                                            slot != appointment.getTreatmentSlot()) {
                                        availableSlots.add(slot);
                                    }
                                }
                            }
                        } else if (searchChoiceForReschedule == 2) {
                            System.out.print("Enter physiotherapist's name: ");
                            physioName = scanner.nextLine();

                            Physiotherapist physio = clinic.findPhysiotherapistByName(physioName);
                            if (physio != null) {
                                for (TreatmentSlot slot : physio.getTimetable()) {
                                    if (!slot.isBooked() && slot != appointment.getTreatmentSlot()) {
                                        availableSlots.add(slot);
                                    }
                                }
                            } else {
                                System.out.println("Physiotherapist not found.");
                                break;
                            }
                        }

                        if (availableSlots.isEmpty()) {
                            System.out.println("No available slots found for rescheduling.");
                            break;
                        }

                        System.out.println("Available Slots for Rescheduling:");
                        for (int i = 0; i < availableSlots.size(); i++) {
                            System.out.println((i + 1) + ". " + availableSlots.get(i));
                        }

                        // Select and reschedule appointment
                        System.out.print("Select a new slot number: ");
                        int selectedIndex = scanner.nextInt();
                        scanner.nextLine();

                        if (selectedIndex < 1 || selectedIndex > availableSlots.size()) {
                            System.out.println("Invalid selection.");
                            break;
                        }

                        // Reschedule appointment
                        TreatmentSlot oldSlot = appointment.getTreatmentSlot();
                        TreatmentSlot newSlot = availableSlots.get(selectedIndex - 1);

                        oldSlot.cancelSlot();  // Unbook old slot
                        newSlot.bookSlot();    // Book new slot

                        appointment.reschedule(newSlot);  // Update appointment with new slot
                        System.out.println("Appointment rescheduled successfully.");
                        System.out.println("Updated Appointment Details: " + appointment);
                    }
                    break;

                case 4:
                    // Attend Appointment
                    System.out.print("Enter appointment ID to attend: ");
                    int attendId = scanner.nextInt();
                    clinic.attendAppointment(attendId); // Mark appointment as attended
                    break;

                case 5:
                    // Generate Report
                    clinic.generateReport(); // Generate and print report
                    break;

                case 6:
                    // Exit the system
                    System.out.println("Exiting the system.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Main method to start the program
    public static void main(String[] args) {
        Main main = new Main();
        main.sampleData(); // Load sample data
        main.MainMenu();   // Show the main menu
    }
}