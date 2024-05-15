package Models.Items;

import Models.Author;
import Models.Publisher;
import Models.Section;

public class Book extends LibraryItem {
    private Author author;
    private Section section;
    private Publisher publisher;
    private Integer pageNo;
    private Integer year;

    public Book(String title, double price, int stock, double rating, Author author, Section section, Publisher publisher, Integer pageNo, Integer year) {
        super(title, price, stock, rating);
        this.author = author;
        this.year = year;
        this.pageNo = pageNo;
        this.publisher = publisher;
        this.section = section;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Author findAuthorWithMostBooks(){ return author;}

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", rating=" + rating +
                ", itemId=" + itemId +
                "author=" + author +
                ", section=" + section +
                ", publisher=" + publisher +
                ", pageNo=" + pageNo +
                ", year=" + year +
                '}' + "\n";
    }
}
