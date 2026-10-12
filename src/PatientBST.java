/**
 * Binary Search Tree (BST) implementation to store and manage Patient records.
 * Uses Patient ID as the key for all operations.
 */
public class PatientBST {
    private BSTNode root;
    private boolean deleteSuccess; // Helper flag to track deletion outcome

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
        // Duplicate Patient ID check
        if (search(patient.getPatientId()) != null) {
            return false;
        }
        root = insertRec(root, patient);
        return true;
    }

    // Recursive helper for insertion
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

    // Search for a patient using Patient ID
    public Patient search(int patientId) {
        return searchRec(root, patientId);
    }

    // Recursive helper for search
    private Patient searchRec(BSTNode current, int patientId) {
        if (current == null) {
            return null; // Not found
        }
        if (current.patient.getPatientId() == patientId) {
            return current.patient; // Found
        }
        if (patientId < current.patient.getPatientId()) {
            return searchRec(current.left, patientId);
        } else {
            return searchRec(current.right, patientId);
        }
    }

    // Delete a patient using Patient ID
    public boolean delete(int patientId) {
        deleteSuccess = false;
        root = deleteRec(root, patientId);
        return deleteSuccess;
    }

    // Recursive helper for deletion
    private BSTNode deleteRec(BSTNode current, int patientId) {
        if (current == null) {
            return null;
        }

        if (patientId < current.patient.getPatientId()) {
            current.left = deleteRec(current.left, patientId);
        } else if (patientId > current.patient.getPatientId()) {
            current.right = deleteRec(current.right, patientId);
        } else {
            // Node to be deleted found!
            deleteSuccess = true;

            // Case 1: Node has no children (leaf) or only right child
            if (current.left == null) {
                return current.right;
            }
            // Case 2: Node has only left child
            else if (current.right == null) {
                return current.left;
            }

            // Case 3: Node has two children
            // Find in-order successor (smallest node in the right subtree)
            BSTNode successor = findMin(current.right);
            // Copy successor's patient record into current node
            current.patient = successor.patient;
            // Delete successor from right subtree
            current.right = deleteRec(current.right, successor.patient.getPatientId());
        }

        return current;
    }

    // Helper method to find minimum value node (in-order successor)
    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
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

    // Recursive helper for in-order traversal (Left -> Root -> Right)
    private void inOrderRec(BSTNode current) {
        if (current != null) {
            inOrderRec(current.left);
            current.patient.displaySummary();
            inOrderRec(current.right);
        }
    }
}
