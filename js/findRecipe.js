const resultContainer = document.getElementById("searchResult");

for (let i = 1; i <= 9; i++) {
  const card = document.createElement("div");
  card.className = "small-recipe";
  card.innerHTML = `
    <div class="image-recipe"></div>
    <div class="recipe-meta">
      <div class="recipe-name">Dish's name ${i}</div>
      <div class="author">Author ${i}</div>
      <div class="category">Test</div>
      <div class="rate">
        <img class="star-icon" src="images/star.png" />
        <div class="rating-count">4.5</div>
      </div>
    </div>
  `;
  resultContainer.appendChild(card);
}
