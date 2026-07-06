document.addEventListener("DOMContentLoaded", function () {
    const savedScrollPosition = sessionStorage.getItem("cartScrollPosition");

    if (savedScrollPosition !== null) {
        window.scrollTo(0, parseInt(savedScrollPosition));
        sessionStorage.removeItem("cartScrollPosition");
    }

    const cartForms = document.querySelectorAll(".cart-actions form, .cart-summary form");

    cartForms.forEach(function (form) {
        form.addEventListener("submit", function () {
            sessionStorage.setItem("cartScrollPosition", window.scrollY);
        });
    });
});