package org.example.recipes.rate;

public class RateRequest {

        private String userId;
        private String recipeId;
        private int rating;
        // getter/setter
        public RateRequest(String userId, String recipeId, int rating) {
            this.userId = userId;
            this.recipeId = recipeId;
            this.rating = rating;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getRecipeId() {
            return recipeId;
        }

        public void setRecipeId(String recipeId) {
            this.recipeId = recipeId;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

}
