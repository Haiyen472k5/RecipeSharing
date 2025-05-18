package com.recipeshare.backend.Service;

public interface LikeService {
    boolean toggleLike(String username, Long recipeId); /// true neu la like moi, false neu la bo like
    boolean hasLiked(String username, Long recipeId);
}
