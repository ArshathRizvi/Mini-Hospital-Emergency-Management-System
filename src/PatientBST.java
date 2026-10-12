/**
 * Binary Search Tree (BST) implementation to store and manage Patient records.
 * Uses Patient ID as the key.
 */
public class PatientBST {
    private BSTNode root;

    public PatientBST() {
        this.root = null;
    }

    // Check if the tree is empty
    public boolean isEmpty() {
        return root == null;
    }

    // Insert a new patient into the BST
    public boolean insert(Patient patient) {
        if (patient == null) {
            return false;
        }
        if (search(patient.getPatientId()) != null) {
            return false; // Patient ID already exists
        }
        root = insertRec(root, patient);
        return true;
    }

    // Helper recursive method for insertion
    private BSTNode insertRec(BSTNode current, Patient patient) {
        if (current == null) {
            return new BSTNode(patient);
        }

        if (patient.getPatientId() < current.patient.getPatientId()) {
            current.left = insertRec(current.left, patient);
        } else if (patient.getPatientId() > current.patient.getPatientId()) {
            current.right = insertRec(current.right, patient);
        }

        return current;
    }

    // Temporary basic search used during insert
    public Patient search(int patientId) {
        return searchRec(root, patientId);
    }

    private Patient searchRec(BSTNode current, int patientId) {
        if (current == null) {
            return null;
        }
        if (current.patient.getPatientId() == patientId) {
            return current.patient;
        }
        if (patientId < current.patient.getPatientId()) {
            return searchRec(current.left, patientId);
        } else {
            return searchRec(current.right, patientId);
        }
    }

    // Perform an in-order traversal to display patients in ascending order of Patient ID
    public void inOrderTraversal() {
        if (root == null) {
            System.out.println("No patient records found in the system.");
            return;
        }
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-5s | %-15s | %-35s |\n", "ID", "Name", "Age", "Contact", "Medical Condition");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        inOrderRec(root);
        System.out.println("---------------------------------------------------------------------------------------------------------");
    }

    // Helper recursive method for in-order traversal (Left, Root, Right)
    private void inOrderRec(BSTNode current) {
        if (current != null) {
            inOrderRec(current.left);
            current.patient.displaySummary();
            inOrderRec(current.right);
        }
    }
}
