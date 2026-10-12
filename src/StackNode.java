/**
 * Node class for the Treatment History Stack.
 */
public class StackNode {
    TreatmentRecord record;
    StackNode next;

    public StackNode(TreatmentRecord record) {
        this.record = record;
        this.next = null;
    }
}
