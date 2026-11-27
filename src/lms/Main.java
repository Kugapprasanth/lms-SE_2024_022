
package lms;

import lms.exception.ItemNotAvailableException;
import lms.exception.MemberNotFoundException;

import lms.model.Book;
import lms.model.DVD;
import lms.model.Magazine;
import lms.model.Staff;
import lms.model.Student;

import lms.service.Library;

public class Main {


    private static void attempt(
            Library library,
            String itemId,
            String memberId) {

        try {

            library.borrowItem(itemId, memberId);

        } catch (ItemNotAvailableException e) {

            System.out.println(
                    "Cannot borrow: " + e.getMessage());

        } catch (MemberNotFoundException e) {

            System.out.println(
                    "Unknown member: " + e.getMemberId());

        } finally {

            System.out.println(
                    "    (attempt finished: item " + itemId + ", member " + memberId + ")");
        }
    }

    public static void main(String[] args) {

        Library library = new Library();

        Book b1 = new Book(
                "B001",
                "Clean Code",
                "Robert C. Martin");

        Book b2 = new Book(
                "B002",
                "Effective Java",
                "Joshua Bloch");

        Book b3 = new Book(
                "B003",
                "Java Basics",
                "Someone");

        Book b4 = new Book(
                "B004",
                "Head First Java",
                "Kathy Sierra");

        DVD dvd = new DVD(
                "D001",
                "Introduction to Algorithms",
                120);

        Magazine magazine = new Magazine(
                "MG001",
                "Software Weekly",
                12);


        library.addItem(b1);
        library.addItem(b2);
        library.addItem(b3);
        library.addItem(b4);
        library.addItem(dvd);
        library.addItem(magazine);


        Student student = new Student(
                "M001",
                "Nadeesha Perera");

        Staff staff = new Staff(
                "M002",
                "Mr. Kasun Silva");


        library.addMember(student);
        library.addMember(staff);


        System.out.println(
                "--- 1. Successful borrow ---");

        attempt(library, "B001", "M001");


        System.out.println(
                "--- 2. Already on loan (checked exception) ---");

        attempt(library, "B001", "M002");


        System.out.println(
                "--- 3. Unknown member (unchecked exception) ---");

        attempt(library, "D001", "M999");


        System.out.println(
                "--- 4. Unknown item ---");

        attempt(library, "X404", "M001");


        System.out.println(
                "--- 5. Returning something not on loan ---");

        try {

            library.returnItem("D001");

        } catch (ItemNotAvailableException e) {

            System.out.println(
                    "Cannot return: " + e.getMessage());
        }


        System.out.println(
                "--- 6. Bad data (IllegalArgumentException) ---");

        try {

            new Book("B099", " ", "Someone");

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid book: " + e.getMessage());
        }


        System.out.println(
                "--- 7. Student reaches borrowing limit of 3 ---");

        attempt(library, "B002", "M001");

        attempt(library, "B003", "M001");

        attempt(library, "B004", "M001");


        System.out.println(
                "--- 8. Return frees a slot ---");

        try {

            library.returnItem("B001");

        } catch (ItemNotAvailableException e) {

            System.out.println(
                    "Cannot return: " + e.getMessage());
        }


        attempt(library, "B004", "M001");
    }
}
