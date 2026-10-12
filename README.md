# Data Structure & Graph Performance Analyzer

[![Java Version](https://img.shields.io/badge/Java-8%20%7C%2011%20%7C%2017%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Course](https://img.shields.io/badge/Course-CIT300%20Assignment%202-0052CC?style=for-the-badge)](README.md)
[![Architecture](https://img.shields.io/badge/Architecture-Modular%20OOP-2ea44f?style=for-the-badge)](README.md)
[![License](https://img.shields.io/badge/License-Academic-lightgrey?style=for-the-badge)](README.md)

An interactive, high-precision Java console suite designed to simulate, benchmark, and evaluate fundamental data structures, search algorithms, and graph traversal methods. The tool pairs theoretical asymptotic analysis ($O$-notation) with empirical nanosecond runtime benchmarks and operation-step telemetry via an integrated measurement engine.

---

## Table of Contents

- [Overview](#overview)
- [System Architecture](#system-architecture)
- [Key Features & Modules](#key-features--modules)
  - [1. Array Operations](#1-array-operations)
  - [2. Stack Operations (LIFO)](#2-stack-operations-lifo)
  - [3. Circular Queue Operations (FIFO)](#3-circular-queue-operations-fifo)
  - [4. Singly Linked List](#4-singly-linked-list)
  - [5. Searching Algorithms](#5-searching-algorithms)
  - [6. Graph Traversal (BFS & DFS)](#6-graph-traversal-bfs--dfs)
  - [7. Empirical Performance Tracker](#7-empirical-performance-tracker)
- [Complexity Analysis Reference](#complexity-analysis-reference)
- [Project Directory Structure](#project-directory-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Compilation](#compilation)
  - [Execution](#execution)
- [CLI Navigation & Menu Map](#cli-navigation--menu-map)
- [Team & Branch Workflow](#team--branch-workflow)

---

## Overview

The **Data Structure & Graph Performance Analyzer** was developed as part of **CIT300 (Assignment 2)** to provide an empirical testing platform for classical computer science algorithms.

Instead of analyzing algorithmic complexity solely from textbook definitions, this application captures runtime metrics directly from memory operations during runtime:
* **High-Resolution Clocking**: Measures elapsed wall-clock execution time in nanoseconds (`System.nanoTime()`).
* **Step & Shift Counting**: Quantifies inner-loop comparisons, array index shifts, and node traversals.
* **Invariant & Bounds Checking**: Enforces defensive boundaries (underflow, overflow, null verification, and sorted order constraints).

---

## System Architecture

The application adopts a modular object-oriented design where each data structure is encapsulated in its own component and coupled with the `PerformanceTracker` service.

```
                              +-----------------------+
                              |      Main (CLI)       |
                              +-----------+-----------+
                                          |
                +-------------------------+-------------------------+
                |                         |                         |
       +--------v--------+       +--------v--------+       +--------v--------+
       | ArrayOperations |       |    IntStack     |       |  CircularQueue  |
       +--------+--------+       +--------+--------+       +--------+--------+
                |                         |                         |
       +--------v--------+       +--------v--------+       +--------v--------+
       | LinkedList      |       | SearchAlgorithms|       |      Graph      |
       +--------+--------+       +--------+--------+       +--------+--------+
                |                         |                         |
                +-------------------------+-------------------------+
                                          |
                              +-----------v-----------+
                              |  PerformanceTracker   |
                              |  (Nanosecond Engine)  |
                              +-----------------------+
```

---

## Key Features & Modules

### 1. Array Operations
* **File**: `ArrayOperations.java`
* **Storage**: Contiguous linear storage backed by Java collection abstractions.
* **Capabilities**:
  * `insert(index, value)`: Arbitrary-index insertion with automatic elements-shifted tracking.
  * `delete(value)`: Searches for target value and shifts downstream elements leftward.
  * `search(value)`: Sequential search returning the target index and comparison count.
  * `display()`: Serializes and prints the active array elements.

### 2. Stack Operations (LIFO)
* **File**: `IntStack.java`
* **Mechanism**: Array-backed integer stack following the **Last-In, First-Out (LIFO)** policy with configurable capacity (default: 10).
* **Capabilities**:
  * `push(value)`: Validates stack boundary; appends element to top ($O(1)$).
  * `pop()`: Returns and removes top element with underflow guard ($O(1)$).
  * `peek()`: Inspects the current top element without mutation ($O(1)$).
  * `display()`: Inspects stack elements ordered from base to top.

### 3. Circular Queue Operations (FIFO)
* **File**: `CircularQueue.java`
* **Mechanism**: Fixed-size circular buffer using modulo indexing `(index + 1) % capacity` to achieve space-efficient **First-In, First-Out (FIFO)** processing.
* **Capabilities**:
  * `enqueue(value)`: Inserts element at rear pointer.
  * `dequeue()`: Extracts element from front pointer and increments head index cyclically.
  * `peek()` / `front()`: Non-destructive inspection of the current head.
  * Circular pointer mathematics prevent memory starvation and avoid array element shifting.

### 4. Singly Linked List
* **File**: `LinkedListStructure.java`
* **Mechanism**: Dynamic node-pointer chaining (`Node { int data; Node next; }`).
* **Capabilities**:
  * `insert(value)`: Appends nodes dynamically at the tail without requiring pre-allocated capacities.
  * `delete(value)`: Pointer adjustments to unlink target nodes across head, interior, or tail positions.
  * `search(value)`: Traverses node references sequentially from head.
  * `runAllTests()`: Built-in automated regression test harness testing 10 edge cases (empty lists, head removal, middle removal, and negative searches).

### 5. Searching Algorithms
* **File**: `SearchAlgorithms.java`
* **Linear Search**: Sequential element-by-element evaluation ($O(n)$) compatible with unsorted arrays.
* **Binary Search**: Divide-and-conquer search ($O(\log n)$) on sorted data using middle index halving (`low + (high - low) / 2`). Includes input sort-validation guards.
* **Comparative Analyzer**: Side-by-side benchmarking of both algorithms on identical datasets to observe empirical efficiency divergence.

### 6. Graph Traversal (BFS & DFS)
* **File**: `Graph.java`
* **Representation**: Adjacency list backed by `LinkedHashMap<String, Set<String>>` to maintain predictable insertion order without duplicates.
* **Capabilities**:
  * `addVertex(name)`: Adds normalized, unique vertex labels.
  * `addEdge(v1, v2)`: Constructs bidirectional (undirected) connections.
  * `breadthFirstTraversal(start)`: Queue-driven iterative level-order traversal (BFS).
  * `depthFirstTraversal(start)`: Recursive backtracking traversal (DFS).

### 7. Empirical Performance Tracker
* **File**: `PerformanceTracker.java`
* **Mechanism**: Telemetry collector that logs detailed audit trails across all data structure operations.
* **Logged Parameters**:
  * **Operation Identifier**: e.g., `Array INSERT`, `Binary Search`, `BFS`.
  * **Input Size ($n$)**: Number of active items in the target data structure.
  * **Steps / Operations**: Comparisons made, elements shifted, or edges visited.
  * **Elapsed Time**: Wall-clock time recorded in nanoseconds ($10^{-9}\text{ s}$).
  * **Diagnostic Details**: Contextual output strings detailing the operation result.

---

## Complexity Analysis Reference

| Data Structure / Algorithm | Operation | Best Case | Average Case | Worst Case | Space Complexity | Measured Metric |
| :--- | :--- | :---: | :---: | :---: | :---: | :--- |
| **Array** | Insert (Arbitrary) | $O(1)$ | $O(n)$ | $O(n)$ | $O(1)$ | Elements shifted + nanos |
| **Array** | Delete (By Value) | $O(1)$ | $O(n)$ | $O(n)$ | $O(1)$ | Comparisons + shifts + nanos |
| **Array** | Search (Linear) | $O(1)$ | $O(n)$ | $O(n)$ | $O(1)$ | Comparisons + nanos |
| **Stack** | Push / Pop / Peek | $O(1)$ | $O(1)$ | $O(1)$ | $O(n)$ | Direct index operations |
| **Circular Queue** | Enqueue / Dequeue | $O(1)$ | $O(1)$ | $O(1)$ | $O(n)$ | Modulo index increments |
| **Linked List** | Insert (Tail) | $O(1)$ | $O(n)$ | $O(n)$ | $O(1)$ | Traversal steps + nanos |
| **Linked List** | Delete (Value) | $O(1)$ | $O(n)$ | $O(n)$ | $O(1)$ | Traversal steps + nanos |
| **Linear Search** | Value Search | $O(1)$ | $O(n)$ | $O(n)$ | $O(1)$ | Iterations / comparisons |
| **Binary Search** | Value Search | $O(1)$ | $O(\log n)$ | $O(\log n)$ | $O(1)$ | Bisection comparisons |
| **Graph (BFS)** | Traversal | $O(V + E)$ | $O(V + E)$ | $O(V + E)$ | $O(V)$ | Visited nodes + edge visits |
| **Graph (DFS)** | Traversal | $O(V + E)$ | $O(V + E)$ | $O(V + E)$ | $O(V)$ | Recursive call stack steps |

---

## Project Directory Structure

```text
Data-Structure-Graph-Performance-Analyzer/
├── .gitignore
├── README.md
└── src/
    └── com/
        └── cit300/
            └── analyzer/
                ├── ArrayOperations.java       # Dynamic array manipulation & metrics
                ├── CircularQueue.java         # Modulo-based circular FIFO queue
                ├── Graph.java                 # Adjacency list graph with BFS & DFS
                ├── IntStack.java              # LIFO array-backed stack
                ├── LinkedListStructure.java   # Singly linked list with node chaining
                ├── Main.java                  # Interactive CLI application & routing
                ├── PerformanceTracker.java    # Telemetry logging & benchmark suite
                └── SearchAlgorithms.java      # Linear and binary search routines
```

---

## Getting Started

### Prerequisites
* **Java Development Kit (JDK)**: Version 8 or higher (JDK 11, 17, or 21 recommended).
* Compatible terminal (PowerShell, Command Prompt, bash, or zsh).

Verify your Java installation:
```bash
javac -version
java -version
```

### Compilation

Compile all Java source files into a target output directory (e.g., `bin` or `out`):

```bash
# Windows (PowerShell)
javac -d bin (Get-ChildItem -Path src -Filter "*.java" -Recurse | Select-Object -ExpandProperty FullName)

# macOS / Linux (bash)
javac -d bin $(find src -name "*.java")
```

### Execution

Launch the interactive console application:

```bash
java -cp bin com.cit300.analyzer.Main
```

To run the standalone unit verification harness for the Singly Linked List:
```bash
java -cp bin com.cit300.analyzer.LinkedListStructure
```

---

## CLI Navigation & Menu Map

The command-line interface provides an intuitive menu hierarchy:

```text
================================================================
            DATA STRUCTURE & GRAPH ANALYZER
================================================================
  1. Array Operations          --> Insert, Delete, Search, Display
  2. Stack Operations          --> Push, Pop, Peek, Display (LIFO)
  3. Queue Operations          --> Enqueue, Dequeue, Peek, Display (FIFO)
  4. Linked List Operations    --> Insert, Delete, Search, Display (Nodes)
  5. Searching Operations      --> Linear Search vs. Binary Search
  6. Graph Operations          --> Add Vertices/Edges, Adjacency List, BFS, DFS
  7. Performance Comparison    --> Comparative Search Efficiency Benchmarks
  8. Display All Results       --> View Session Execution Metrics & Nanosecond Logs
  9. Exit
================================================================
```

### Sample Session Output

#### Linear vs. Binary Search Benchmark (Option 7)
```text
PERFORMANCE COMPARISON
How many integer values? 7
Enter value 1: 4
Enter value 2: 8
Enter value 3: 15
Enter value 4: 16
Enter value 5: 23
Enter value 6: 42
Enter value 7: 99
Both searches will use this same sorted data: [4, 8, 15, 16, 23, 42, 99]
Enter the target value: 42

Linear search: found at index 5
Binary search: found at index 5
Linear search has O(n) time complexity; binary search has O(log n) time complexity on sorted data.
The tracker records the actual comparisons and elapsed time for each search.
```

#### Graph Traversals (Option 6)
```text
GRAPH OPERATIONS
Vertex added: A, B, C, D, E
Undirected edges added: (A-B), (A-C), (B-D), (C-D), (D-E)

Display Graph:
A -> [B, C]
B -> [A, D]
C -> [A, D]
D -> [B, C, E]
E -> [D]

BFS order (start at A): [A, B, C, D, E]
DFS order (start at A): [A, B, D, C, E]
```

#### Performance Tracker Audit (Option 8)
```text
Performance measurements:
1. Array INSERT  | input size: 0 | steps: 0  | elapsed: 12400 ns | details: inserted 45 at index 0; elements shifted: 0
2. Array SEARCH  | input size: 4 | steps: 2  | elapsed: 8700 ns  | details: value 90 found at index 1; comparisons: 2
3. Linear Search | input size: 7 | steps: 6  | elapsed: 19500 ns | details: target 42 found at index 5; comparisons: 6
4. Binary Search | input size: 7 | steps: 3  | elapsed: 6100 ns  | details: target 42 found at index 5; comparisons: 3
5. BFS           | input size: 5 | steps: 11 | elapsed: 32800 ns | details: vertices visited: 5, adjacency entries examined: 6
```

---

## Team & Branch Workflow

This repository was developed using a feature-branch integration workflow:

| Contributor / Branch | Module Responsibility | Key Components |
| :--- | :--- | :--- |
| **`azam-array-search`** | Linear array operations & Search algorithms | `ArrayOperations.java`, `SearchAlgorithms.java` |
| **`rimas-stack-queue`** | Stack (LIFO) & Circular Queue (FIFO) | `IntStack.java`, `CircularQueue.java` |
| **`chathurika-linked-list`** | Singly linked list & Dynamic chaining | `LinkedListStructure.java` |
| **`nishath-graph-performance`** | Graph representation, Traversals, & Metrics | `Graph.java`, `PerformanceTracker.java` |
| **`development`** | Integration branch & CLI system menu | `Main.java` orchestrator |
| **`main`** | Production release branch | Stable deployment baseline |

---

## License & Academic Honor Code

This project was created for academic evaluation in **CIT300**. Code contributions are maintained in compliance with academic integrity guidelines.
