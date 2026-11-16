package lms;
import lms.model.Book;
import lms.model.Member;
import lms.service.Library;

public class Main
{
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book(
                "Clean Code",
                "Robert C. Martin",
                "978-0132350884"
        );

        Member member1 = new Member(
                "M001",
                "Nadeesha Perera"
        );

        library.addBook(book1);
        library.addMember(member1);

        System.out.println("=== Initial Details ===");
        library.listBooks();
        library.listMembers();

        // Manually update the book status and member count.
        book1.setBorrowed(true);
        member1.incrementBorrowedCount();

        System.out.println("\n=== After Borrowing ===");
        library.listBooks();
        library.listMembers();

        System.out.println("\n=== List Sizes ===");
        System.out.println("Total books: " + library.getBooks().size());
        System.out.println("Total members: " + library.getMembers().size());
    }
}