/**
 * Represents a patient in the hospital system.
 * Contains patient details and their visit history (Singly Linked List).
 */
public class Patient {
    private int patientId;
    private String name;
    private int age;
    private String contactNumber;
    private String medicalCondition;
    private VisitLinkedList visitHistory;

    public Patient(int patientId, String name, int age, String contactNumber, String medicalCondition) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;
        this.visitHistory = new VisitLinkedList();
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public VisitLinkedList getVisitHistory() {
        return visitHistory;
    }

    public void displayPatientDetails() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Patient ID       : " + patientId);
        System.out.println("Name             : " + name);
        System.out.println("Age              : " + age);
        System.out.println("Contact Number   : " + contactNumber);
        System.out.println("Medical Condition: " + medicalCondition);
        System.out.println("Visit History    :");
        visitHistory.displayVisits();
        System.out.println("-----------------------------------------------------------------");
    }

    public void displaySummary() {
        System.out.printf("| %-10d | %-20s | %-5d | %-15s | %-25s |\n", 
                          patientId, name, age, contactNumber, medicalCondition);
    }
}
