// Function to handle smooth scrolling when navigating within the same page
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
        e.preventDefault();

        const target = document.querySelector(this.getAttribute('href'));

        if (target) {
            window.scrollTo({
                top: target.offsetTop,
                behavior: 'smooth'
            });
        }
    });
});

// Check if the URL contains a query parameter for the section
window.onload = function() {
    const urlParams = new URLSearchParams(window.location.search);
    const section = urlParams.get('section'); // Get the 'section' parameter from the URL

    if (section) {
        // If a section is specified, scroll to that section smoothly
        const target = document.getElementById(section);
        if (target) {
            setTimeout(() => {
                window.scrollTo({
                    top: target.offsetTop,
                    behavior: 'smooth' // Smooth scrolling
                });
            }, 500); // Delay to ensure the page fully loads before scrolling
        }
    }
};

// Function to handle cross-page scrolling
document.querySelectorAll('.scroll-link').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
        const href = this.getAttribute('href');

        // Check if the link points to a section on the home page (e.g., #about_us or #contact_us)
        if (href === '#about_us' || href === '#contact_us') {
            e.preventDefault();
            
            // If you're on the home page
            if (window.location.pathname === '/') {
                const target = document.querySelector(href);
                if (target) {
                    window.scrollTo({
                        top: target.offsetTop,
                        behavior: 'smooth'
                    });
                }
            } else {
                // Redirect to home page and pass the section hash
                window.location.href = `/?section=${href.substring(1)}`;
            }
        }
    });
});

// Smooth scrolling after redirect to home page
window.onload = function() {
    const urlParams = new URLSearchParams(window.location.search);
    const section = urlParams.get('section'); // Get the 'section' parameter from the URL

    if (section) {
        // Scroll smoothly to the specified section on the home page
        const target = document.getElementById(section);
        if (target) {
            setTimeout(() => {
                window.scrollTo({
                    top: target.offsetTop,
                    behavior: 'smooth'
                });
            }, 500); // Delay to ensure page fully loads before scrolling
        }
    }
};


// Function to add/remove the "scrolled" class based on scroll position (for a sticky header)
window.addEventListener("scroll", function() {
    var header = document.querySelector(".header2");

    if (window.scrollY > 50) { // Adjust the value based on when you want the border to appear
        header.classList.add("scrolled");
    } else {
        header.classList.remove("scrolled");
    }
});
