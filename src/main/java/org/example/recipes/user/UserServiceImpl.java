package org.example.recipes.user;

import org.example.recipes.follow.FollowService;
import org.example.recipes.recipe.RecipeService;
import org.example.recipes.recipe.RecipeSimpleDTO;
import org.example.recipes.recipe.Recipes;
import org.example.recipes.save.SaveService;
import org.example.recipes.like.LikeService;
import org.example.recipes.rate.RateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final FollowService followService;
    private final RecipeService recipeService;
    private final SaveService saveService;
    private final LikeService likeService;
    private final RateService rateService;

    public UserServiceImpl(
            UserRepository userRepo,
            FollowService followService,
            RecipeService recipeService,
            SaveService saveService,
            LikeService likeService,
            RateService rateService
    ) {
        this.userRepo       = userRepo;
        this.followService  = followService;
        this.recipeService  = recipeService;
        this.saveService    = saveService;
        this.likeService    = likeService;
        this.rateService    = rateService;
    }

    @Override
    public List<UserSimpleDTO> getFollowers(String userId) {
        return followService.getFollowers(userId);
    }

    @Override
    public List<UserSimpleDTO> getFollowing(String userId) {
        return followService.getFollowing(userId);
    }

    @Override
    public UserPublicDTO getPublicProfile(String userId) {
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        UserPublicDTO dto = new UserPublicDTO();
        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFirstName() + " " + user.getLastName());
        dto.setAvatarUrl(user.getAvatarUrl());
        dto.setDateOfBirth(user.getDateOfBirth());

        // Basic statistics
        dto.setFollowersCount(followService.getFollowers(userId).size());
        dto.setFollowingCount(followService.getFollowing(userId).size());
        List<Recipes> posts = recipeService.findPostedByUser(userId);
        dto.setTotalPosts(posts.size());

        // Calculate average rating across all posts
        double avgRating = posts.stream()
                .mapToDouble(r -> rateService.getAverageRating(r.getRecipeId()))
                .average()
                .orElse(0.0);
        dto.setAverageRating(avgRating);

        return dto;
    }

    @Override
    public List<RecipeSimpleDTO> getPostedRecipes(String userId) {
        return recipeService.findPostedByUser(userId).stream()
                .map(this::toSimpleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeSimpleDTO> getSavedRecipes(String userId) {
        return saveService.getSavedRecipes(userId).stream()
                .map(this::toSimpleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeSimpleDTO> getLikedRecipes(String userId) {
        return likeService.getLikedRecipes(userId).stream()
                .map(this::toSimpleDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateUserInfo(String currentUserId, UserProfileUpdatedDTO dto) {
        Users user = userRepo.findById(currentUserId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + currentUserId));

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAvatarUrl(dto.getAvatarUrl());
        user.setDateOfBirth(dto.getDateOfBirth());

        userRepo.save(user);
    }

    private RecipeSimpleDTO toSimpleDTO(Recipes r) {
        RecipeSimpleDTO d = new RecipeSimpleDTO();
        d.setId(r.getRecipeId());
        d.setTitle(r.getName());
        d.setImageUrl(r.getAvatarUrl());
        d.setAverageRating((float) rateService.getAverageRating(r.getRecipeId()));
        d.setLikeCount(likeService.countLikes(r.getRecipeId()));
        return d;
    }
}