const BoatStore = (() => {
    let boats = [];

    return {
        setBoats: function (data) {
            boats = data;
            localStorage.setItem('boats', JSON.stringify(boats)); // Persist
        },
        getBoats: function () {
            if (boats.length === 0) { // Reload from storage if empty
                boats = JSON.parse(localStorage.getItem('boats')) || [];
            }
            return boats;
        },
        getBoatById: function (id) {
            return boats.find(boat => boat.id === id);
        }
    };
})();

let selectedBoatId = null;


// Function to populate selector
function populateBoatSelector() {
    const boats = BoatStore.getBoats(); // Retrieve all boats
    const container = document.getElementById("ship-selector-container");

    container.innerHTML = ""; // Clear existing buttons

    boats.forEach(boat => {
        const button = document.createElement("button");
        button.textContent = boat.title;
        button.onclick = () => selectBoat(boat.id); // Attach click event
        container.appendChild(button);
    });
}



// Select a boat and populate fields
function selectBoat(name) {
    const boat = BoatStore.getBoatById(name);
    selectedBoatId = boat.id;
    document.getElementById("boat-name").innerText = boat.title;
    document.getElementById("str").value = boat.str;
    document.getElementById("dex").value = boat.dex;
    document.getElementById("con").value = boat.cha;
    document.getElementById("intelligence").value = boat.intelligence;
    document.getElementById("wis").value = boat.wis;
    document.getElementById("cha").value = boat.charisma;
    document.getElementById("crew").value = boat.crew;
    document.getElementById("cargoCapacity").value = 0//boat.cargo;
    document.getElementById("travelPace").value = 0//boat.pace;
    document.getElementById("description").innerText = boat.description;
}

// Create a new empty boat
function createNewBoat() {
    selectedBoatId = null
    document.getElementById("boat-name").innerText = " "
    document.getElementById("str").value = 0
    document.getElementById("dex").value = 0
    document.getElementById("con").value = 0
    document.getElementById("intelligence").value = 0
    document.getElementById("wis").value = 0
    document.getElementById("cha").value = 0
    document.getElementById("crew").value = 0
    document.getElementById("description").innerText = " "
    document.getElementById("cargoCapacity").value = 0
    document.getElementById("travelPace").value = 0
}

async function saveBoat() {
    const boat = {
        title: document.getElementById('title').value,
        description: document.getElementById('description').value,
        width: parseInt(document.getElementById('width').value),
        height: parseInt(document.getElementById('height').value),
        crew: parseInt(document.getElementById('crew').value),
        str: parseInt(document.getElementById('str').value),
        dex: parseInt(document.getElementById('dex').value),
        cha: parseInt(document.getElementById('con').value),
        intelligence: parseInt(document.getElementById('intelligence').value),
        wis: parseInt(document.getElementById('wis').value),
        charisma: parseInt(document.getElementById('cha').value),
        armorclass: parseInt(document.getElementById('armorclass').value),
        hpHull: parseInt(document.getElementById('hpHull').value),
        hpSails: parseInt(document.getElementById('hpSails').value),
        hpSteer: parseInt(document.getElementById('hpSteer').value)
    };
    const isNew = !selectedBoatId // Check if it's a new boat

    const url = isNew
        ? 'http://localhost:8080/dm-api/dm/boats' // POST for new boat
        : `http://localhost:8080/dm-api/dm/boats/${selectedBoatId}`; // PUT for existing boat

    const method = isNew ? 'POST' : 'PUT'; // Choose the right HTTP method
    console.info(method);
    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(boat)
        });

        if (response.ok) {
            alert('Boat saved successfully!');
        } else {
            const errorData = await response.json();
            console.error('Failed to save boat:', errorData);
            alert('Failed to save boat!');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred while saving the boat.');
    }
    populateBoatSelector()
}


window.onload = async () => {
    try {
        // Fetch boats from the backend
        const response = await fetch("http://localhost:8080/dm-api/dm/1/boats");
        const boats = await response.json();

        // Save boats in BoatStore
        BoatStore.setBoats(boats);

        // Populate the selector dynamically
        populateBoatSelector();
    } catch (error) {
        console.error("Error loading boats:", error);
    }
};