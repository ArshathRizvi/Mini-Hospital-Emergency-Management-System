/**
 * Node class for the Patient Binary Search Tree (BST).
 * Uses Patient ID as the key for ordering.
 */
public class BSTNode {
    Patient patient;
    BSTNode left;
    BSTNode right;

    public BSTNode(Patient patient) {
        this.patient = patient;
        this.left = null;
        this.right = null;
    }
}
