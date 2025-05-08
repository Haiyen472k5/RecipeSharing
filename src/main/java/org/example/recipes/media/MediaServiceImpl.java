// src/main/java/org/example/recipes/media/MediaServiceImpl.java
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

    @Override
    public List<String> getMediaUrls(String recipeId) {
        return repo.findByRecipeId(recipeId)
                .stream()
                .map(Media::getFileUrl)
                .collect(Collectors.toList());
    }
}
