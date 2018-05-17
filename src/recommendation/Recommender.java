package recommendation;

import entity.MovieEntity;
import entity.UserEntity;
import entity.UserReviewEntity;

import java.util.*;

/**
 * Collaborative filtering user-based recommendation engine.
 * Data source: D={U,G,R}.
 *
 * @author Johnny Miller
 */
@SuppressWarnings("Duplicates")
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
    private HashMap<Integer, List<UserReviewEntity>> userScoreMatrix;

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
    private HashMap<Integer, Double> nearestNeighbors = new HashMap<>();
    private List<Map.Entry<Integer, Double>> sortedNearestNeighbors;

    /**
     * Unsorted predicted scores; Sorted data stored in List<Map.Entry<Integer, Double>> sortedPredictedScores
     */
    private HashMap<Integer, Double> predictedScores = new HashMap<>();
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

    public void setUserScoreMatrix(HashMap<Integer, List<UserReviewEntity>> userScoreMatrix) {
        this.userScoreMatrix = userScoreMatrix;
    }

    public void setCommonMovies(List<MovieEntity> commonMovies) {
        this.commonMovies = commonMovies;
    }

    public void setCommonUsers(List<UserEntity> commonUsers) {
        this.commonUsers = commonUsers;
    }

    public void setTargetUser(UserEntity targetUser) {
        this.targetUser = targetUser;
    }

    public List<Map.Entry<Integer, Double>> getSortedNearestNeighbors() {
        return sortedNearestNeighbors;
    }

    public List<Map.Entry<Integer, Double>> getSortedPredictedScores() {
        return sortedPredictedScores;
    }

    public void calculateSimilarity() {
        double numerator = 0, denominator1 = 0, denominator2 = 0;
        List<UserReviewEntity> targetUserReviews = userScoreMatrix.get(targetUser.getId());
        double targetUserAverageScoreOfCommonMovies = getAverageScoreOfCommonMovies(targetUser.getId());

        for (UserEntity otherUser : commonUsers) {
            // calc the numerator of sim(targetUser, otherUSer)
            List<UserReviewEntity> otherUserReviews = userScoreMatrix.get(otherUser.getId());
            for (MovieEntity movieEntity : commonMovies) {
                int movieId = movieEntity.getId();
                int userReviewIndex = movieId - 1;

                double targetUserScore = targetUserReviews.get(userReviewIndex).getScore();

                double otherUserScore = otherUserReviews.get(userReviewIndex).getScore();
                double otherUserAverageScore = getAverageScoreOfCommonMovies(otherUser.getId());

                numerator += (targetUserScore - targetUserAverageScoreOfCommonMovies) * (otherUserScore - otherUserAverageScore);

                denominator1 += Math.pow((targetUserScore - targetUserAverageScoreOfCommonMovies), 2);
                denominator2 += Math.pow((otherUserScore - otherUserAverageScore), 2);
            }

            // sim(i,j)
            double similarity = numerator / (Math.sqrt(denominator1) * Math.sqrt(denominator2));
            // Save similarity
            nearestNeighbors.put(otherUser.getId(), similarity);
        }

        // Sort nearestNeighbors in descending order
        sortedNearestNeighbors = new ArrayList<>(nearestNeighbors.entrySet());
        Collections.sort(sortedNearestNeighbors, (o1, o2) -> {
            // In descending order
            if (o1.getValue() > o2.getValue()) {
                return -1;
            } else if (o1.getValue() < o2.getValue()) {
                return 1;
            } else {
                return 0;
            }
        });
    }

    public double getAverageScoreOfCommonMovies(int userId) {
        List<UserReviewEntity> userReviews = userScoreMatrix.get(userId);
        double averageScore = 0d;
        for (MovieEntity me : commonMovies) {
            int movieId = me.getId();
            int userReviewIndex = movieId - 1;
            UserReviewEntity userReviewEntity = userReviews.get(userReviewIndex);
            averageScore += userReviewEntity.getScore();
        }
        return averageScore / commonMovies.size();
    }

    public void calculatePredictedScore() {
        double targetUserAverageScoreOfRatedMovies = getAverageScoreOfRatedMovies(targetUser.getId());

        for (MovieEntity me : movies) {
            int movieId = me.getId();
            double predictedScoreOfThisMovie = targetUserAverageScoreOfRatedMovies;
            double numerator = 0d, denominator = 0d;

            for (Map.Entry<Integer, Double> snn : sortedNearestNeighbors) {
                int otherUserId = snn.getKey();
                double similarityTargetUserAndOtherUser = snn.getValue();
                double otherUserAverageScoreOfRatedMovies = getAverageScoreOfRatedMovies(otherUserId);
                int scoreOfMovie = getScoreOfMovie(otherUserId, movieId);

                numerator += similarityTargetUserAndOtherUser * (scoreOfMovie - otherUserAverageScoreOfRatedMovies);
                denominator += Math.abs(similarityTargetUserAndOtherUser);
            }

            predictedScoreOfThisMovie += numerator / denominator;
            predictedScores.put(movieId, predictedScoreOfThisMovie);
        }

        // Sort nearestNeighbors in descending order
        sortedPredictedScores = new ArrayList<>(predictedScores.entrySet());
        sortedPredictedScores.sort((o1, o2) -> {
            // In descending order
            if (o1.getValue() > o2.getValue()) {
                return -1;
            } else if (o1.getValue() < o2.getValue()) {
                return 1;
            } else {
                return 0;
            }
        });
    }

    public double getAverageScoreOfRatedMovies(int userId) {
        List<UserReviewEntity> userReviews = userScoreMatrix.get(userId);

        double totalScore = 0d;
        int ratedMovieCount = 0;
        for (UserReviewEntity ure : userReviews) {
            if (ure.getScore() != 0) {
                totalScore += ure.getScore();
                ratedMovieCount++;
            }
        }

        if (totalScore == 0) {
            return 0;
        } else if (ratedMovieCount == 0) {
            return 0;
        } else {
            return totalScore / ratedMovieCount;
        }
    }

    public int getScoreOfMovie(int userId, int movieId) {
        List<UserReviewEntity> userReviews = userScoreMatrix.get(userId);
        int userReviewIndex = movieId - 1;
        return userReviews.get(userReviewIndex).getScore();
    }

    public void removeTargetUserRatedMovie() {

    }
}























