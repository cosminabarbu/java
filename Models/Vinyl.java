package Models;

public class Vinyl extends LibraryItem {
    private String title;
    private String singer;
    private String genre;
    private Boolean specialEdition;

    public Vinyl() {
    }

    public Vinyl(String name, String type, double price, int stock, String title, String singer, String genre, Boolean specialEdition) {
        super(name, type, price, stock);
        this.title = title;
        this.singer = singer;
        this.genre = genre;
        this.specialEdition = specialEdition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Boolean getSpecialEdition() {
        return specialEdition;
    }

    public void setSpecialEdition(Boolean specialEdition) {
        this.specialEdition = specialEdition;
    }

    @Override
    public void printDetails() {
        System.out.println("Vinyl details: ");
        super.printDetails();
        System.out.println("Title: " + title);
        System.out.println("Singer: " + singer);
        System.out.println("Genre: " + genre);
        System.out.println("SpecialEdition: " + specialEdition);
    }
}
