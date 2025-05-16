package com.recipeshare.backend.Like;

public interface LikeService {
    boolean toggleLike(String username, Long recipeId); /// true neu la like moi, false neu la bo like
    boolean hasLiked(String username, Long recipeId);
}
