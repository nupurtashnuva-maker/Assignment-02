# Data Structures Assignment 2

**Assigned structures:** Category A — Singly Linked List; Category B — Binary Tree  
**Language:** Java  
**External libraries:** None required

This project follows the two structures named in the assignment picture. The tree is a **general binary tree**, not a Binary Search Tree (BST).

## What is included

- `SinglyLinkedList.java` — a custom integer linked list using nodes and `next` references, with add, insert, remove, access, update, search, reverse, and clear operations.
- `BinaryTree.java` — a custom general binary tree with level-order insertion, search, removal, four traversal methods, height, size, and clear operations. It does **not** arrange nodes by numeric comparison.
- `Main.java` — an interactive console menu and a demonstration mode.
- `StructureTests.java` — automated, dependency-free checks for normal cases, boundaries, duplicate values, tree traversals, and removal behavior.
- `PROJECT_REPORT.md` — design, operation explanations, algorithms, and time complexity.
- `TEST_RESULTS.txt` and `DEMO_OUTPUT.txt` — output captured from the executed test suite and demo.

## How the binary tree works

The tree uses **level-order insertion**: each new value is placed in the first available child position, scanning top to bottom and left to right. This keeps the shape compact and does not use a BST sorting rule. Duplicate values are allowed. Search finds a matching value by scanning level-order. Removal deletes the first matching value by replacing its value with the deepest, rightmost node's value and then detaching that deepest node.

## Requirements

Install a JDK (Java 8 or later). The program does not require Maven, JavaFX, a database, or third-party libraries.

## Run on Windows

1. Extract the ZIP.
2. Open the extracted `Assignment2_DataStructures` folder.
3. Double-click `run.bat`, or open Command Prompt in this folder and run:

```bat
run.bat
```

Run the automated tests:

```bat
run.bat test
```

Run the demonstration without opening the menu:

```bat
run.bat demo
```

## Run on macOS / Linux

From this folder:

```bash
chmod +x run.sh
./run.sh
```

Run tests:

```bash
./run.sh test
```

Run the demonstration:

```bash
./run.sh demo
```

## Demonstration

The linked-list demo covers insertion, access, searching, removal, reversal, and size. The binary-tree demo inserts `10, 20, 30, 40, 50, 60, 70` using level-order placement, displays in-order, pre-order, post-order, and level-order traversals, searches for a value, reports the height, and removes a value.

## Testing

The test command compiles the source using `javac` and runs `assignment2.StructureTests`. It exits with status `1` if any check fails. The tests cover linked-list pointer/tail updates, invalid indices, reversal, clearing/reuse, and general-binary-tree insertion, duplicate values, traversal orders, searching, height, leaf/internal/root removal, and clearing.
