package org.example.recipes.comment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repo;

    public CommentServiceImpl(CommentRepository repo) {
        this.repo = repo;
    }

    @Override
    public int countComments(String recipeId) {
        return repo.countByRecipeId(recipeId);
    }

    @Override
    public List<Comment> listComments(String recipeId) {
        return repo.findByRecipeIdOrderByCreatedAtDesc(recipeId);
    }

    @Override
    @Transactional
    public void addComment(String userId, String recipeId, String content) {
        Comment c = new Comment();
        c.setCommentId(UUID.randomUUID().toString().substring(0,10));
        c.setUserId(userId);
        c.setRecipeId(recipeId);
        c.setContent(content);
        c.setCreatedAt(LocalDateTime.now());
        repo.save(c);
    }

    @Override
    @Transactional
    public void deleteComment(String commentId) {
        repo.deleteById(commentId);
    }
}