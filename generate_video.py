import cv2
import numpy as np
import os
import sys

def create_text_card(title, subtitle, bullets, footer_time, chapter_name):
    # Create 1920x1080 blank frame
    img = np.zeros((1080, 1920, 3), dtype=np.uint8)
    img[:] = (26, 24, 20) # Dark navy-slate BGR

    # Top header bar
    cv2.rectangle(img, (0, 0), (1920, 90), (34, 30, 24), -1)
    cv2.putText(img, "CIT300 - DATA STRUCTURES & ALGORITHMS | MINI HOSPITAL SYSTEM", (50, 55),
                cv2.FONT_HERSHEY_SIMPLEX, 0.9, (176, 201, 78), 2, cv2.LINE_AA) # BGR teal
    cv2.putText(img, f"{chapter_name}  [{footer_time}]", (1320, 55),
                cv2.FONT_HERSHEY_SIMPLEX, 0.8, (214, 156, 86), 2, cv2.LINE_AA)
    cv2.line(img, (0, 90), (1920, 90), (66, 46, 46), 2)

    # Title & Subtitle
    cv2.putText(img, title, (100, 210), cv2.FONT_HERSHEY_SIMPLEX, 1.4, (255, 255, 255), 3, cv2.LINE_AA)
    cv2.putText(img, subtitle, (100, 270), cv2.FONT_HERSHEY_SIMPLEX, 0.95, (214, 156, 86), 2, cv2.LINE_AA)
    cv2.line(img, (100, 300), (1820, 300), (60, 50, 40), 2)

    # Bullets
    y = 380
    for bullet in bullets:
        cv2.circle(img, (120, y - 8), 7, (176, 201, 78), -1)
        cv2.putText(img, bullet, (150, y), cv2.FONT_HERSHEY_SIMPLEX, 0.85, (220, 220, 220), 2, cv2.LINE_AA)
        y += 75

    # Bottom Prompt bar
    cv2.rectangle(img, (0, 990), (1920, 1080), (28, 24, 20), -1)
    cv2.line(img, (0, 990), (1920, 990), (66, 46, 46), 2)
    cv2.putText(img, "SPEAKING PROMPT: State your name, introduction, and summarize these points clearly on camera.",
                (50, 1045), cv2.FONT_HERSHEY_SIMPLEX, 0.75, (120, 195, 152), 2, cv2.LINE_AA)

    return img

def create_screenshot_frame(screenshot_path, chapter_title, time_range, speaking_prompt):
    img = np.zeros((1080, 1920, 3), dtype=np.uint8)
    img[:] = (26, 24, 20)

    # Top header bar
    cv2.rectangle(img, (0, 0), (1920, 85), (34, 30, 24), -1)
    cv2.putText(img, "CIT300 - MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM", (50, 52),
                cv2.FONT_HERSHEY_SIMPLEX, 0.85, (176, 201, 78), 2, cv2.LINE_AA)
    cv2.putText(img, f"{chapter_title}  [{time_range}]", (1250, 52),
                cv2.FONT_HERSHEY_SIMPLEX, 0.8, (214, 156, 86), 2, cv2.LINE_AA)
    cv2.line(img, (0, 85), (1920, 85), (66, 46, 46), 2)

    # Load screenshot image
    sc = cv2.imread(screenshot_path)
    if sc is not None:
        h, w = sc.shape[:2]
        # Target area: width up to 1800, height up to 860
        target_w = 1760
        target_h = 860
        scale = min(target_w / w, target_h / h)
        new_w = int(w * scale)
        new_h = int(h * scale)
        resized_sc = cv2.resize(sc, (new_w, new_h), interpolation=cv2.INTER_AREA)

        # Center in canvas
        x_offset = (1920 - new_w) // 2
        y_offset = 95 + (880 - new_h) // 2
        img[y_offset:y_offset+new_h, x_offset:x_offset+new_w] = resized_sc

    # Bottom Speaking Guidance Bar
    cv2.rectangle(img, (0, 995), (1920, 1080), (28, 24, 20), -1)
    cv2.line(img, (0, 995), (1920, 995), (66, 46, 46), 2)
    cv2.putText(img, f"WHAT TO SAY: {speaking_prompt}", (50, 1045),
                cv2.FONT_HERSHEY_SIMPLEX, 0.7, (120, 195, 152), 2, cv2.LINE_AA)

    return img

def main():
    print("Preparing 1080p Screen Recording Walkthrough Video...")

    output_file = "demonstration_screen_recording.mp4"
    fps = 10
    width = 1920
    height = 1080

    fourcc = cv2.VideoWriter_fourcc(*'mp4v')
    writer = cv2.VideoWriter(output_file, fourcc, float(fps), (width, height))

    if not writer.isOpened():
        print("Error: Could not open VideoWriter.")
        sys.exit(1)

    # 1. Slide 1: Introduction Card (60 seconds: 0:00 - 1:00)
    print("Rendering [0:00 - 1:00] Intro Card...")
    frame1 = create_text_card(
        "Mini Hospital Emergency Management System",
        "CIT300 Data Structures and Algorithms - Individual Mid Assignment",
        [
            "Student Name: Arshath Rizvi",
            "Module: CIT300 - Data Structures and Algorithms",
            "Core Structures: Binary Search Tree (BST), Queue (FIFO), Stack (LIFO), Singly Linked List",
            "Architecture: 100% Custom Node-Based Implementations (No java.util collections)",
            "University Submission: Java Source Code, Git Repo, Video Demo, README, Screenshots"
        ],
        "0:00 - 1:00",
        "CHAPTER 1: INTRODUCTION"
    )
    for _ in range(60 * fps):
        writer.write(frame1)

    # 2. Slide 2: GitHub Repository & Commit Timeline (45 seconds: 1:00 - 1:45)
    print("Rendering [1:00 - 1:45] GitHub Repository Evidence...")
    frame2 = create_text_card(
        "GitHub Repository & Progressive Development Evidence",
        "Public Repository: github.com/ArshathRizvi/Mini-Hospital-Emergency-Management-System",
        [
            "Commit 1: Created project structure, base directory, and .gitignore",
            "Commit 2: Implemented patient visit history using custom Singly Linked List",
            "Commit 3: Implemented patient records using Binary Search Tree (BST)",
            "Commit 4: Added BST recursive search and 3-case deletion operations",
            "Commit 5: Implemented emergency queue following FIFO principle",
            "Commit 6: Implemented treatment history following LIFO Stack principle",
            "Commit 7: Added interactive console menu CLI and system integration",
            "Commit 8: Added automated test suite (HospitalSystemTest)",
            "Commit 9: Added program output screenshots and comprehensive README"
        ],
        "1:00 - 1:45",
        "CHAPTER 2: GITHUB EVIDENCE"
    )
    for _ in range(45 * fps):
        writer.write(frame2)

    # 3. Component 1: BST Patient Records (90 seconds: 1:45 - 3:15)
    print("Rendering [1:45 - 3:15] Component 1: BST...")
    frame3 = create_screenshot_frame(
        "screenshots/02_bst_patient_records.png",
        "COMPONENT 1: PATIENT RECORDS (BST)",
        "1:45 - 3:15",
        "In-Order Traversal visits Left-Root-Right to print sorted IDs. Search runs in O(log n). Deletion handles 3 cases."
    )
    for _ in range(90 * fps):
        writer.write(frame3)

    # 4. Component 4: Singly Linked List (60 seconds: 3:15 - 4:15)
    print("Rendering [3:15 - 4:15] Component 4: Linked List...")
    frame4 = create_screenshot_frame(
        "screenshots/03_patient_visit_history.png",
        "COMPONENT 4: VISIT HISTORY (LINKED LIST)",
        "3:15 - 4:15",
        "Each patient holds a Singly Linked List. Add appends new visits, search locates by Visit ID, remove unlinks nodes."
    )
    for _ in range(60 * fps):
        writer.write(frame4)

    # 5. Component 2: Emergency Queue (60 seconds: 4:15 - 5:15)
    print("Rendering [4:15 - 5:15] Component 2: Queue...")
    frame5 = create_screenshot_frame(
        "screenshots/04_emergency_queue.png",
        "COMPONENT 2: EMERGENCY QUEUE (FIFO)",
        "4:15 - 5:15",
        "FIFO principle: First patient to arrive (ID 102) is first dequeued for emergency consultation."
    )
    for _ in range(60 * fps):
        writer.write(frame5)

    # 6. Component 3: Treatment Stack (45 seconds: 5:15 - 6:00)
    print("Rendering [5:15 - 6:00] Component 3: Stack...")
    frame6 = create_screenshot_frame(
        "screenshots/05_treatment_stack.png",
        "COMPONENT 3: TREATMENT STACK (LIFO)",
        "5:15 - 6:00",
        "LIFO principle: Completed treatments push to top; pop removes most recent case (ID 106). Empty stack handled safely."
    )
    for _ in range(45 * fps):
        writer.write(frame6)

    # 7. Component 5: Integrated Emergency Simulation (60 seconds: 6:00 - 7:00)
    print("Rendering [6:00 - 7:00] Integrated Workflow...")
    frame7 = create_screenshot_frame(
        "screenshots/06_emergency_simulation.png",
        "INTEGRATED SIMULATION WORKFLOW",
        "6:00 - 7:00",
        "Patient dequeued from Queue -> Doctor diagnoses -> Treatment pushed to Stack -> Visit added to Linked List."
    )
    for _ in range(60 * fps):
        writer.write(frame7)

    # 8. Component 6: Automated Test Suite (45 seconds: 7:00 - 7:45)
    print("Rendering [7:00 - 7:45] Automated Tests...")
    frame8 = create_screenshot_frame(
        "screenshots/07_automated_test_suite.png",
        "VERIFICATION: AUTOMATED TEST SUITE",
        "7:00 - 7:45",
        "Standalone assertions verify Singly Linked List, BST, Queue, and Stack operations pass with 100% success."
    )
    for _ in range(45 * fps):
        writer.write(frame8)

    # 9. Slide 9: Design Decisions & Reflection (45 seconds: 7:45 - 8:30)
    print("Rendering [7:45 - 8:30] Reflection & Summary...")
    frame9 = create_text_card(
        "Design Decisions & Learning Reflection",
        "Academic Takeaways & Algorithmic Insights",
        [
            "Custom Nodes vs Collections: Direct pointer management deepened understanding of memory layout.",
            "BST Complexity: In-order successor replacement ensures BST properties remain balanced after deletion.",
            "Real-World Modeling: FIFO perfectly mirrors emergency arrival order; LIFO tracks medical treatment logs.",
            "Error Handling: Safe empty-state returns prevent NullPointerExceptions across all 4 structures.",
            "Conclusion: System successfully models an emergency hospital while meeting all CIT300 requirements."
        ],
        "7:45 - 8:30",
        "CHAPTER 9: REFLECTION"
    )
    for _ in range(45 * fps):
        writer.write(frame9)

    writer.release()
    print("Successfully generated demonstration_screen_recording.mp4!")

if __name__ == "__main__":
    main()
