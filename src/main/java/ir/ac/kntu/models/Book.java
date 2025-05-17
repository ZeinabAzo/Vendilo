package ir.ac.kntu.models;

import ir.ac.kntu.enums.AgeGroup;
import ir.ac.kntu.enums.BookGenre;

public class Book extends Product{

    private String authorName;
    private double pageCount;
    private BookGenre bookGenre;
    private AgeGroup ageGroup;
    private String ISBN;

    public Book(String name, double price, int inventory, String authorName, double pageCount,
                BookGenre bookGenre, AgeGroup ageGroup, String ISBN){
        super(name, price, inventory);
        this.authorName=authorName;
        this.pageCount=pageCount;
        this.bookGenre=bookGenre;
        this.ageGroup=ageGroup;
        this.ISBN=ISBN;

    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public double getPageCount() {
        return pageCount;
    }

    public void setPageCount(double pageCount) {
        this.pageCount = pageCount;
    }

    public BookGenre getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(BookGenre bookGenre) {
        this.bookGenre = bookGenre;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

}
