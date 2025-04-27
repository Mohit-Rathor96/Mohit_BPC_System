import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private List<Integer> appointmentIds; // Stores IDs of appointments booked by this patient

    public Patient(int id, String fullName, String address, String phone) {
        super(id, fullName, address, phone);
        this.appointmentIds = new ArrayList<>();
    }

    public void addAppointmentId(int appointmentId) {
        if (!appointmentIds.contains(appointmentId)) {
            appointmentIds.add(appointmentId);
        }
    }

    public void removeAppointmentId(int appointmentId) {
        appointmentIds.remove((Integer) appointmentId);
    }

    public List<Integer> getAppointmentIds() {
        return appointmentIds;
    }
}
