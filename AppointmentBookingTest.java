import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class AppointmentBookingTest {

    private Clinic control;

    // Setup before each test, initializes the Clinic instance and adds a dummy patient
    @BeforeEach
    void setup() {
        control = new Clinic();
        // Adding a sample patient with basic details
        control.addPatient(1,"Mohit", "India", "0745896422");

        // Preloaded physiotherapists & available time slots are already set in the main code
    }

    // Test case for booking an appointment based on treatment type
    @Test
    void testAppointmentBookingByTreatment() {
        // Retrieve the patient ID from the list of all patients
        int patientId = control.getAllPatients().get(0).getId();

        // Attempt to book an appointment by treatment type ("Back Pain")
        boolean success = control.bookAppointmentByTreatment(patientId, "Back Pain");

        // Verifying the appointment was successfully booked
        assertTrue(success, "Appointment should be booked successfully for the patient.");

        // Retrieve and verify the appointment details for the patient
        List<TreatmentAppointment> appointments = control.getAllAppointmentsForPatient(patientId);
        assertEquals(1, appointments.size(), "One appointment should be scheduled.");
        assertEquals("BOOKED", appointments.get(0).getStatus().toString());
    }

    // Test case for preventing double booking at the same time
    @Test
    void testPreventDoubleBookingSameTime() {
        // Retrieve the patient ID
        int patientId = control.getAllPatients().get(0).getId();

        // Book the first appointment for the patient
        control.bookAppointmentByTreatment(patientId, "Back Pain");

        // Attempt to book a second appointment for the same treatment, which should fail due to a time conflict
        boolean result = control.bookAppointmentByTreatment(patientId, "Back Pain");

        // Verify the second appointment is rejected due to a conflicting time slot
        assertFalse(result, "Second appointment booking should be rejected due to time conflict.");
    }
}