const chatBody = document.getElementById('chatBody');
const chatInput = document.getElementById('chatInput');
const sendBtn = document.getElementById('sendBtn');

function addMessage(content, sender) {
    const message = document.createElement('div');
    message.className = `chat-message ${sender}`;
    message.textContent = content;
    chatBody.appendChild(message);
    chatBody.scrollTop = chatBody.scrollHeight; // Auto-scroll to the latest message
}

function showTypingIndicator() {
    const typingIndicator = document.createElement('div');
    typingIndicator.className = 'chat-message typing';
    typingIndicator.id = 'typingIndicator';
    typingIndicator.textContent = 'Processing...';
    chatBody.appendChild(typingIndicator);
    chatBody.scrollTop = chatBody.scrollHeight;
}

function hideTypingIndicator() {
    const typingIndicator = document.getElementById('typingIndicator');
    if (typingIndicator) {
        chatBody.removeChild(typingIndicator);
    }
}

sendBtn.addEventListener('click', () => {
    const userInput = chatInput.value.trim();
    if (userInput) {
        addMessage(userInput, 'user');
        chatInput.value = '';

        // Show typing indicator
        showTypingIndicator();

        // Simulate bot response
        // setTimeout(() => {
        //     hideTypingIndicator();
            
        // }, 1500);

        $.ajax({
            url: '/chatbot',
            method: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({ content : userInput }),
            success: function(data) {
            console.log('Data received:', data);
            hideTypingIndicator();
            addMessage(data, 'bot');
            },
            error: function(error) {
            console.error('Error:', error);
            hideTypingIndicator();
            alert('Service temporary unavailable. Retry later.')
            // addMessage('Error : Service temporary unavailable.', 'bot');
            }
            });

    }
});

chatInput.addEventListener('keydown', (e) => {
    if (e.key === 'Enter') {
        sendBtn.click();
    }
});

addMessage("Hi there! 😊 \n I'm here to help you learn more about me, my skills, and what I can do to help you. Feel free to ask me anything. \n Where would you like to start?",'bot')