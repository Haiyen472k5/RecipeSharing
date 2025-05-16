package com.recipeshare.backend.User;

import com.recipeshare.backend.Comment.CommentDTO;
import com.recipeshare.backend.Comment.CommentRepository;
import com.recipeshare.backend.Follow.Follow;
import com.recipeshare.backend.Follow.FollowRepository;
import com.recipeshare.backend.Like.Like;
import com.recipeshare.backend.Like.LikeRepository;
import com.recipeshare.backend.Rating.Rating;
import com.recipeshare.backend.Rating.RatingRepository;
import com.recipeshare.backend.Recipe.Recipe;
import com.recipeshare.backend.Recipe.RecipeRepository;
import com.recipeshare.backend.Recipe.RecipeSimpleDTO;
import com.recipeshare.backend.Save.Save;
import com.recipeshare.backend.Save.SaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final RecipeRepository recipeRepository;
    private final RatingRepository ratingRepository;
    private final CommentRepository commentRepository;
    private final SaveRepository saveRepository;
    private final LikeRepository likeRepository;


    @Autowired

    public UserServiceImpl(UserRepository userRepository, FollowRepository followRepository, RecipeRepository recipeRepository, RatingRepository ratingRepository, CommentRepository commentRepository, SaveRepository saveRepository, LikeRepository likeRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.recipeRepository = recipeRepository;
        this.ratingRepository = ratingRepository;
        this.commentRepository = commentRepository;
        this.saveRepository = saveRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public UserPublicDTO getPublicProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        int followersCount = followRepository.countByFollowing(user);
        int followingCount = followRepository.countByFollower(user);
        int totalPosts = recipeRepository.findByAuthor(user).size();
        Double avgRating = ratingRepository.findAverageScoreByAuthor(user);
        double averageRating = avgRating != null ? Math.round(avgRating * 100.0) / 100.0 : 0.0;


        return new UserPublicDTO(
                user.getUsername(),
                user.getFullName(),
                user.getAvatarUrl(),
                user.getDateOfBirth(),
                followersCount,
                followingCount,
                totalPosts,
                averageRating
        );
    }



    @Override
    public List<UserSimpleDTO> getFollowers(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Ai đang theo dõi user → FOLLOWING = user
        List<Follow> follows = followRepository.findByFollowing(user);

        return follows.stream()
                .map(Follow::getFollower)
                .map(follower -> new UserSimpleDTO(
                        follower.getUsername(),
                        follower.getAvatarUrl()))
                .toList();
    }

    @Override
    public List<UserSimpleDTO> getFollowing(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // User đang theo dõi ai → FOLLOWER = user
        List<Follow> follows = followRepository.findByFollower(user);

        return follows.stream()
                .map(Follow::getFollowing)
                .map(following -> new UserSimpleDTO(
                        following.getUsername(),
                        following.getAvatarUrl()))
                .toList();
    }

    @Override
    public List<RecipeSimpleDTO> getPostedRecipes(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Recipe> recipes = recipeRepository.findByAuthor(user);

        return recipes.stream().map(recipe -> {
            double avgRating = 0.0;
            if (recipe.getRatings() != null && !recipe.getRatings().isEmpty()) {
                avgRating = recipe.getRatings().stream()
                        .mapToInt(Rating::getScore)
                        .average().orElse(0.0);
            }

            int likeCount = recipe.getLikes() != null? recipe.getLikes().size(): 0;

            List<CommentDTO> comments = commentRepository.findByRecipe(recipe).stream()
                    .map(c -> new CommentDTO(c.getUser().getUsername(), c.getContent()))
                    .toList();
            return new RecipeSimpleDTO(
                    recipe.getId(),
                    recipe.getTitle(),
                    recipe.getImageUrl(),
                    avgRating,
                    likeCount,
                    comments
            );
        }).toList();
    }

    @Override
    public List<RecipeSimpleDTO> getSavedRecipes(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Save> saves = saveRepository.findByUser(user);

        return saves.stream()
                .map(Save::getRecipe)
                .map(recipe -> {
                    double avgRating = 0.0;
                    if (recipe.getRatings() != null && !recipe.getRatings().isEmpty()) {
                        avgRating = recipe.getRatings().stream()
                                .mapToInt(Rating::getScore)
                                .average().orElse(0.0);
                    }

                    int likeCount = recipe.getLikes() != null? recipe.getLikes().size(): 0;

                    List<CommentDTO> comments = commentRepository.findByRecipe(recipe).stream()
                            .map(c -> new CommentDTO(c.getUser().getUsername(), c.getContent()))
                            .toList();


                    return new RecipeSimpleDTO(
                            recipe.getId(),
                            recipe.getTitle(),
                            recipe.getImageUrl(),
                            avgRating,
                            likeCount,
                            comments
                    );
                }).toList();
    }

    @Override
    public List<RecipeSimpleDTO> getLikedRecipes(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Like> likes = likeRepository.findByUser(user);

        return likes.stream()
                .map(Like::getRecipe)
                .map(recipe -> {
                    double avgRating = 0.0;
                    if (recipe.getRatings() != null && !recipe.getRatings().isEmpty()) {
                        avgRating = recipe.getRatings().stream()
                                .mapToInt(Rating::getScore)
                                .average().orElse(0.0);
                    }

                    int likeCount = recipe.getLikes() != null? recipe.getLikes().size(): 0;

                    List<CommentDTO> comments = commentRepository.findByRecipe(recipe).stream()
                            .map(c -> new CommentDTO(c.getUser().getUsername(), c.getContent()))
                            .toList();


                    return new RecipeSimpleDTO(
                            recipe.getId(),
                            recipe.getTitle(),
                            recipe.getImageUrl(),
                            avgRating,
                            likeCount,
                            comments
                    );
                }).toList();
    }

    @Override
    public void updateUserInfo(String currentUsername, UserProfileUpdatedDTO dto) {
        if (dto.getUsername() == null || dto.getUsername().isBlank()) {
            throw new RuntimeException("Username cannot be empty");
        }
        if (dto.getFullName() == null || dto.getFullName().isBlank()) {
            throw new RuntimeException("Full name cannot be empty");
        }
        if (dto.getAvatarUrl() == null || dto.getAvatarUrl().isBlank()) {
            throw new RuntimeException("Avatar URL cannot be empty");
        }

        // Kiểm tra username mới có trùng không (nếu đổi)
        if (!dto.getUsername().equals(currentUsername)) {
            if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
                throw new RuntimeException("Username already exists");
            }
        }

        // Tìm user và cập nhật
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(dto.getUsername());
        user.setFullName(dto.getFullName());
        user.setAvatarUrl(dto.getAvatarUrl());

        userRepository.save(user);
    }
}
