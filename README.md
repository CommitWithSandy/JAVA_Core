# Contact Book Application

A console-based Contact Management System implemented in Core Java demonstrating Object-Oriented Programming (OOP) principles, custom exception handling, and the Java Collections Framework.

---

## 📌 Features

- **Add Contact**: Add a contact with name, 10-digit phone number, and email. Validates non-empty names, 10-digit phone format, and prevents duplicate phone entries.
- **Search by Phone**: Instantly look up a specific contact using their phone number with $O(1)$ lookup time.
- **Search by Name**: Case-insensitive partial matching search across all contacts.
- **Update Contact**: Update an existing contact's name or email while retaining their phone number.
- **Delete Contact**: Remove a contact by phone number.
- **View All Sorted (A–Z)**: Display all stored contacts sorted alphabetically by name.

---

## 🛠️ Java Concepts Demonstrated

| Concept | Implementation Details |
| :--- | :--- |
| **Encapsulation** | `Contact` model uses private fields (`name`, `phone`, `email`) accessible only via getters and setters. |
| **Custom Exceptions** | Domain-specific checked exceptions: `ContactNotFoundException`, `DuplicateContactException`, and `InvalidInputException`. |
| **Collections Framework** | `HashMap<String, Contact>` for fast phone-based lookups and `ArrayList<Contact>` with `Comparator` for sorting. |
| **Defensive Input Handling** | Regular expression validation (`\\d{10}`) and numeric parsing with safe recovery. |

---

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK 8 or higher) installed.

### Compilation & Execution

1. **Clone the repository:**
   ```bash
   git clone https://github.com/CommitWithSandy/JAVA_Core.git
   cd JAVA_Core
   ```

2. **Compile the source code:**
   ```bash
   javac ContactBookApp.java
   ```

3. **Run the application:**
   ```bash
   java ContactBookApp
   ```

---

## 📖 Menu Options

```text
===== CONTACT BOOK =====
1. Add contact
2. Search by phone
3. Search by name
4. Update contact
5. Delete contact
6. Show all contacts (A-Z)
7. Exit
```

---

## 📂 Project Structure

```text
.
├── .gitignore
├── ContactBookApp.java
└── README.md
```
