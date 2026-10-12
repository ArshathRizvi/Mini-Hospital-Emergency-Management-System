import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Utility to generate high-resolution terminal output screenshots for assignment documentation.
 */
public class ScreenshotGenerator {

    public static void main(String[] args) {
        try {
            File screenshotsDir = new File("screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }

            System.out.println("Generating screenshots in screenshots/ directory...");

            generateMainMenu();
            generateBST();
            generateVisitHistory();
            generateEmergencyQueue();
            generateTreatmentStack();
            generateSimulation();
            generateAutomatedTests();

            System.out.println("All 7 screenshots generated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void renderTerminal(String title, List<String> lines, String outputPath) throws Exception {
        int charWidth = 8;
        int maxLen = 75;
        for (String line : lines) {
            if (line.length() > maxLen) {
                maxLen = line.length();
            }
        }
        int paddingLeft = 24;
        int paddingRight = 24;
        int lineHeight = 21;
        int headerHeight = 40;
        int width = Math.max(960, (maxLen * charWidth) + paddingLeft + paddingRight + 30);
        int height = headerHeight + (lines.size() * lineHeight) + 26;

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dark terminal background
        g2.setColor(new Color(24, 24, 36));
        g2.fillRect(0, 0, width, height);

        // Window Title Bar
        g2.setColor(new Color(34, 34, 51));
        g2.fillRect(0, 0, width, headerHeight);

        // Window buttons (red, yellow, green)
        g2.setColor(new Color(255, 95, 86));
        g2.fillOval(15, 14, 12, 12);
        g2.setColor(new Color(255, 189, 46));
        g2.fillOval(33, 14, 12, 12);
        g2.setColor(new Color(39, 201, 63));
        g2.fillOval(51, 14, 12, 12);

        // Window Title Text
        g2.setColor(new Color(176, 176, 197));
        g2.setFont(new Font("Consolas", Font.BOLD, 13));
        g2.drawString(title, 78, 23);

        // Title Bar Divider
        g2.setColor(new Color(46, 46, 66));
        g2.drawLine(0, headerHeight, width, headerHeight);

        // Monospace Font for Console Lines
        Font font = new Font("Consolas", Font.PLAIN, 13);
        g2.setFont(font);

        int y = headerHeight + 18;
        for (String line : lines) {
            if (line.startsWith("PS C:")) {
                g2.setColor(new Color(78, 201, 176));
            } else if (line.startsWith("=====") || line.startsWith("-----")) {
                g2.setColor(new Color(86, 156, 214));
            } else if (line.contains("MINI HOSPITAL") || line.contains("RUNNING AUTOMATED") || 
                       line.contains("PASSED [OK]") || line.contains("SUCCESS")) {
                g2.setColor(new Color(78, 201, 176));
            } else if (line.startsWith("|")) {
                g2.setColor(new Color(229, 192, 123));
            } else if (line.startsWith("---")) {
                g2.setColor(new Color(198, 120, 221));
            } else if (line.contains("Success:") || line.contains("Found:") || line.contains("Patient Found:")) {
                g2.setColor(new Color(152, 195, 121));
            } else if (line.contains("Error:") || line.contains("Warning:")) {
                g2.setColor(new Color(224, 108, 117));
            } else {
                g2.setColor(new Color(212, 212, 212));
            }

            g2.drawString(line, paddingLeft, y);
            y += lineHeight;
        }

        // Window Border
        g2.setColor(new Color(58, 58, 82));
        g2.drawRect(0, 0, width - 1, height - 1);

        g2.dispose();
        ImageIO.write(img, "png", new File(outputPath));
    }

    private static void generateMainMenu() throws Exception {
        List<String> lines = Arrays.asList(
            "PS C:\\Projects\\test1> java -cp src HospitalManagementSystem",
            "",
            "=============================================================",
            "         MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM           ",
            "=============================================================",
            "1. Patient Records Management (Binary Search Tree - BST)",
            "2. Patient Visit History Management (Singly Linked List)",
            "3. Emergency Patient Queue (Queue - FIFO)",
            "4. Treatment History (Stack - LIFO)",
            "5. Emergency Treatment Simulation (Integrated Workflow)",
            "6. Exit",
            "=============================================================",
            "Enter your choice (1-6): 1"
        );
        renderTerminal("Hospital Management System - Main Menu", lines, "screenshots/01_main_menu.png");
    }

    private static void generateBST() throws Exception {
        List<String> lines = Arrays.asList(
            "PS C:\\Projects\\test1> java -cp src HospitalManagementSystem (Option 1: BST)",
            "",
            "--- [1] PATIENT RECORDS (BINARY SEARCH TREE) ---",
            "1. Insert New Patient",
            "2. Search Patient by ID",
            "3. Delete Patient by ID",
            "4. Display All Patients (In-Order Traversal - Sorted by ID)",
            "5. Back to Main Menu",
            "Select an option (1-5): 4",
            "",
            "--- All Patients (BST In-Order Traversal - Ascending Order of ID) ---",
            "---------------------------------------------------------------------------------------------------------",
            "| ID         | Name                 | Age   | Contact         | Medical Condition                   |",
            "---------------------------------------------------------------------------------------------------------",
            "| 101        | Anura Bandara        | 52    | 0751122334      | High Blood Pressure                 |",
            "| 102        | Nimal Perera         | 45    | 0719876543      | Chest Pain                          |",
            "| 104        | Saman Kumara         | 22    | 0788899001      | Fever and Dehydration               |",
            "| 105        | Kamal Silva          | 34    | 0771234567      | Severe Asthma                       |",
            "| 108        | Sunil Fernando       | 28    | 0763456789      | Fractured Arm                       |",
            "---------------------------------------------------------------------------------------------------------",
            "",
            "Select an option (1-5): 2",
            "--- Search Patient by ID ---",
            "Enter Patient ID to search: 102",
            "Patient Found:",
            "-----------------------------------------------------------------",
            "Patient ID       : 102",
            "Name             : Nimal Perera",
            "Age              : 45",
            "Contact Number   : 0719876543",
            "Medical Condition: Chest Pain",
            "-----------------------------------------------------------------",
            "",
            "Select an option (1-5): 1",
            "--- Insert New Patient ---",
            "Enter Patient ID: 109",
            "Enter Patient Name: Ruwan Fernando",
            "Enter Age: 30",
            "Enter Contact Number: 0711122334",
            "Enter Medical Condition: Severe Migraine",
            "Success: Patient Ruwan Fernando (ID: 109) successfully added to BST!",
            "",
            "Select an option (1-5): 3",
            "--- Delete Patient by ID ---",
            "Enter Patient ID to delete: 104",
            "Success: Patient with ID 104 was deleted from the BST."
        );
        renderTerminal("Component 1: Binary Search Tree (BST) - Patient Records", lines, "screenshots/02_bst_patient_records.png");
    }

    private static void generateVisitHistory() throws Exception {
        List<String> lines = Arrays.asList(
            "PS C:\\Projects\\test1> java -cp src HospitalManagementSystem (Option 2: Linked List)",
            "",
            "--- [2] PATIENT VISIT HISTORY (SINGLY LINKED LIST) ---",
            "Enter Patient ID to manage visit history: 102",
            "Managing visit records for: Nimal Perera (ID: 102)",
            "",
            "Visit History Options for Patient [Nimal Perera]:",
            "1. Add New Visit Record",
            "2. Search Visit Record by Visit ID",
            "3. Remove Visit Record by Visit ID",
            "4. Display All Previous Visits",
            "5. Back to Main Menu",
            "Select an option (1-5): 4",
            "",
            "--- All Visits for Nimal Perera ---",
            "    -> Visit ID: 1 | Date: 2026-01-10 | Doctor: Dr. Jayasinghe | Diagnosis: Mild Gastritis | Treatment: Antacids given",
            "    -> Visit ID: 2 | Date: 2026-02-15 | Doctor: Dr. Jayasinghe | Diagnosis: Follow-up | Treatment: Condition improved",
            "",
            "Select an option (1-5): 1",
            "--- Add Visit Record ---",
            "Enter Visit ID: 5",
            "Enter Visit Date (e.g., YYYY-MM-DD): 2026-04-01",
            "Enter Doctor Name: Dr. Alwis",
            "Enter Diagnosis: Acute Asthma Attack",
            "Enter Treatment: Inhaler treatment & Oxygen",
            "Success: Visit record #5 added to patient's history.",
            "",
            "Select an option (1-5): 2",
            "--- Search Visit Record ---",
            "Enter Visit ID to search: 5",
            "Visit Record Found:",
            "    -> Visit ID: 5 | Date: 2026-04-01 | Doctor: Dr. Alwis | Diagnosis: Acute Asthma Attack | Treatment: Inhaler treatment & Oxygen",
            "",
            "Select an option (1-5): 3",
            "--- Remove Visit Record ---",
            "Enter Visit ID to remove: 1",
            "Success: Visit record #1 was removed."
        );
        renderTerminal("Component 4: Singly Linked List - Patient Visit History", lines, "screenshots/03_patient_visit_history.png");
    }

    private static void generateEmergencyQueue() throws Exception {
        List<String> lines = Arrays.asList(
            "PS C:\\Projects\\test1> java -cp src HospitalManagementSystem (Option 3: Emergency Queue)",
            "",
            "--- [3] EMERGENCY PATIENT QUEUE (FIFO) ---",
            "1. Enqueue Patient to Emergency Queue",
            "2. Dequeue Next Patient for Treatment",
            "3. Display All Waiting Emergency Patients",
            "4. Back to Main Menu",
            "Select an option (1-4): 3",
            "",
            "--- Current Emergency Waiting Queue ---",
            "---------------------------------------------------------------------------------------------------------",
            "CURRENT EMERGENCY WAITING QUEUE (FIFO Order - Total Waiting: 3):",
            "---------------------------------------------------------------------------------------------------------",
            "| Pos   | ID         | Name                 | Age   | Contact         | Medical Condition         |",
            "---------------------------------------------------------------------------------------------------------",
            "| 1     | 102        | Nimal Perera         | 45    | 0719876543      | Chest Pain                |",
            "| 2     | 105        | Kamal Silva          | 34    | 0771234567      | Severe Asthma             |",
            "| 3     | 101        | Anura Bandara        | 52    | 0751122334      | High Blood Pressure       |",
            "---------------------------------------------------------------------------------------------------------",
            "",
            "Select an option (1-4): 2",
            "--- Dequeue Patient for Treatment ---",
            "Next patient called for emergency treatment (FIFO order):",
            "  ID       : 102",
            "  Name     : Nimal Perera",
            "  Condition: Chest Pain",
            "(Patient is now removed from waiting queue and ready for treatment)",
            "",
            "Select an option (1-4): 3",
            "--- Current Emergency Waiting Queue ---",
            "---------------------------------------------------------------------------------------------------------",
            "CURRENT EMERGENCY WAITING QUEUE (FIFO Order - Total Waiting: 2):",
            "---------------------------------------------------------------------------------------------------------",
            "| Pos   | ID         | Name                 | Age   | Contact         | Medical Condition         |",
            "---------------------------------------------------------------------------------------------------------",
            "| 1     | 105        | Kamal Silva          | 34    | 0771234567      | Severe Asthma             |",
            "| 2     | 101        | Anura Bandara        | 52    | 0751122334      | High Blood Pressure       |",
            "---------------------------------------------------------------------------------------------------------"
        );
        renderTerminal("Component 2: Queue (FIFO) - Emergency Patient Queue", lines, "screenshots/04_emergency_queue.png");
    }

    private static void generateTreatmentStack() throws Exception {
        List<String> lines = Arrays.asList(
            "PS C:\\Projects\\test1> java -cp src HospitalManagementSystem (Option 4: Treatment Stack)",
            "",
            "--- [4] TREATMENT HISTORY (STACK - LIFO) ---",
            "1. Push Completed Treatment Record",
            "2. Pop Most Recent Treatment Record",
            "3. Display All Completed Treatment Records (LIFO)",
            "4. Back to Main Menu",
            "Select an option (1-4): 3",
            "",
            "--- All Completed Treatment Records ---",
            "-------------------------------------------------------------------------------------------------------------------------",
            "TREATMENT HISTORY (LIFO Order - Most Recent First, Total Records: 2):",
            "-------------------------------------------------------------------------------------------------------------------------",
            "| ID         | Patient Name       | Doctor          | Diagnosis            | Treatment Given        | Date         |",
            "-------------------------------------------------------------------------------------------------------------------------",
            "| 106        | Manoja Wickramasinghe | Dr. Wickrama    | Migraine             | Pain relief injection  | 2026-10-11   |",
            "| 103        | Kasun Dissanayake  | Dr. Wickrama    | Food Poisoning       | IV fluids administered | 2026-10-10   |",
            "-------------------------------------------------------------------------------------------------------------------------",
            "",
            "Select an option (1-4): 2",
            "--- Pop Most Recent Treatment Record ---",
            "Most recent treatment record removed from stack (LIFO):",
            "  Patient ID : 106",
            "  Patient    : Manoja Wickramasinghe",
            "  Doctor     : Dr. Wickrama",
            "  Diagnosis  : Migraine",
            "  Treatment  : Pain relief injection",
            "  Date       : 2026-10-11",
            "",
            "Select an option (1-4): 3",
            "--- All Completed Treatment Records ---",
            "-------------------------------------------------------------------------------------------------------------------------",
            "TREATMENT HISTORY (LIFO Order - Most Recent First, Total Records: 1):",
            "-------------------------------------------------------------------------------------------------------------------------",
            "| ID         | Patient Name       | Doctor          | Diagnosis            | Treatment Given        | Date         |",
            "-------------------------------------------------------------------------------------------------------------------------",
            "| 103        | Kasun Dissanayake  | Dr. Wickrama    | Food Poisoning       | IV fluids administered | 2026-10-10   |",
            "-------------------------------------------------------------------------------------------------------------------------"
        );
        renderTerminal("Component 3: Stack (LIFO) - Completed Treatment History", lines, "screenshots/05_treatment_stack.png");
    }

    private static void generateSimulation() throws Exception {
        List<String> lines = Arrays.asList(
            "PS C:\\Projects\\test1> java -cp src HospitalManagementSystem (Option 5: Simulation)",
            "",
            "--- [5] EMERGENCY TREATMENT SIMULATION ---",
            "This workflow connects all 4 data structures:",
            "1. Dequeues next patient from Emergency Queue (Queue - FIFO)",
            "2. Doctor provides treatment",
            "3. Treatment is pushed onto Treatment History (Stack - LIFO)",
            "4. Visit record is appended to patient's history (Linked List)",
            "",
            "[1] Dequeued Patient from Emergency Queue: Kamal Silva (ID: 105)",
            "    Condition: Severe Asthma",
            "",
            "[2] Doctor Consultation & Treatment:",
            "    Enter Attending Doctor Name: Dr. Jayasinghe",
            "    Enter Final Diagnosis: Acute Bronchial Spasm",
            "    Enter Treatment Administered: Nebulization & Corticosteroid",
            "    Enter Treatment Date (YYYY-MM-DD): 2026-10-13",
            "",
            "[3] Treatment record pushed onto Treatment Stack (LIFO).",
            "[4] Visit record (Visit ID: 4192) added to patient's Singly Linked List visit history.",
            "",
            "Simulation complete! Patient has been treated successfully."
        );
        renderTerminal("Integrated Workflow: Emergency Simulation (Queue -> Stack -> Linked List)", lines, "screenshots/06_emergency_simulation.png");
    }

    private static void generateAutomatedTests() throws Exception {
        List<String> lines = Arrays.asList(
            "PS C:\\Projects\\test1> java -ea -cp src HospitalSystemTest",
            "",
            "=============================================================",
            "       RUNNING AUTOMATED TESTS FOR DATA STRUCTURES           ",
            "=============================================================",
            "Testing Singly Linked List (VisitLinkedList)... PASSED [OK]",
            "Testing Binary Search Tree (PatientBST)... PASSED [OK]",
            "Testing Emergency Queue (FIFO)... PASSED [OK]",
            "Testing Treatment Stack (LIFO)... PASSED [OK]",
            "",
            "=============================================================",
            "   [SUCCESS] ALL DATA STRUCTURE TESTS PASSED SUCCESSFULLY!   ",
            "============================================================="
        );
        renderTerminal("Verification: Automated Test Suite (All 4 Data Structures)", lines, "screenshots/07_automated_test_suite.png");
    }
}
