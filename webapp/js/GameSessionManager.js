// sessionScripts.js

let boats = [];
let selectedBoats = [];
let gameSessions = [];
let sessionId = null;
const url = "http://localhost:8080";
const dmId = 1;
// Populate Boats Dropdown
function loadBoats() {
    const url2 = "/dm-api/dm/".concat(dmId.toString(), "/boats")
    fetch(url.concat(url2))
        .then(response => response.json())
        .then(data => {
            boats = data;
            const boatChooser = document.getElementById('boatChooser');
            boatChooser.innerHTML = '';
            boats.forEach(boat => {
                let option = document.createElement('option');
                option.value = boat.id;
                option.textContent = boat.title;
                boatChooser.appendChild(option);
            });
        });
}

// Populate Game Sessions Dropdown
function loadGameSessions() {
    const url2 = "/game/gm/".concat(dmId.toString())
    const sessionChooser = document.getElementById('sessionChooser');
    sessionChooser.innerHTML = '';
    fetch(url.concat(url2)).then(response => response.json()).then(data => {
        gameSessions = data;
            gameSessions.forEach(session => {
                let option = document.createElement('option');
                option.value = session.id;
                option.textContent = session.title;
                sessionChooser.appendChild(option);
            });
        });
}

// Save Game Session
async function saveGameSession(start = false) {
    let tmpBoats = [];
    const url2 = "/game/";
    // Loop through each boat listed in the UI

    selectedBoats.forEach((b) => {
        const boat = {
            title: b.title,
            hpHull: b.hpHull,
            cHpHull: b.hpHull,
            hpSails: b.hpSails,
            cHpSails: b.hpSails,
            hpSteer: b.hpSteer,
            cHpSteer: b.hpSteer,
            sessionId: 0,
            templateBoatId: b.templateBoatId,
            str: b.str,
            dex: b.dex,
            cha: b.cha,
            intelligence: b.intelligence,
            wis: b.wis,
            charisma: b.charisma,
            description: b.description,
            shared: b.shared,
            ac:b.ac,
            crew: b.crew,
            status: ""
        };

        tmpBoats.push(boat);
    });

    let gameSession = {
        title: document.getElementById('title').value,
        dmId: dmId,
        boats: tmpBoats
    };
    const isNew = !sessionId // Check if it's a new Session
    const method = isNew ? 'POST' : 'PUT';
    const reqUrl = isNew
        ? url.concat(url2) // POST for new session
        : url.concat(url2, sessionId); // PUT for existing session
    if (!isNew) gameSession.id = sessionId;

    try {
        const response = await fetch(reqUrl, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(gameSession)
        });
        if (response.ok) {
            const tmp = await response.json();
            sessionId = (tmp).id;
            console.log(tmp);

            alert('Game session saved successfully!');
        } else {
            const errorData = await response.json();
            console.error('Failed to save game session:', errorData);
            alert('Failed to save game session!');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred while saving the game session.');
    }
    if (!start) loadGameSessions()
}


// Add Selected Boat
document.getElementById('addBoatBtn').addEventListener('click', () => {
    const boatChooser = document.getElementById('boatChooser');
    const selectedBoatId = boatChooser.value;
    const selectedBoat = boats.find(boat => boat.id == selectedBoatId);

    if (selectedBoat) {
        let newBoat = { ...selectedBoat, shared: false };
        const boat = {
            title: newBoat.title,
            hpHull: newBoat.hpHull,
            cHpHull: newBoat.hpHull,
            hpSails: newBoat.hpSails,
            cHpSails: newBoat.hpSails,
            hpSteer: newBoat.hpSteer,
            cHpSteer: newBoat.hpSteer,
            str: newBoat.str,
            dex: newBoat.dex,
            cha: newBoat.cha,
            intelligence: newBoat.intelligence,
            wis: newBoat.wis,
            charisma: newBoat.charisma,
            description: newBoat.description,
            sessionId: 0,
            templateBoatId: newBoat.id,
            shared: newBoat.shared,
            ac: newBoat.armorClass,
            crew: newBoat.crew,
            status: ""
        };
        selectedBoats.push(boat);
        renderSelectedBoats();
    }
});

// Render Selected Boats
function renderSelectedBoats() {
    const selectedBoatsList = document.getElementById('selectedBoats');
    selectedBoatsList.innerHTML = '';
    selectedBoats.forEach((boat, index) => {
        const li = document.createElement('li');
        li.textContent = boat.title;

        const checkboxContainer = document.createElement('div');
        checkboxContainer.classList.add('checkbox-container');

        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.checked = boat.shared;
        checkbox.addEventListener('change', () => {
            selectedBoats[index].shared = checkbox.checked;
        });

        checkboxContainer.appendChild(checkbox);
        li.appendChild(checkboxContainer);
        selectedBoatsList.appendChild(li);
    });
}

function newSession(){
    sessionId = null;
    selectedBoats = [];

    renderSelectedBoats();
}

async function delSession() {
    const sessionChooser = document.getElementById('sessionChooser');
    const selectedSessionId = sessionChooser.value;
    const url2 = "/game/"
    if (selectedSessionId.length === 0) {
        return
    }
    try {
        const response = await fetch(url.concat(url2, selectedSessionId), {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json'
            },
        });
        if (response.ok) {
            alert('Game session deleted successfully!');
        } else {
            const errorData = await response.json();
            console.error('Failed to deleted game session:', errorData);
            alert('Failed to delete game session!');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred while deleting the game session.');
    }

    loadGameSessions();
}

async function startGameSession() {
    await saveGameSession(true);
    const url2 = `/game/${sessionId}/start`;
    try {
        const response = await fetch(url.concat(url2), {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json'
            },
        });
        if (response.ok) {
            let startedSession = await response.json();
            sessionStorage.setItem('url', url);
            sessionStorage.setItem('sessionId', sessionId);
            window.location.href = 'StartedGame.html'
        } else {
            const errorData = await response.json();
            console.error('Failed to start game session:', errorData);
            alert('Failed to start game session!');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred while starting the game session.');
    }

}

// Load Selected Game Session
document.getElementById('loadSessionBtn').addEventListener('click', () => {
    const sessionChooser = document.getElementById('sessionChooser');
    const selectedSessionId = sessionChooser.value;
    const url2 = "/game/"
    if (selectedSessionId.length === 0) {
        return
    }
    fetch(url.concat(url2, selectedSessionId))
        .then(response => response.json())
        .then(data => {
            console.log(data);
            sessionId = data.id;
            selectedBoats = data.boats || [];
            renderSelectedBoats();
        });

});

// Initial Load
loadBoats();
loadGameSessions();
