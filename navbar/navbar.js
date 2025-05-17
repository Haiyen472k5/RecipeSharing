function initNavbar() {
  const toggle = document.getElementById("menuToggle");
  const menu = document.getElementById("navMenu");
  if (toggle && menu) {
    toggle.addEventListener("click", () => {
      menu.classList.toggle("active");
    });
  }

  const input = document.getElementById("searchInput");
  const list = document.getElementById("suggestionList");
  if (input && list) {
    input.addEventListener("focus", () => list.hidden = false);
    input.addEventListener("blur", () => setTimeout(() => list.hidden = true, 150));
    input.addEventListener("input", () => list.hidden = false);
  }
}
