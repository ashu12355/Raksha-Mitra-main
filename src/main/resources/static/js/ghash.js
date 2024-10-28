// Variables to hold data
let data = {};
let disasterTypes = [];

// Fetch and load data from JSON files
function fetchData() {
    // Load states and districts data
    fetch('../json/data.json')
        .then(response => response.json())
        .then(jsonData => {
            data = jsonData;
            populateStateDropdown();
        })
        .catch(error => console.error('Error loading states data:', error));

    // Load disaster types data
    fetch('../json/types.json')
        .then(response => response.json())
        .then(jsonData => {
            disasterTypes = jsonData.types;
            populateTypeDropdown();
        })
        .catch(error => console.error('Error loading disaster types data:', error));
}

// Populate state dropdown
function populateStateDropdown() {
    const stateSelect = document.getElementById('stateSelect');
    data.states.forEach(state => {
        const option = document.createElement('option');
        option.value = state.code;
        option.textContent = state.name;
        stateSelect.appendChild(option);
    });
}

// Populate district dropdown based on selected state
function populateDistrictDropdown() {
    const stateCode = document.getElementById('stateSelect').value;
    const districtSelect = document.getElementById('districtSelect');
    const subDistrictSelect = document.getElementById('subDistrictSelect');
    
    districtSelect.innerHTML = '<option value="">Choose Your District</option>';
    subDistrictSelect.innerHTML = '<option value="">Choose Your Sub-District</option>';
    subDistrictSelect.disabled = true;

    if (stateCode) {
        const state = data.states.find(s => s.code === stateCode);
        state.districts.forEach(district => {
            const option = document.createElement('option');
            option.value = district.shortName;
            option.textContent = district.name;
            districtSelect.appendChild(option);
        });
        districtSelect.disabled = false;
    }
}

// Populate sub-district dropdown based on selected district
function populateSubDistrictDropdown() {
    const stateCode = document.getElementById('stateSelect').value;
    const districtShortName = document.getElementById('districtSelect').value;
    const subDistrictSelect = document.getElementById('subDistrictSelect');
    
    subDistrictSelect.innerHTML = '<option value="">Select Sub-District</option>';

    if (stateCode && districtShortName) {
        const state = data.states.find(s => s.code === stateCode);
        const district = state.districts.find(d => d.shortName === districtShortName);
        district.subDistricts.forEach(subDist => {
            const option = document.createElement('option');
            option.value = subDist;
            option.textContent = subDist;
            subDistrictSelect.appendChild(option);
        });
        subDistrictSelect.disabled = false;
    }
}

// Populate disaster type dropdown
function populateTypeDropdown() {
    const typeSelect = document.getElementById('typeSelect');
    disasterTypes.forEach(type => {
        const option = document.createElement('option');
        option.value = type;
        option.textContent = type;
        typeSelect.appendChild(option);
    });
}

// Generate and display hashtags
function generateHashtags(event) {
    event.preventDefault(); // Prevent form submission and page refresh

    const stateCode = document.getElementById('stateSelect').value;
    const districtShortName = document.getElementById('districtSelect').value;
    const subDistrict = document.getElementById('subDistrictSelect').value;
    const disasterType = document.getElementById('typeSelect').value;

    const resultsDiv = document.getElementById('results');
    resultsDiv.innerHTML = ''; // Clear previous results

    if (stateCode && districtShortName && subDistrict && disasterType) {
        const hashtag = `#RM24X7_${stateCode}_${districtShortName}_${subDistrict}_${disasterType}`;
        const hashtagDiv = document.createElement('div');
        hashtagDiv.style.display = 'flex';
hashtagDiv.style.alignItems = 'center';
hashtagDiv.style.gap = '20px';
        hashtagDiv.className = 'hashtag';
        hashtagDiv.innerHTML = `
        
            <h3 >${hashtag}</h3>
            <span class="copy-btn" onclick="copyToClipboard('${hashtag}')">Copy</span>
        `;
        resultsDiv.appendChild(hashtagDiv);
    } else {
        resultsDiv.innerHTML = '<h3>Please select all fields.</h3>';
    }
}

// Copy text to clipboard
function copyToClipboard(text) {
    navigator.clipboard.writeText(text)
        .then(() => alert('Hashtag copied to clipboard!'))
        .catch(err => console.error('Failed to copy text: ', err));
}

// Event listeners
document.getElementById('stateSelect').addEventListener('change', populateDistrictDropdown);
document.getElementById('districtSelect').addEventListener('change', populateSubDistrictDropdown);
document.getElementById('submitBtn').addEventListener('click', generateHashtags);

// Load data on page load
fetchData();
