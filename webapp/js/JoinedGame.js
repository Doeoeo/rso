const url = sessionStorage.getItem('url');
const sessionId = sessionStorage.getItem('sessionId');
let gameSession = null;
let lastIndex = 0;
console.log(url);

let websocket = null;
document.addEventListener("click", () => {
    if (!websocket && gameSession) {
        createWebSocket();
    }
});

function createWebSocket() {
    websocket = new WebSocket("ws://localhost:8080/session?key=" + gameSession.joinCode);
    websocket.onopen = function(evt) { onOpen(evt) };
    websocket.onmessage = function(evt) { onMessage(evt) };
    websocket.onerror = function(evt) { onError(evt) };
    websocket.onclose = function (evt){onClose(evt)};
}

function onOpen() {
    console.log("Opened");
}

function onMessage(event) {
    const updatedBoat = JSON.parse(event.data);
    updateBoatById(updatedBoat.id, updatedBoat);
    renderBoats();
    showBoatDetails(lastIndex);
}

// Function to update a boat's property by ID
function updateBoatById(boatId, newBoat) {
    // Find the boat by ID
    let boat = gameSession.boats.find(b => b.id === boatId);
    if (boat) {
        // Update the specified field
        boat.cHpSteer = newBoat.cHpSteer;
        boat.cHpHull = newBoat.cHpHull;
        boat.cHpSails = newBoat.cHpSails;

        console.log(`Boat with ID ${boatId} updated:`, boat);
    } else {
        console.log(`Boat with ID ${boatId} not found.`);
    }
}

function onError(event) {
    console.log("onError: " + event);
}

function  onClose(evt) {
    websocket = null;
}

function renderBoats() {

    const boatList = document.getElementById('boatList');
    boatList.innerHTML = '';
    gameSession.boats.forEach((boat, index) => {
        if (boat.shared){
            const li = document.createElement('li');
            li.innerHTML = `
                <span>${boat.title}</span>
                <span>AC: ${boat.ac} | Hull: ${boat.cHpHull}/${boat.hpHull}</span>
            `;
            li.addEventListener('click', () => showBoatDetails(index));
            boatList.appendChild(li);
        }
    });
}

function showBoatDetails(index) {
    lastIndex = index;
    const boat = gameSession.boats[index];
    const boatDetails = document.getElementById('boatDetails');
    console.log(boat);

    boatDetails.innerHTML = `
        <h3>${boat.title}</h3>
        <div class="Stats">
            <table class="stats-table">
                <thead>
                    <tr>
                        <th>STR</th>
                        <th>DEX</th>
                        <th>CON</th>
                        <th>INT</th>
                        <th>WIS</th>
                        <th>CHA</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${boat.str} <span class="modifier">(+3)</span></td>
                        <td>${boat.dex} <span class="modifier">(-2)</span></td>
                        <td>${boat.cha} <span class="modifier">(+1)</span></td>
                        <td>${boat.intelligence}</td>
                        <td>${boat.wis}</td>
                        <td>${boat.charisma}</td>
                    </tr>
                </tbody>
            </table>               
        </div>
        <p>Armor Class: ${boat.ac}</p>
        <div class="hp-bar">
            <span>Hull HP:</span>
            <input type="number" value="${boat.cHpHull}" onchange="updateHP(${index}, 'cHpHull', this.value)" /> &nbsp;/ ${boat.hpHull}
        </div>
        <div class="hp-bar">
            <span>Sails HP:</span>
            <input type="number" value="${boat.cHpSails}" onchange="updateHP(${index}, 'cHpSails', this.value)" /> &nbsp;/ ${boat.hpSails}
        </div>
        <div class="hp-bar">
            <span>Steer HP:</span>
            <input type="number" value="${boat.cHpSteer}" onchange="updateHP(${index}, 'cHpSteer', this.value)" /> &nbsp;/ ${boat.hpSteer}
        </div>
        <div class="desc">
            <span>${boat.description}</span>
        </div>
    `;
}

async function updateHP(index, type, value) {
    //console.log(index, value, type, websocket);
    gameSession.boats[index][type].current = parseInt(value, 10);
    const message = JSON.stringify({
        boatId: gameSession.boats[index].id,
        field: type,
        newValue: value
    });
    const boat = await test(message)
    websocket.send(JSON.stringify(boat));
    renderBoats();
}
async function test(txt) {
    const url2 = `/game/updateBoat`;
    try {
        const response = await fetch(url.concat(url2), {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json'
            },
            body: txt
        });
        if (response.ok) {
            return await response.json();
        } else {
            const errorData = await response.json();
            console.error('Failed to update boat:', errorData);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}
async function loadGameSession() {
    const url2 = `/game/${sessionId}`;
    try {
        const response = await fetch(url.concat(url2), {
            method: "GET",
            headers: {
                'Content-Type': 'application/json'
            },

        });
        if (response.ok) {
            gameSession = await response.json();
            document.getElementById('code').innerText = gameSession.joinCode;
            createWebSocket();
            renderBoats();

        } else {
            const errorData = await response.json();
            console.error('Failed to start game session:', errorData);
            //alert('Failed to start game session!');
        }
    } catch (error) {
        console.error('Error:', error);
        //alert('An error occurred while starting the game session.');
    }
}
// Initial render
loadGameSession();
