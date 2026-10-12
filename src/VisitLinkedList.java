/**
 * Singly Linked List implementation to store and manage a patient's visit history.
 * Follows the Singly Linked List data structure concepts.
 */
public class VisitLinkedList {
    private VisitNode head;

    public VisitLinkedList() {
        this.head = null;
    }

    // Check if the visit history is empty
    public boolean isEmpty() {
        return head == null;
    }

    // Adding a new visit to the patient's history (appends to the end)
    public void addVisit(VisitRecord visit) {
        VisitNode newNode = new VisitNode(visit);
        if (head == null) {
            head = newNode;
        } else {
            VisitNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Removing a visit by Visit ID
    public boolean removeVisit(int visitId) {
        if (head == null) {
            return false;
        }

        // If the visit to remove is the first node
        if (head.visit.getVisitId() == visitId) {
            head = head.next;
            return true;
        }

        // Search for the node prior to the one to be deleted
        VisitNode current = head;
        while (current.next != null && current.next.visit.getVisitId() != visitId) {
            current = current.next;
        }

        // If found, unlink it
        if (current.next != null) {
            current.next = current.next.next;
            return true;
        }

        return false; // Not found
    }

    // Searching for a visit by Visit ID
    public VisitRecord searchVisit(int visitId) {
        VisitNode current = head;
        while (current != null) {
            if (current.visit.getVisitId() == visitId) {
                return current.visit;
            }
            current = current.next;
        }
        return null;
    }

    // Displaying the patient's visit history
    public void displayVisits() {
        if (head == null) {
            System.out.println("    (No previous visit history found for this patient.)");
            return;
        }

        VisitNode current = head;
        while (current != null) {
            current.visit.display();
            current = current.next;
        }
    }
}
