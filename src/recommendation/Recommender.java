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
    private HashMap<Integer, List<UserReviewEntity>> ratingMatrix;

    /**
     * Target user and other user's common movies
     */
    private List<MovieEntity> commonMovies;

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

    public void setRatingMatrix(HashMap<Integer, List<UserReviewEntity>> ratingMatrix) {
        this.ratingMatrix = ratingMatrix;
    }

    public void setCommonMovies(List<MovieEntity> commonMovies) {
        this.commonMovies = commonMovies;
    }

    public List<Map.Entry<Integer, Double>> getSortedPredictedScores() {
        return sortedPredictedScores;
    }


}
