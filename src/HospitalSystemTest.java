/**
 * Standalone automated test class to verify each data structure implementation.
 * Can be executed without external testing libraries:
 *   javac src/*.java
 *   java -cp src HospitalSystemTest
 */
public class HospitalSystemTest {

    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println("       RUNNING AUTOMATED TESTS FOR DATA STRUCTURES");
        System.out.println("=============================================================");

        testVisitLinkedList();
        testPatientBST();
        testEmergencyQueue();
        testTreatmentStack();

        System.out.println("\n=============================================================");
        System.out.println("   [SUCCESS] ALL DATA STRUCTURE TESTS PASSED SUCCESSFULLY!");
        System.out.println("=============================================================");
    }

    private static void testVisitLinkedList() {
        System.out.print("Testing Singly Linked List (VisitLinkedList)... ");
        VisitLinkedList list = new VisitLinkedList();
        assert list.isEmpty() : "List should initially be empty";

        // Test Add
        list.addVisit(new VisitRecord(1, "2026-01-01", "Dr. A", "Flu", "Rest"));
        list.addVisit(new VisitRecord(2, "2026-02-01", "Dr. B", "Cough", "Syrup"));
        assert !list.isEmpty() : "List should not be empty after add";

        // Test Search
        VisitRecord v = list.searchVisit(1);
        assert v != null && v.getDoctorName().equals("Dr. A") : "Search for visit 1 failed";
        assert list.searchVisit(99) == null : "Search for non-existent visit should return null";

        // Test Remove
        boolean removed = list.removeVisit(1);
        assert removed : "Removal of visit 1 should return true";
        assert list.searchVisit(1) == null : "Removed visit should not be found";

        boolean removeNonExistent = list.removeVisit(99);
        assert !removeNonExistent : "Removal of non-existent visit should return false";

        System.out.println("PASSED [OK]");
    }

    private static void testPatientBST() {
        System.out.print("Testing Binary Search Tree (PatientBST)... ");
        PatientBST bst = new PatientBST();
        assert bst.isEmpty() : "BST should initially be empty";

        // Test Insert
        Patient p1 = new Patient(105, "Kamal", 30, "0771111111", "Condition A");
        Patient p2 = new Patient(102, "Nimal", 40, "0772222222", "Condition B");
        Patient p3 = new Patient(108, "Sunil", 25, "0773333333", "Condition C");
        Patient p4 = new Patient(101, "Anura", 50, "0774444444", "Condition D");

        assert bst.insert(p1) : "Insert p1 should succeed";
        assert bst.insert(p2) : "Insert p2 should succeed";
        assert bst.insert(p3) : "Insert p3 should succeed";
        assert bst.insert(p4) : "Insert p4 should succeed";
        assert !bst.insert(p1) : "Duplicate insert should return false";

        // Test Search
        Patient found = bst.search(102);
        assert found != null && found.getName().equals("Nimal") : "Search for ID 102 failed";
        assert bst.search(999) == null : "Search for non-existent ID should return null";

        // Test Delete (Leaf node 101)
        assert bst.delete(101) : "Delete leaf node 101 should succeed";
        assert bst.search(101) == null : "ID 101 should no longer exist";

        // Test Delete (Node with 2 children 105)
        assert bst.delete(105) : "Delete node 105 with two children should succeed";
        assert bst.search(105) == null : "ID 105 should no longer exist";
        assert bst.search(102) != null : "Remaining nodes should still be intact";
        assert bst.search(108) != null : "Remaining nodes should still be intact";

        // Test Delete non-existent
        assert !bst.delete(999) : "Delete non-existent ID should return false";

        System.out.println("PASSED [OK]");
    }

    private static void testEmergencyQueue() {
        System.out.print("Testing Emergency Queue (FIFO)... ");
        EmergencyQueue queue = new EmergencyQueue();
        assert queue.isEmpty() : "Queue should initially be empty";
        assert queue.dequeue() == null : "Dequeue from empty queue should return null";

        Patient p1 = new Patient(1, "First", 20, "111", "Mild");
        Patient p2 = new Patient(2, "Second", 30, "222", "Medium");
        Patient p3 = new Patient(3, "Third", 40, "333", "Severe");

        queue.enqueue(p1);
        queue.enqueue(p2);
        queue.enqueue(p3);
        assert queue.getSize() == 3 : "Queue size should be 3";

        // FIFO verification
        Patient out1 = queue.dequeue();
        assert out1.getPatientId() == 1 : "First enqueued should be first dequeued (FIFO)";

        Patient out2 = queue.dequeue();
        assert out2.getPatientId() == 2 : "Second enqueued should be second dequeued";

        Patient out3 = queue.dequeue();
        assert out3.getPatientId() == 3 : "Third enqueued should be third dequeued";

        assert queue.isEmpty() : "Queue should be empty after dequeuing all";
        assert queue.dequeue() == null : "Dequeue on empty queue should safely return null";

        System.out.println("PASSED [OK]");
    }

    private static void testTreatmentStack() {
        System.out.print("Testing Treatment Stack (LIFO)... ");
        TreatmentStack stack = new TreatmentStack();
        assert stack.isEmpty() : "Stack should initially be empty";
        assert stack.pop() == null : "Pop on empty stack should return null";

        TreatmentRecord r1 = new TreatmentRecord(1, "Patient A", "Dr. X", "Cold", "Meds", "2026-10-01");
        TreatmentRecord r2 = new TreatmentRecord(2, "Patient B", "Dr. Y", "Fever", "Paracetamol", "2026-10-02");

        stack.push(r1);
        stack.push(r2);
        assert stack.getSize() == 2 : "Stack size should be 2";

        // LIFO verification
        TreatmentRecord popped1 = stack.pop();
        assert popped1.getPatientId() == 2 : "Last pushed should be first popped (LIFO)";

        TreatmentRecord popped2 = stack.pop();
        assert popped2.getPatientId() == 1 : "First pushed should be second popped";

        assert stack.isEmpty() : "Stack should be empty after popping all";
        assert stack.pop() == null : "Pop on empty stack should safely return null";

        System.out.println("PASSED [OK]");
    }
}
