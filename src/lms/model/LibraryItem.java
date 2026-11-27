
package lms.model;

import lms.exception.ItemNotAvailableException;

public abstract class LibraryItem implements Loanable {

    private String id;
    private String title;
    private boolean isBorrowed;
    private Member borrower;

    public LibraryItem(String id, String title) {
        setId(id);
        setTitle(title);
        this.isBorrowed = false;
        this.borrower = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                    "ID cannot be blank");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Title cannot be blank");
        }
        this.title = title;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.isBorrowed = borrowed;
    }

    public Member getBorrower() {
        return borrower;
    }

    @Override
    public void loan(Member member)
            throws ItemNotAvailableException {

        if (isBorrowed) {
            throw new ItemNotAvailableException(
                    getTitle() + " is already on loan.");
        }

        if (member == null) {
            throw new IllegalArgumentException(
                    "Member cannot be null");
        }

        if (member.getBorrowedCount()
                >= member.getMaxBorrowLimit()) {

            throw new ItemNotAvailableException(
                    member.getName()
                            + " has reached the limit of "
                            + member.getMaxBorrowLimit()
                            + " items.");
        }

        this.borrower = member;
        this.isBorrowed = true;

        member.incrementBorrowedCount();

        System.out.println(
                getTitle() + " loaned to "
                        + member.getName());
    }

    @Override
    public void returnItem()
            throws ItemNotAvailableException {

        if (!isBorrowed) {
            throw new ItemNotAvailableException(
                    getTitle() + " is not on loan.");
        }

        if (borrower != null) {
            borrower.decrementBorrowedCount();
        }

        borrower = null;
        isBorrowed = false;

        System.out.println(
                getTitle() + " returned.");
    }

    public abstract double calculateLateFee(int daysLate);

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + "{id='" + id
                + "', title='" + title
                + "', borrowed=" + isBorrowed + "}";
    }
}
