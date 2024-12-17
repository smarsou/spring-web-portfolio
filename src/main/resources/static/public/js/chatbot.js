const chatBody = document.getElementById('chatBody');
const chatInput = document.getElementById('chatInput');
const sendBtn = document.getElementById('sendBtn');
const chatHistory = []; 

function addMessage(content, sender) {
    const message = document.createElement('div');
    message.className = `chat-message ${sender}`;
    message.textContent = content;
    chatBody.appendChild(message);
    chatBody.scrollTop = chatBody.scrollHeight; // Auto-scroll to the latest message
    chatHistory.push({'role': sender,'content': content})
}

function retrieveMessages() {
    return chatHistory;
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

        $.ajax({
            url: '/chatbot',
            method: 'POST',
            contentType: "application/json; charset=utf-8",
            // TODO : data has to be a list of dict which contains 'role' and 'content' fields ('role' can be 'user' or 'assistant', and 'content' is the content of the message)
            data: JSON.stringify(this.retrieveMessages()),
            success: function(data) {
                hideTypingIndicator();
                if (data.startsWith("!!ERROR!!")){
                    data = data.replace(/^!!ERROR!!/, '');
                    alert(data);
                }else{
                    addMessage(data, 'assistant');
                }
            },
            error: function(error) {
                hideTypingIndicator();
                alert('Service temporary unavailable. Try again or retry later.')
            }
            });

    }
});

chatInput.addEventListener('keydown', (e) => {
    if (e.key === 'Enter') {
        sendBtn.click();
    }
});

addMessage("Hi there! 😊 \n I'm here to help you learn more about me, my skills, and what I can do to help you. Feel free to ask me anything. \n Where would you like to start?",'assistant')