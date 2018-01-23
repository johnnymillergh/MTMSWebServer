package entity;

import java.sql.Timestamp;

public class UserReviewEntity {
    private int id;
    private int userId;
    private int movieId;
    private int score;
    private String title;
    private String text;
    private boolean isSpoilers;
    private Timestamp dateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getIsSpoilers() {
        return isSpoilers;
    }

    public void setIsSpoilers(boolean isSpoilers) {
        this.isSpoilers = isSpoilers;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "UserReviewEntity: " + id + ", " + userId + ", " + movieId + ", " + score;
    }
}
