/**
 * Represents a single hospital visit record for a patient.
 */
public class VisitRecord {
    private int visitId;
    private String visitDate;
    private String doctorName;
    private String diagnosis;
    private String treatment;

    public VisitRecord(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        this.visitId = visitId;
        this.visitDate = visitDate;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public int getVisitId() {
        return visitId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void display() {
        System.out.println("    -> Visit ID: " + visitId + " | Date: " + visitDate + " | Doctor: " + doctorName + 
                           " | Diagnosis: " + diagnosis + " | Treatment: " + treatment);
    }

    @Override
    public String toString() {
        return "Visit ID: " + visitId + ", Date: " + visitDate + ", Doctor: " + doctorName + 
               ", Diagnosis: " + diagnosis + ", Treatment: " + treatment;
    }
}
