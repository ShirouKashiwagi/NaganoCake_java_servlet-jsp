/**
 * 
 */document.addEventListener("DOMContentLoaded", () => {
    const btn = document.getElementById("orderBtn");

    btn.addEventListener("click", () => {
        btn.style.transform = "scale(0.97)";
        setTimeout(() => {
            btn.style.transform = "scale(1)";
        }, 150);
    });
});