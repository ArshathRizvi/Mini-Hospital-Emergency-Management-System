/**
 * Queue implementation for managing patients arriving at the emergency unit.
 * Follows the FIFO (First-In, First-Out) principle.
 */
public class EmergencyQueue {
    private QueueNode front;
    private QueueNode rear;
    private int size;

    public EmergencyQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }

    // Enqueue - add a patient to the waiting queue (at rear)
    public void enqueue(Patient patient) {
        if (patient == null) {
            return;
        }
        QueueNode newNode = new QueueNode(patient);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // Dequeue - remove and return the next patient for treatment (from front)
    public Patient dequeue() {
        if (isEmpty()) {
            return null; // Appropriate handling of empty queue
        }
        Patient patientToTreat = front.patient;
        front = front.next;
        if (front == null) {
            rear = null; // Queue is now empty
        }
        size--;
        return patientToTreat;
    }

    // Peek at the next patient without removing
    public Patient peek() {
        if (isEmpty()) {
            return null;
        }
        return front.patient;
    }

    // Display all patients currently waiting
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patients currently waiting.");
            return;
        }

        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("CURRENT EMERGENCY WAITING QUEUE (FIFO Order - Total Waiting: " + size + "):");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-10s | %-20s | %-5s | %-15s | %-25s |\n", 
                          "Pos", "ID", "Name", "Age", "Contact", "Medical Condition");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        QueueNode current = front;
        int position = 1;
        while (current != null) {
            Patient p = current.patient;
            System.out.printf("| %-5d | %-10d | %-20s | %-5d | %-15s | %-25s |\n", 
                              position, p.getPatientId(), p.getName(), p.getAge(), p.getContactNumber(), p.getMedicalCondition());
            current = current.next;
            position++;
        }
        System.out.println("---------------------------------------------------------------------------------------------------------");
    }

    public int getSize() {
        return size;
    }
}
