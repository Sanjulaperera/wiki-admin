/*
 * validation.js
 * Author: Sanjula Perera | Student ID: S1532573
 * Subject: BIT235 Object Oriented Programming
 * Assessment: Assessment 2, Part B - Wiki Content Management and Admin Console
 * Date: 2026
 */

const articleForm = document.querySelector("[data-article-form]");

if (articleForm) {
    articleForm.addEventListener("submit", function (event) {
        const title = articleForm.querySelector("#title").value.trim();
        const category = articleForm.querySelector("#categoryId").value;
        const content = articleForm.querySelector("#content").value.trim();
        const errorBox = articleForm.querySelector("[data-form-error]");

        if (title.length < 3 || category === "" || content.length < 30) {
            event.preventDefault();
            errorBox.textContent = "Please enter a title, category, and at least 30 characters of content.";
            errorBox.hidden = false;
            return;
        }

        if (!confirm("Save this article?")) {
            event.preventDefault();
        }
    });
}

document.querySelectorAll("[data-confirm-delete]").forEach(function (form) {
    form.addEventListener("submit", function (event) {
        if (!confirm("Delete this article? This action cannot be undone.")) {
            event.preventDefault();
        }
    });
});
