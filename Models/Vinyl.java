package Models;

public class Vinyl extends LibraryItem {
    private String singer;
    private String genre;
    private Boolean specialEdition;

    public Vinyl() {
    }

    public Vinyl(String title, double price, int stock, String singer, String genre, Boolean specialEdition) {
        super(title, price, stock);
        this.singer = singer;
        this.genre = genre;
        this.specialEdition = specialEdition;
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
        System.out.println("Singer: " + singer);
        System.out.println("Genre: " + genre);
        System.out.println("SpecialEdition: " + specialEdition);
    }
}
