// src/main/java/org/example/recipes/media/MediaService.java
package org.example.recipes.media;

import java.util.List;

public interface MediaService {
    List<String> getMediaUrls(String recipeId);
}
