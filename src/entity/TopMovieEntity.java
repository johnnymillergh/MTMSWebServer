package entity;

public class TopMovieEntity {
    private int id;
    private int movieId;
    private String movieTitle;
    private byte[] poster;
    private String posterStr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public byte[] getPoster() {
        return poster;
    }

    public void setPoster(byte[] poster) {
        this.poster = poster;
    }

    public String getPosterStr() {
        return posterStr;
    }

    public void setPosterStr(String posterStr) {
        this.posterStr = posterStr;
    }
}
