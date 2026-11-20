
package lms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lms.model.Book;
import lms.model.DVD;
import lms.model.LibraryItem;
import lms.model.Loanable;
import lms.model.Member;
import lms.model.Reservable;

public class Library {

    private List<LibraryItem> items = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    // Week 3: Method Overloading

    public void addItem(LibraryItem item) {
        items.add(item);
        System.out.println(
                "Added via addItem(LibraryItem): "
                        + item.getTitle());
    }

    public void addItem(Book book) {
        items.add(book);
        System.out.println(
                "Added via addItem(Book): "
                        + book.getTitle());
    }

    public void addItem(DVD dvd) {
        items.add(dvd);
        System.out.println(
                "Added via addItem(DVD): "
                        + dvd.getTitle());
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public List<LibraryItem> getItems() {
        return items;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void listItems() {
        for (LibraryItem item : items) {
            System.out.println(item);
        }
    }

    public void listMembers() {
        for (Member member : members) {
            System.out.println(member);
        }
    }

    // Week 3: Polymorphism

    public void printAllLateFees(int daysLate) {
        System.out.println(
                "--- Late fees if " + daysLate
                        + " day(s) late ---");

        for (LibraryItem item : items) {
            System.out.println(
                    item.getTitle() + ": "
                            + item.calculateLateFee(daysLate));
        }
    }

    // Week 3: instanceof DVD

    public void streamIfPossible(LibraryItem item) {

        if (item instanceof DVD dvd) {
            System.out.println(
                    "Streaming \"" + dvd.getTitle()
                            + "\" (" + dvd.getDurationMinutes()
                            + " min)...");
        } else {
            System.out.println(
                    item.getTitle()
                            + " cannot be streamed - it isn't a DVD.");
        }
    }

    // Week 4: Loanable Interface

    public void lend(Loanable thing, Member member) {
        thing.loan(member);
    }

    // Week 4: Reservable Interface

    public void reserveIfPossible(
            LibraryItem item, Member member) {

        if (item instanceof Reservable reservable) {
            reservable.reserve(member);
        } else {
            System.out.println(
                    item.getTitle()
                            + " cannot be reserved.");
        }
    }

    // Week 4: Comparable and Sorting

    public void printMembersByActivity() {

        List<Member> ranked = new ArrayList<>(members);

        Collections.sort(ranked);

        System.out.println(
                "--- Members ranked by activity ---");

        for (Member member : ranked) {
            System.out.println(
                    member.getName() + " ("
                            + member.getBorrowedCount()
                            + " borrowed)");
        }
    }
}
