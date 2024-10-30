function createProject(createButton) {
    createButton.addEventListener('click', function() {
        console.log('Your new page is loading...');
        window.location.href = 'createProject.html';
    });
}


// Function to handle form submission
function submitProjectForm(event) {
    event.preventDefault(); // Prevent form from reloading the page
    
    // Get form data
    const projectName = document.getElementById('projectName').value;
    const projectDescription = document.getElementById('projectDescription').value;
    
    // Log form data to the console
    console.log('Project Name:', projectName);
    console.log('Project Description:', projectDescription);
    
    // Display the data on the page
    const displayArea = document.getElementById('projectdetails');
    displayArea.innerHTML = `
        <h3>Project Details</h3>
        <p><strong>Name:</strong> ${projectName}</p>
        <p><strong>Description:</strong> ${projectDescription}</p>
    `;
    document.body.prepend(displayArea); // Add data to the left side
}

document.addEventListener('DOMContentLoaded', () => {
    // For index.html
    const createButton = document.getElementById('createProj');
    if (createButton) createProject(createButton);

    // For createProject.html
    const projForm = document.querySelector('form');
    if (projForm) projForm.addEventListener('submit', submitProjectForm);
});