package Models;

public class Book {
    private String title;
    private Author author;
    private Section section;
    private Publisher publisher;
    private Integer pageNo;
    private Integer rating;
    private Integer bookId;
    private Integer nextId;
    private Integer price;
    private Integer year;

    public Book() {
        this.bookId = nextId;
        nextId++;
    }

    public Book(String title, Author author, Section section, Publisher publisher, Integer pageNo, Integer rating, Integer bookId, Integer nextId, Integer price, Integer year) {
        this.title = title;
        this.author = author;
        this.section = section;
        this.publisher = publisher;
        this.pageNo = pageNo;
        this.rating = rating;
        this.bookId = bookId;
        this.nextId = nextId;
        this.price = price;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getNextId() {
        return nextId;
    }

    public void setNextId(Integer nextId) {
        this.nextId = nextId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void printDetails() {
        System.out.println("Book Details:");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author.getName());
        System.out.println("Section: " + section);
        System.out.println("Publisher: " + publisher.getName());
        System.out.println("Page Number: " + pageNo);
        System.out.println("Rating: " + rating);
        System.out.println("Book ID: " + bookId);
        System.out.println("Price: $" + price);
        System.out.println("Year: " + year);
    }
}
