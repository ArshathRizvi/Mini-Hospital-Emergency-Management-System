# CIT300 - Data Structures and Algorithms
# Individual Mid Assignment: Video Demonstration Script (5 - 8 Minutes)

**Student Name:** Arshath Rizvi  
**Course:** CIT300 - Data Structures and Algorithms  
**Assignment Title:** Mini Hospital Emergency Management System Using Data Structures  
**Target Duration:** 6 - 8 Minutes (Meets university requirement: 5 - 10 minutes)  

---

## Important Notice on University Marking & Academic Integrity

> **[IMPORTANT] Requirement 1 (Component 6):**  
> *"A brief introduction. The student's face must be visible during the introduction."*  
> The university evaluators (`asanka.r@sltc.ac.lk` and `kaushika.w@sltc.ac.lk`) require your face and voice to verify authenticity.  
> You can easily record this using:
> - **Windows 11 Clipchamp** (Pre-installed: Choose *Record screen and camera*)
> - **OBS Studio** (Free: Screen capture with webcam in corner)
> - **Zoom / Google Meet** (Start a personal meeting, share screen, turn on camera, click *Record to Computer*)

---

## Detailed Section-by-Section Recording Script

### Section 1: Introduction (Face Visible on Camera)
**Timestamp:** `0:00 - 1:00`  
**What to show:** Webcam with your face clearly visible, then switch to or show title slide / project folder.  
**What to say (Word-for-Word):**
> *"Hello, my name is Arshath Rizvi, and this is my video demonstration for the CIT300 Data Structures and Algorithms Individual Mid Assignment.*  
> *The title of my project is the Mini Hospital Emergency Management System.*  
> *In this project, I have designed and implemented a console-based hospital emergency simulation in Java.*  
> *The system simulates the entire workflow of patient arrival, registration, emergency queueing, doctor consultation, treatment completion, and patient medical visit history.*  
> *To fulfill the assignment requirements without relying on Java's built-in collections, I have implemented four core data structures from scratch:*  
> *First, a Binary Search Tree for patient records.*  
> *Second, a custom Queue for emergency patient waiting.*  
> *Third, a custom Stack for completed treatment history.*  
> *And fourth, a Singly Linked List for each patient's previous visit history.*  
> *Now, let me walk you through my GitHub repository and the running application."*

---

### Section 2: GitHub Repository & Commit History
**Timestamp:** `1:00 - 1:45`  
**What to show:** Open your browser with:  
`https://github.com/ArshathRizvi/Mini-Hospital-Emergency-Management-System`  
Scroll through the repository files, README, and click on **Commits** (showing the 10 commits).  
**What to say:**
> *"Here is my public GitHub repository under my personal account, ArshathRizvi.*  
> *As required by the marking scheme, the project was developed progressively with meaningful commits demonstrating each development milestone.*  
> *As you can see in the commit history:*  
> *- I started by setting up the project structure and `.gitignore`.*  
> *- Then implemented the patient visit history using a Singly Linked List.*  
> *- Next, I built the Patient records using a Binary Search Tree, followed by adding search and 3-case deletion operations.*  
> *- Afterwards, I implemented the Emergency Queue following FIFO and the Treatment History Stack following LIFO.*  
> *- Finally, I integrated the interactive console menu, automated test assertions, and generated full verification screenshots.*  
> *All source code is cleanly modularized inside the `src/` directory."*

---

### Section 3: Component 1 — Binary Search Tree (BST)
**Timestamp:** `1:45 - 3:15`  
**What to show:** Terminal running `java -cp src HospitalManagementSystem`.  
**Step 1:** Press `1` (Patient Records BST).  
**Step 2:** Press `4` (Display In-Order Traversal).  
**What to say:**
> *"Now let's launch the application in the terminal using `java -cp src HospitalManagementSystem`.*  
> *First, I will demonstrate Component 1: Patient Records using a Binary Search Tree.*  
> *In my BST implementation, the Patient ID serves as the unique key.*  
> *When I choose option 4 to perform an In-Order Traversal, the system traverses the tree recursively in Left-Root-Right order.*  
> *Notice that all patients are displayed in ascending sorted order of their Patient IDs: 101, 102, 104, 105, and 108.*  
> *Next, let's search for patient 102 by entering option 2 and ID 102.*  
> *The search runs in O(log n) time, locating Nimal Perera with Chest Pain and displaying his full details.*  
> *Now let's insert a new patient using option 1: Patient ID 109, Ruwan Fernando, age 30, contact number, and condition.*  
> *The patient is placed in the right subtree of 108.*  
> *Finally, I demonstrate BST deletion with option 3: let's delete patient 104.*  
> *My deletion algorithm correctly handles leaf nodes, single-child nodes, and two-child nodes by finding the in-order successor.*  
> *When we display in-order traversal again, ID 104 is gone and 109 is properly sorted at the end."*

---

### Section 4: Component 4 — Patient Visit History (Singly Linked List)
**Timestamp:** `3:15 - 4:15`  
**What to show:** Main Menu $\rightarrow$ Option `2` (Visit History) $\rightarrow$ Enter ID `102`.  
**What to say:**
> *"Next is Component 4: Patient Visit History implemented using a Singly Linked List.*  
> *Each patient record in the BST contains their own independent Singly Linked List.*  
> *Let's enter Patient ID 102.*  
> *When I choose option 4, it traverses the linked list from `head` to display Nimal's previous visits.*  
> *Let's add a new visit using option 1:*  
> *Visit ID: 5, Date: 2026-04-01, Doctor: Dr. Alwis, Diagnosis: Acute Asthma, Treatment: Inhaler prescribed.*  
> *The new visit node is appended dynamically to the end of the list.*  
> *We can search for this visit by ID using option 2, or remove an old visit (such as Visit ID 1) using option 3.*  
> *The pointer unlinking works cleanly without any memory leaks or broken references."*

---

### Section 5: Component 2 — Emergency Patient Queue (FIFO)
**Timestamp:** `4:15 - 5:15`  
**What to show:** Main Menu $\rightarrow$ Option `3` (Emergency Queue).  
**What to say:**
> *"Now let's examine Component 2: the Emergency Patient Queue.*  
> *Arriving emergency patients must be treated in the order they arrive, following the FIFO principle — First-In, First-Out.*  
> *When I choose option 3, the queue displays the waiting patients:*  
> *Patient 102 arrived first, followed by 105, then 101.*  
> *When a doctor becomes available, we invoke `dequeue` using option 2.*  
> *Notice that patient 102 is immediately dequeued from the front because he arrived first.*  
> *When we display the waiting queue again, only 2 patients remain, and position 1 is now patient 105.*  
> *If the queue becomes empty, the system safely handles it by displaying a friendly message instead of throwing an exception."*

---

### Section 6: Component 3 — Treatment History (Stack - LIFO)
**Timestamp:** `5:15 - 6:00`  
**What to show:** Main Menu $\rightarrow$ Option `4` (Treatment History).  
**What to say:**
> *"Next is Component 3: Treatment History managed using a custom Stack.*  
> *When treatments are completed, records are stored following the LIFO principle — Last-In, First-Out.*  
> *This ensures doctors can immediately review the most recently treated patients at the top of the stack.*  
> *Choosing option 3 displays the treatment history in reverse-chronological order, with patient 106 at the top.*  
> *If we perform a `pop` operation using option 2, the most recent record (ID 106) is removed from the top.*  
> *Viewing the stack again confirms that patient 103 is now at the top.*  
> *Empty stack conditions are also gracefully validated."*

---

### Section 7: Component 5 — Integrated Emergency Simulation
**Timestamp:** `6:00 - 7:00`  
**What to show:** Main Menu $\rightarrow$ Option `5` (Simulation).  
**What to say:**
> *"To showcase how these four data structures work together in a real emergency hospital, I built option 5: the Emergency Simulation Workflow.*  
> *When I trigger this option:*  
> *1. It automatically dequeues the next waiting patient from the Emergency Queue (FIFO).*  
> *2. The attending doctor enters their diagnosis and treatment.*  
> *3. The completed treatment is automatically pushed onto the Treatment History Stack (LIFO).*  
> *4. Simultaneously, the visit record is permanently appended to the patient's Singly Linked List visit history.*  
> *This demonstrates a seamless, end-to-end integration of all required data structures."*

---

### Section 8: Verification & Reflection
**Timestamp:** `7:00 - 8:00`  
**What to show:** Run `java -ea -cp src HospitalSystemTest`.  
**What to say:**
> *"To verify code correctness, I wrote a standalone test suite with assertions: `HospitalSystemTest`.*  
> *When executed with `java -ea -cp src HospitalSystemTest`, all automated unit tests for BST, Queue, Stack, and Singly Linked List pass with 100% success.*  
>  
> *Reflecting on this assignment:*  
> *Implementing these structures manually reinforced the fundamental mechanics of node pointers, memory handling, and algorithmic complexity.*  
> *The most challenging aspect was implementing 3-case BST deletion, specifically finding the in-order successor and updating subtree pointers cleanly.*  
> *This project gave me practical, hands-on experience in how foundational data structures solve critical real-world healthcare scheduling problems.*  
>  
> *Thank you very much for watching my demonstration!"*

---

## Quick Input Cheat Sheet for Your Live Demo

Keep this cheat sheet next to you during your recording:

| Screen | Menu Keys to Press | What You're Showing |
| :--- | :--- | :--- |
| **Main Menu** | `1` | Open BST Menu |
| **BST Traversal** | `4` | Shows sorted patient IDs |
| **BST Search** | `2` $\rightarrow$ `102` | Shows patient found |
| **BST Insert** | `1` $\rightarrow$ `109` $\rightarrow$ `Ruwan Fernando` $\rightarrow$ `30` $\rightarrow$ `0711122334` $\rightarrow$ `Severe Migraine` | Shows insert success |
| **BST Delete** | `3` $\rightarrow$ `104` | Shows deletion of ID 104 |
| **BST Traversal** | `4` $\rightarrow$ `5` | Verify updated sorted tree, exit to main |
| **Linked List** | `2` $\rightarrow$ `102` $\rightarrow$ `4` | Display previous visits for patient 102 |
| **Add Visit** | `1` $\rightarrow$ `5` $\rightarrow$ `2026-04-01` $\rightarrow$ `Dr. Alwis` $\rightarrow$ `Acute Asthma` $\rightarrow$ `Inhaler treatment` | Adds new visit node |
| **Search Visit** | `2` $\rightarrow$ `5` | Shows visit found |
| **Remove Visit**| `3` $\rightarrow$ `1` $\rightarrow$ `4` $\rightarrow$ `5` | Removes visit 1, display remaining, back to main |
| **Emergency Queue** | `3` $\rightarrow$ `3` | Display waiting queue (FIFO) |
| **Queue Dequeue**| `2` $\rightarrow$ `3` $\rightarrow$ `4` | Dequeue next patient, show updated queue, back to main |
| **Treatment Stack**| `4` $\rightarrow$ `3` | Display completed treatments (LIFO) |
| **Stack Pop** | `2` $\rightarrow$ `3` $\rightarrow$ `4` | Pop latest treatment, show updated stack, back to main |
| **Simulation** | `5` $\rightarrow$ `Dr. Jayasinghe` $\rightarrow$ `Acute Bronchial Spasm` $\rightarrow$ `Nebulization` $\rightarrow$ `2026-10-13` | Full integrated workflow |
| **Exit** | `6` | Exit system |
| **Automated Tests** | `java -ea -cp src HospitalSystemTest` | Shows ALL TESTS PASSED [OK] |
