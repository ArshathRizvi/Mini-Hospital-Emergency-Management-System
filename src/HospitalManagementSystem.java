import java.util.Scanner;

/**
 * CIT300 - Data Structures and Algorithms
 * Mini Hospital Emergency Management System
 * 
 * Main driver class providing an interactive console interface to manage:
 * 1. Patient Records - Binary Search Tree (BST)
 * 2. Patient Visit History - Singly Linked List
 * 3. Emergency Patient Queue - Queue (FIFO)
 * 4. Treatment History - Stack (LIFO)
 */
public class HospitalManagementSystem {

    private static PatientBST patientTree = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-load sample data for convenient demonstration and testing
        loadSampleData();

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = readIntInput("Enter your choice (1-6): ");

            switch (choice) {
                case 1:
                    managePatientBST();
                    break;
                case 2:
                    manageVisitHistory();
                    break;
                case 3:
                    manageEmergencyQueue();
                    break;
                case 4:
                    manageTreatmentStack();
                    break;
                case 5:
                    simulateEmergencyTreatment();
                    break;
                case 6:
                    System.out.println("\nThank you for using Mini Hospital Emergency Management System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid option! Please select a valid choice between 1 and 6.");
            }
        }
        scanner.close();
    }

    // Displays the main menu
    private static void displayMainMenu() {
        System.out.println("\n=============================================================");
        System.out.println("         MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM");
        System.out.println("=============================================================");
        System.out.println("1. Patient Records Management (Binary Search Tree - BST)");
        System.out.println("2. Patient Visit History Management (Singly Linked List)");
        System.out.println("3. Emergency Patient Queue (Queue - FIFO)");
        System.out.println("4. Treatment History (Stack - LIFO)");
        System.out.println("5. Emergency Treatment Simulation (Integrated Workflow)");
        System.out.println("6. Exit");
        System.out.println("=============================================================");
    }

    // -------------------------------------------------------------
    // 1. PATIENT RECORDS MANAGEMENT (BST)
    // -------------------------------------------------------------
    private static void managePatientBST() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- [1] PATIENT RECORDS (BINARY SEARCH TREE) ---");
            System.out.println("1. Insert New Patient");
            System.out.println("2. Search Patient by ID");
            System.out.println("3. Delete Patient by ID");
            System.out.println("4. Display All Patients (In-Order Traversal - Sorted by ID)");
            System.out.println("5. Back to Main Menu");
            int option = readIntInput("Select an option (1-5): ");

            switch (option) {
                case 1:
                    System.out.println("\n--- Insert New Patient ---");
                    int id = readIntInput("Enter Patient ID: ");
                    if (patientTree.search(id) != null) {
                        System.out.println("Error: A patient with ID " + id + " already exists in the system!");
                        break;
                    }
                    System.out.print("Enter Patient Name: ");
                    String name = scanner.nextLine().trim();
                    int age = readIntInput("Enter Age: ");
                    System.out.print("Enter Contact Number: ");
                    String contact = scanner.nextLine().trim();
                    System.out.print("Enter Medical Condition: ");
                    String condition = scanner.nextLine().trim();

                    Patient newPatient = new Patient(id, name, age, contact, condition);
                    boolean inserted = patientTree.insert(newPatient);
                    if (inserted) {
                        System.out.println("Success: Patient " + name + " (ID: " + id + ") successfully added to BST!");
                    } else {
                        System.out.println("Failed to insert patient.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Search Patient by ID ---");
                    int searchId = readIntInput("Enter Patient ID to search: ");
                    Patient foundPatient = patientTree.search(searchId);
                    if (foundPatient != null) {
                        System.out.println("\nPatient Found:");
                        foundPatient.displayPatientDetails();
                    } else {
                        System.out.println("Patient with ID " + searchId + " was not found in the BST.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Delete Patient by ID ---");
                    int deleteId = readIntInput("Enter Patient ID to delete: ");
                    boolean deleted = patientTree.delete(deleteId);
                    if (deleted) {
                        System.out.println("Success: Patient with ID " + deleteId + " was deleted from the BST.");
                    } else {
                        System.out.println("Patient with ID " + deleteId + " not found. Deletion failed.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- All Patients (BST In-Order Traversal) ---");
                    patientTree.inOrderTraversal();
                    break;

                case 5:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option! Please enter a number between 1 and 5.");
            }
        }
    }

    // -------------------------------------------------------------
    // 2. PATIENT VISIT HISTORY MANAGEMENT (SINGLY LINKED LIST)
    // -------------------------------------------------------------
    private static void manageVisitHistory() {
        System.out.println("\n--- [2] PATIENT VISIT HISTORY (SINGLY LINKED LIST) ---");
        int patientId = readIntInput("Enter Patient ID to manage visit history: ");
        Patient patient = patientTree.search(patientId);

        if (patient == null) {
            System.out.println("Patient with ID " + patientId + " not found in the system.");
            return;
        }

        System.out.println("Managing visit records for: " + patient.getName() + " (ID: " + patient.getPatientId() + ")");
        VisitLinkedList history = patient.getVisitHistory();

        boolean back = false;
        while (!back) {
            System.out.println("\nVisit History Options for Patient [" + patient.getName() + "]:");
            System.out.println("1. Add New Visit Record");
            System.out.println("2. Search Visit Record by Visit ID");
            System.out.println("3. Remove Visit Record by Visit ID");
            System.out.println("4. Display All Previous Visits");
            System.out.println("5. Back to Main Menu");
            int option = readIntInput("Select an option (1-5): ");

            switch (option) {
                case 1:
                    System.out.println("\n--- Add Visit Record ---");
                    int vId = readIntInput("Enter Visit ID: ");
                    if (history.searchVisit(vId) != null) {
                        System.out.println("Error: A visit with Visit ID " + vId + " already exists for this patient!");
                        break;
                    }
                    System.out.print("Enter Visit Date (e.g., YYYY-MM-DD): ");
                    String date = scanner.nextLine().trim();
                    System.out.print("Enter Doctor Name: ");
                    String doc = scanner.nextLine().trim();
                    System.out.print("Enter Diagnosis: ");
                    String diag = scanner.nextLine().trim();
                    System.out.print("Enter Treatment: ");
                    String treat = scanner.nextLine().trim();

                    VisitRecord newVisit = new VisitRecord(vId, date, doc, diag, treat);
                    history.addVisit(newVisit);
                    System.out.println("Success: Visit record #" + vId + " added to patient's history.");
                    break;

                case 2:
                    System.out.println("\n--- Search Visit Record ---");
                    int searchVId = readIntInput("Enter Visit ID to search: ");
                    VisitRecord foundVisit = history.searchVisit(searchVId);
                    if (foundVisit != null) {
                        System.out.println("Visit Record Found:");
                        foundVisit.display();
                    } else {
                        System.out.println("Visit ID " + searchVId + " not found in this patient's history.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Remove Visit Record ---");
                    int removeVId = readIntInput("Enter Visit ID to remove: ");
                    boolean removed = history.removeVisit(removeVId);
                    if (removed) {
                        System.out.println("Success: Visit record #" + removeVId + " was removed.");
                    } else {
                        System.out.println("Visit ID " + removeVId + " not found. Removal failed.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- All Visits for " + patient.getName() + " ---");
                    history.displayVisits();
                    break;

                case 5:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option! Please enter a number between 1 and 5.");
            }
        }
    }

    // -------------------------------------------------------------
    // 3. EMERGENCY PATIENT QUEUE (QUEUE - FIFO)
    // -------------------------------------------------------------
    private static void manageEmergencyQueue() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- [3] EMERGENCY PATIENT QUEUE (FIFO) ---");
            System.out.println("1. Enqueue Patient to Emergency Queue");
            System.out.println("2. Dequeue Next Patient for Treatment");
            System.out.println("3. Display All Waiting Emergency Patients");
            System.out.println("4. Back to Main Menu");
            int option = readIntInput("Select an option (1-4): ");

            switch (option) {
                case 1:
                    System.out.println("\n--- Enqueue Patient ---");
                    int patientId = readIntInput("Enter Patient ID to enqueue: ");
                    Patient patient = patientTree.search(patientId);

                    if (patient == null) {
                        System.out.println("Patient not found in records. Would you like to register this patient first? (y/n): ");
                        String ans = scanner.nextLine().trim();
                        if (ans.equalsIgnoreCase("y")) {
                            System.out.print("Enter Patient Name: ");
                            String name = scanner.nextLine().trim();
                            int age = readIntInput("Enter Age: ");
                            System.out.print("Enter Contact Number: ");
                            String contact = scanner.nextLine().trim();
                            System.out.print("Enter Emergency Medical Condition: ");
                            String condition = scanner.nextLine().trim();

                            patient = new Patient(patientId, name, age, contact, condition);
                            patientTree.insert(patient);
                            emergencyQueue.enqueue(patient);
                            System.out.println("Success: Patient registered and added to the emergency queue (FIFO).");
                        } else {
                            System.out.println("Enqueue cancelled.");
                        }
                    } else {
                        emergencyQueue.enqueue(patient);
                        System.out.println("Success: Existing patient " + patient.getName() + " (ID: " + patient.getPatientId() + ") added to emergency queue.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Dequeue Patient for Treatment ---");
                    if (emergencyQueue.isEmpty()) {
                        System.out.println("Emergency Queue is empty! No patients waiting for treatment.");
                    } else {
                        Patient nextPatient = emergencyQueue.dequeue();
                        System.out.println("Next patient called for emergency treatment (FIFO order):");
                        System.out.println("  ID       : " + nextPatient.getPatientId());
                        System.out.println("  Name     : " + nextPatient.getName());
                        System.out.println("  Condition: " + nextPatient.getMedicalCondition());
                        System.out.println("(Patient is now removed from waiting queue and ready for treatment)");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Current Emergency Waiting Queue ---");
                    emergencyQueue.displayQueue();
                    break;

                case 4:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option! Please enter a number between 1 and 4.");
            }
        }
    }

    // -------------------------------------------------------------
    // 4. TREATMENT HISTORY (STACK - LIFO)
    // -------------------------------------------------------------
    private static void manageTreatmentStack() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- [4] TREATMENT HISTORY (STACK - LIFO) ---");
            System.out.println("1. Push Completed Treatment Record");
            System.out.println("2. Pop Most Recent Treatment Record");
            System.out.println("3. Display All Completed Treatment Records (LIFO)");
            System.out.println("4. Back to Main Menu");
            int option = readIntInput("Select an option (1-4): ");

            switch (option) {
                case 1:
                    System.out.println("\n--- Push Treatment Record ---");
                    int pId = readIntInput("Enter Patient ID: ");
                    Patient p = patientTree.search(pId);
                    String pName;
                    if (p != null) {
                        pName = p.getName();
                        System.out.println("Patient Name: " + pName);
                    } else {
                        System.out.print("Enter Patient Name: ");
                        pName = scanner.nextLine().trim();
                    }

                    System.out.print("Enter Doctor Name: ");
                    String doctor = scanner.nextLine().trim();
                    System.out.print("Enter Diagnosis: ");
                    String diagnosis = scanner.nextLine().trim();
                    System.out.print("Enter Treatment Given: ");
                    String treatment = scanner.nextLine().trim();
                    System.out.print("Enter Completion Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine().trim();

                    TreatmentRecord record = new TreatmentRecord(pId, pName, doctor, diagnosis, treatment, date);
                    treatmentStack.push(record);
                    System.out.println("Success: Treatment record pushed to top of Treatment Stack.");
                    break;

                case 2:
                    System.out.println("\n--- Pop Most Recent Treatment Record ---");
                    if (treatmentStack.isEmpty()) {
                        System.out.println("Treatment Stack is empty! No records to pop.");
                    } else {
                        TreatmentRecord popped = treatmentStack.pop();
                        System.out.println("Most recent treatment record removed from stack (LIFO):");
                        System.out.println("  Patient ID : " + popped.getPatientId());
                        System.out.println("  Patient    : " + popped.getPatientName());
                        System.out.println("  Doctor     : " + popped.getDoctorName());
                        System.out.println("  Diagnosis  : " + popped.getDiagnosis());
                        System.out.println("  Treatment  : " + popped.getTreatmentGiven());
                        System.out.println("  Date       : " + popped.getCompletionDate());
                    }
                    break;

                case 3:
                    System.out.println("\n--- All Completed Treatment Records ---");
                    treatmentStack.displayHistory();
                    break;

                case 4:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option! Please enter a number between 1 and 4.");
            }
        }
    }

    // -------------------------------------------------------------
    // 5. EMERGENCY TREATMENT SIMULATION (INTEGRATED WORKFLOW)
    // -------------------------------------------------------------
    private static void simulateEmergencyTreatment() {
        System.out.println("\n--- [5] EMERGENCY TREATMENT SIMULATION ---");
        System.out.println("This workflow connects all 4 data structures:");
        System.out.println("1. Dequeues next patient from Emergency Queue (Queue - FIFO)");
        System.out.println("2. Doctor provides treatment");
        System.out.println("3. Treatment is pushed onto Treatment History (Stack - LIFO)");
        System.out.println("4. Visit record is appended to patient's history (Linked List)");

        if (emergencyQueue.isEmpty()) {
            System.out.println("\nWarning: No patients in the emergency queue! Please enqueue a patient first.");
            return;
        }

        Patient patient = emergencyQueue.dequeue();
        System.out.println("\n[1] Dequeued Patient from Emergency Queue: " + patient.getName() + " (ID: " + patient.getPatientId() + ")");
        System.out.println("    Condition: " + patient.getMedicalCondition());

        System.out.println("\n[2] Doctor Consultation & Treatment:");
        System.out.print("    Enter Attending Doctor Name: ");
        String doctor = scanner.nextLine().trim();
        System.out.print("    Enter Final Diagnosis: ");
        String diagnosis = scanner.nextLine().trim();
        System.out.print("    Enter Treatment Administered: ");
        String treatment = scanner.nextLine().trim();
        System.out.print("    Enter Treatment Date (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();

        // 3. Push to Stack
        TreatmentRecord record = new TreatmentRecord(patient.getPatientId(), patient.getName(), doctor, diagnosis, treatment, date);
        treatmentStack.push(record);
        System.out.println("\n[3] Treatment record pushed onto Treatment Stack (LIFO).");

        // 4. Add to Singly Linked List
        int visitId = (int) (System.currentTimeMillis() % 10000); // Generate simple unique visit ID
        VisitRecord visit = new VisitRecord(visitId, date, doctor, diagnosis, treatment);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("[4] Visit record (Visit ID: " + visitId + ") added to patient's Singly Linked List visit history.");

        System.out.println("\nSimulation complete! Patient has been treated successfully.");
    }

    // Helper to safely read integer input
    private static int readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid integer.");
            }
        }
    }

    // Pre-populates sample data for quick demonstration
    private static void loadSampleData() {
        // Sample Patients in BST (added in order that creates a balanced tree)
        Patient p1 = new Patient(105, "Kamal Silva", 34, "0771234567", "Severe Asthma");
        Patient p2 = new Patient(102, "Nimal Perera", 45, "0719876543", "Chest Pain");
        Patient p3 = new Patient(108, "Sunil Fernando", 28, "0763456789", "Fractured Arm");
        Patient p4 = new Patient(101, "Anura Bandara", 52, "0751122334", "High Blood Pressure");
        Patient p5 = new Patient(104, "Saman Kumara", 22, "0788899001", "Fever and Dehydration");

        patientTree.insert(p1);
        patientTree.insert(p2);
        patientTree.insert(p3);
        patientTree.insert(p4);
        patientTree.insert(p5);

        // Sample Visit History for Patient 102 (Nimal)
        p2.getVisitHistory().addVisit(new VisitRecord(1, "2026-01-10", "Dr. Jayasinghe", "Mild Gastritis", "Antacids given"));
        p2.getVisitHistory().addVisit(new VisitRecord(2, "2026-02-15", "Dr. Jayasinghe", "Follow-up", "Condition improved"));

        // Sample Visit History for Patient 105 (Kamal)
        p1.getVisitHistory().addVisit(new VisitRecord(3, "2026-03-01", "Dr. Alwis", "Seasonal Allergy", "Antihistamine prescribed"));

        // Sample Emergency Queue (FIFO)
        emergencyQueue.enqueue(p2); // Nimal arrives first
        emergencyQueue.enqueue(p1); // Kamal arrives second
        emergencyQueue.enqueue(p4); // Anura arrives third

        // Sample Completed Treatment Stack (LIFO)
        treatmentStack.push(new TreatmentRecord(103, "Kasun Dissanayake", "Dr. Wickrama", "Food Poisoning", "IV fluids administered", "2026-10-10"));
        treatmentStack.push(new TreatmentRecord(106, "Manoja Wickramasinghe", "Dr. Wickrama", "Migraine", "Pain relief injection", "2026-10-11"));
    }
}
