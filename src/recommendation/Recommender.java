package recommendation;

import entity.MovieEntity;
import entity.UserEntity;
import entity.UserReviewEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Collaborative filtering user-based recommendation engine.
 * Data source: D={U,G,R}.
 *
 * @author Johnny Miller
 */
public class Recommender {
    /**
     * All user list; Stands for U in data source
     */
    private List<UserEntity> users;

    /**
     * All movie list; Stands for G in data source
     */
    private List<MovieEntity> movies;

    /**
     * User-rating matrix; Stands for R in data source
     */
    private HashMap<Integer, List<UserReviewEntity>> userRatingMatrix;

    /**
     * Target user and other user's common movies
     */
    private List<MovieEntity> commonMovies;

    /**
     * Users that have left comment to common movies
     */
    private List<UserEntity> commonUsers;

    /**
     * Unsorted nearest neighbors; Sorted data stored in List<Map.Entry<Integer, Double>> sortedNearestNeighbors
     */
    private HashMap<Integer, Double> nearestNeighbors;
    private List<Map.Entry<Integer, Double>> sortedNearestNeighbors;

    /**
     * Unsorted predicted scores; Sorted data stored in List<Map.Entry<Integer, Double>> sortedPredictedScores
     */
    private HashMap<Integer, Double> predictedScores;
    private List<Map.Entry<Integer, Double>> sortedPredictedScores;

    /**
     * Target user to be pushed recommendation items
     */
    private UserEntity targetUser;

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
    }

    public void setUserRatingMatrix(HashMap<Integer, List<UserReviewEntity>> userRatingMatrix) {
        this.userRatingMatrix = userRatingMatrix;
    }

    public void setCommonMovies(List<MovieEntity> commonMovies) {
        this.commonMovies = commonMovies;
    }

    public void setCommonUsers(List<UserEntity> commonUsers) {
        this.commonUsers = commonUsers;
    }

    public List<Map.Entry<Integer, Double>> getSortedPredictedScores() {
        return sortedPredictedScores;
    }

    public void calculateSimilarity() {

    }

    public double getAverageScoreOfCommonMovies(int userId) {
        List<UserReviewEntity> userReviews = userRatingMatrix.get(userId);
        double averageScore = 0d;
        for (MovieEntity me : commonMovies) {
            int movieId = me.getId();
            int userReviewIndex = movieId - 1;
            UserReviewEntity userReviewEntity = userReviews.get(userReviewIndex);
            averageScore += userReviewEntity.getScore();
        }
        return averageScore / commonMovies.size();
    }
}























