import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class ContactNotFoundException extends Exception {
    public ContactNotFoundException(String message) {
        super(message);
    }
}

class DuplicateContactException extends Exception {
    public DuplicateContactException(String message) {
        super(message);
    }
}

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return name + " | " + phone + " | " + email;
    }
}

class ContactBook {
    private Map<String, Contact> contacts = new HashMap<>();

    public void addContact(String name, String phone, String email)
            throws InvalidInputException, DuplicateContactException {
        if (name == null || name.isEmpty()) {
            throw new InvalidInputException("Name cannot be empty.");
        }
        if (!phone.matches("{10}")) {
            throw new InvalidInputException("Phone must be exactly 10 digits.");
        }
        if (contacts.containsKey(phone)) {
            throw new DuplicateContactException("This phone number already exists.");
        }
        contacts.put(phone, new Contact(name, phone, email));
    }

    public Contact searchByPhone(String phone) throws ContactNotFoundException {
        Contact c = contacts.get(phone);
        if (c == null) {
            throw new ContactNotFoundException("No contact found with phone " + phone);
        }
        return c;
    }

    public List<Contact> searchByName(String keyword) {
        List<Contact> result = new ArrayList<>();
        for (Contact c : contacts.values()) {
            if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }

    public void updateContact(String phone, String newName, String newEmail)
            throws ContactNotFoundException {
        Contact c = searchByPhone(phone);
        if (!newName.isEmpty()) {
            c.setName(newName);
        }
        if (!newEmail.isEmpty()) {
            c.setEmail(newEmail);
        }
    }

    public void deleteContact(String phone) throws ContactNotFoundException {
        if (contacts.remove(phone) == null) {
            throw new ContactNotFoundException("No contact found with phone " + phone);
        }
    }

    public List<Contact> getAllSorted() {
        List<Contact> list = new ArrayList<>(contacts.values());
        list.sort(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER));
        return list;
    }
}

public class ContactBookApp {
    public static void main(String[] args) {
        ContactBook book = new ContactBook();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== CONTACT BOOK =====");
            System.out.println("1. Add contact");
            System.out.println("2. Search by phone");
            System.out.println("3. Search by name");
            System.out.println("4. Update contact");
            System.out.println("5. Delete contact");
            System.out.println("6. Show all contacts (A-Z)");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 1 to 7.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Phone (10 digits): ");
                        String phone = sc.nextLine();

                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        book.addContact(name, phone, email);
                        System.out.println("Contact added.");
                        break;
                    case 2:
                        System.out.print("Enter phone: ");
                        System.out.println(book.searchByPhone(sc.nextLine()));
                        break;
                    case 3:
                        System.out.print("Enter name or part of name: ");
                        List<Contact> found = book.searchByName(sc.nextLine());
                        if (found.isEmpty()) {
                            System.out.println("No matching contacts.");
                        } else {
                            for (Contact c : found) {
                                System.out.println(c);
                            }
                        }
                        break;
                    case 4:
                        System.out.print("Phone of contact to update: ");
                        String p = sc.nextLine();

                        System.out.print("New name (press Enter to skip): ");
                        String n = sc.nextLine();

                        System.out.print("New email (press Enter to skip): ");
                        String e = sc.nextLine();

                        book.updateContact(p, n, e);
                        System.out.println("Contact updated.");
                        break;
                        
                    case 5:
                        System.out.print("Phone of contact to delete: ");
                        book.deleteContact(sc.nextLine());
                        System.out.println("Contact deleted.");
                        break;
                    case 6:
                        List<Contact> all = book.getAllSorted();
                        if (all.isEmpty()) {
                            System.out.println("Contact book is empty.");
                        } else {
                            for (Contact c : all) {
                                System.out.println(c);
                            }
                        }
                        break;
                    case 7:
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try 1 to 7.");
                }
            } catch (InvalidInputException | DuplicateContactException | ContactNotFoundException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        sc.close();
    }
}
