package org.example.recipes.media;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaServiceImpl implements MediaService {
    private final MediaRepository repo;

    public MediaServiceImpl(MediaRepository repo) {
        this.repo = repo;
    }

    /** Giữ nguyên để feed chỉ cần URL */
    @Override
    public List<String> getMediaUrls(String recipeId) {
        return repo.findByRecipeId(recipeId)
                .stream()
                .map(Media::getFileUrl)
                .collect(Collectors.toList());
    }

    /** Bổ sung để detail page lấy được full Media object */
    @Override
    public List<Media> getByRecipeId(String recipeId) {
        return repo.findByRecipeId(recipeId);
    }
}
