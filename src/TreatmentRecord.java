/**
 * Represents a record of a completed treatment.
 */
public class TreatmentRecord {
    private int patientId;
    private String patientName;
    private String doctorName;
    private String diagnosis;
    private String treatmentGiven;
    private String completionDate;

    public TreatmentRecord(int patientId, String patientName, String doctorName, 
                           String diagnosis, String treatmentGiven, String completionDate) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatmentGiven = treatmentGiven;
        this.completionDate = completionDate;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatmentGiven() {
        return treatmentGiven;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void display() {
        System.out.printf("| %-10d | %-18s | %-15s | %-20s | %-22s | %-12s |\n", 
                          patientId, patientName, doctorName, diagnosis, treatmentGiven, completionDate);
    }
}
