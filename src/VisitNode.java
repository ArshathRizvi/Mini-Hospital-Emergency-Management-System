/**
 * Node class for the Singly Linked List holding patient visit records.
 */
public class VisitNode {
    VisitRecord visit;
    VisitNode next;

    public VisitNode(VisitRecord visit) {
        this.visit = visit;
        this.next = null;
    }
}
