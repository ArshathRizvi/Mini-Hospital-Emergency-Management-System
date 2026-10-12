/**
 * Stack implementation to store and manage completed treatment records.
 * Follows the LIFO (Last-In, First-Out) principle.
 */
public class TreatmentStack {
    private StackNode top;
    private int size;

    public TreatmentStack() {
        this.top = null;
        this.size = 0;
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // Push: Add a completed treatment record to the top of the stack
    public void push(TreatmentRecord record) {
        if (record == null) {
            return;
        }
        StackNode newNode = new StackNode(record);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Pop: Remove and return the most recently completed treatment record
    public TreatmentRecord pop() {
        if (isEmpty()) {
            return null; // Appropriate handling of empty stack
        }
        TreatmentRecord removedRecord = top.record;
        top = top.next;
        size--;
        return removedRecord;
    }

    // Peek: View the most recent treatment record without removing
    public TreatmentRecord peek() {
        if (isEmpty()) {
            return null;
        }
        return top.record;
    }

    // Display all treatment records from most recent to oldest (LIFO order)
    public void displayHistory() {
        if (isEmpty()) {
            System.out.println("Treatment history stack is empty. No completed treatment records found.");
            return;
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.println("TREATMENT HISTORY (LIFO Order - Most Recent First, Total Records: " + size + "):");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-18s | %-15s | %-20s | %-22s | %-12s |\n", 
                          "ID", "Patient Name", "Doctor", "Diagnosis", "Treatment Given", "Date");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        StackNode current = top;
        while (current != null) {
            current.record.display();
            current = current.next;
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
    }

    public int getSize() {
        return size;
    }
}
