/**
 * Node class for the Emergency Patient Queue.
 */
public class QueueNode {
    Patient patient;
    QueueNode next;

    public QueueNode(Patient patient) {
        this.patient = patient;
        this.next = null;
    }
}
