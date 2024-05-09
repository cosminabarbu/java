package Models;

public class Vinyl extends LibraryItem {
    private String singer;
    private String genre;
    private Boolean specialEdition;

    public Vinyl() {
    }

    public Vinyl(String title, double price, int stock, double rating, String singer, String genre, Boolean specialEdition) {
        super(title, price, stock, rating);
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
    public String toString() {
        return "Vinyl{" +
                "singer='" + singer + '\'' +
                ", genre='" + genre + '\'' +
                ", specialEdition=" + specialEdition +
                '}';
    }
}
