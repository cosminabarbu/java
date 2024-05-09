package Models;

public class Book extends LibraryItem{
    private Author author;
    private String title;
    private Section section;
    private Publisher publisher;
    private Integer pageNo;
    private Integer rating;
    private Integer bookId;
    private Integer nextId;
    private Integer year;

    public Book() {
        this.bookId = nextId;
        nextId++;
    }

    public Book(String name, String type, double price, int stock, Author author, String title, Section section, Publisher publisher, Integer pageNo, Integer rating, Integer bookId, Integer nextId, Integer year) {
        super(name, type, price, stock);
        this.author = author;
        this.title = title;
        this.section = section;
        this.publisher = publisher;
        this.pageNo = pageNo;
        this.rating = rating;
        this.bookId = bookId;
        this.nextId = nextId;
        this.year = year;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public void printDetails() {
        System.out.println("Book Details:");
        super.printDetails();
        System.out.println("Title: " + title);
        System.out.println("Author: " + author.getName());
        System.out.println("Section: " + section);
        System.out.println("Publisher: " + publisher.getName());
        System.out.println("Page Number: " + pageNo);
        System.out.println("Rating: " + rating);
        System.out.println("Book ID: " + bookId);
        System.out.println("Year: " + year);
    }
}
