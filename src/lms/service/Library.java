
package lms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lms.exception.ItemNotAvailableException;
import lms.exception.MemberNotFoundException;

import lms.model.Book;
import lms.model.DVD;
import lms.model.LibraryItem;
import lms.model.Loanable;
import lms.model.Member;
import lms.model.Reservable;

public class Library {

    private final List<LibraryItem> items = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();


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


    public void printAllLateFees(int daysLate) {

        System.out.println(
                "--- Late fees if "
                        + daysLate + " day(s) late ---");

        for (LibraryItem item : items) {
            System.out.println(
                    item.getTitle() + ": "
                            + item.calculateLateFee(daysLate));
        }
    }


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


    public void lend(Loanable thing, Member member)
            throws ItemNotAvailableException {

        thing.loan(member);
    }


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


    public void printMembersByActivity() {

        List<Member> ranked =
                new ArrayList<>(members);

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


    public Member findMember(String memberId) {

        for (Member member : members) {

            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }

        throw new MemberNotFoundException(memberId);
    }


    public LibraryItem findItem(String itemId)
            throws ItemNotAvailableException {

        for (LibraryItem item : items) {

            if (item.getId().equals(itemId)) {
                return item;
            }
        }

        throw new ItemNotAvailableException(
                "No item with id '" + itemId + "'");
    }


    public void borrowItem(String itemId, String memberId)
            throws ItemNotAvailableException {

        Member member = findMember(memberId);

        LibraryItem item = findItem(itemId);

        item.loan(member);
    }


    public void returnItem(String itemId)
            throws ItemNotAvailableException {

        LibraryItem item = findItem(itemId);

        item.returnItem();
    }
}
