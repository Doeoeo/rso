const url = "http://localhost:8080";

async function joinGame() {
    const gameCode = document.querySelector('.join-input').value;
    const url2 = `/game/joinGame/` + gameCode;
    try {
        const response = await fetch(url.concat(url2), {
            method: "GET",
            headers: {
                'Content-Type': 'application/json'
            },

        });
        if (response.ok) {
            gameSession = await response.json();
            sessionStorage.setItem('url', url);
            sessionStorage.setItem('sessionId', gameSession.id);
            window.location.href = 'JoinedGame.html'


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