

// JS for Contact Form 
document.getElementById('contactForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission

    // Show the loading overlay
    document.getElementById('loadingOverlay').style.display = 'flex';

    // Prepare form data
    const formData = new FormData(this);

    // Submit the form data using fetch
    fetch(this.action, {
        method: this.method,
        body: formData,
    })
    .then(response => {
        // Hide the loading overlay
        document.getElementById('loadingOverlay').style.display = 'none';

        if (response.ok) {
            showSubmissionPopup('Data sent successfully!', 'green', true);
        } else {
            throw new Error('Form submission failed.');
        }
    })
    .catch(error => {
        // Hide the loading overlay
        document.getElementById('loadingOverlay').style.display = 'none';

        showSubmissionPopup('Data failed to send.', 'red', false);
    });
});

function showSubmissionPopup(message, color, resetForm) {
    const popup = document.getElementById('formSubmissionPopup');
    const popupMessage = document.getElementById('popupMessageText');
    const popupButton = document.getElementById('popupCloseButton');

    popupMessage.textContent = message;
    popupMessage.style.color = color;
    popup.style.display = 'flex';

    popupButton.onclick = function() {
        popup.style.display = 'none';
        if (resetForm) {
            document.getElementById('contactForm').reset();
        }
    };
}
