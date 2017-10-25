package mif.ps.psp.FirstAssignment.TemplateMethodRealisation.Model;

import java.time.LocalDate;
import java.util.Date;

public class BookInformation {
    private String bookTitle;
    private String genre;
    private String publishDate;
    private Long pageNumber;
    private String authorName;
    private String ShortSummary;
    private double bookPrimePrice;
    private boolean inStock;
    private int bookID;
    private String bookExpiredate;
    private String startdate;

    public String getStartdate() { return startdate; }
    public void setStartdate(String startdate) { this.startdate = startdate; }
    public String getBookTitle() { return bookTitle; }
    public String getGenre() {
        return genre;
    }
    public String getPublishDate() {
        return publishDate;
    }
    public Long getPageNumber() { return pageNumber; }
    public String getAuthorName() {
        return authorName;
    }
    public String getShortSummary() {
        return ShortSummary;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setPublishDate(String date) {
        this.publishDate = date;
    }
    public void setPageNumber(Long pageNumber) { this.pageNumber = pageNumber; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    public void setShortSummary(String shortSummary) {
        ShortSummary = shortSummary;
    }
    public void setBookPrimePrice(double bookPrimePrice) { this.bookPrimePrice = bookPrimePrice; }
    public void setBookExpiredate(String bookExpiredate) { this.bookExpiredate = bookExpiredate; }
    public String getBookExpiredate() { return bookExpiredate; }
    public void setInStock(boolean inStock) { this.inStock = inStock; }
    public void setBookID(int bookID) { this.bookID = bookID; }
    public double getBookPrimePrice() { return bookPrimePrice; }
    public boolean isInStock() { return inStock; }
    public int getBookID() { return bookID; }
}
