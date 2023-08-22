const suits = ["♠", "♥", "♦", "♣"];
const ranks = ["A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"];

const drawButton = document.getElementById("drawButton");
const hand1 = document.getElementById("hand1");
const hand2 = document.getElementById("hand2");

let currentPlayer = 1;
const playerHands = [[], []];
const deck = createDeck();
shuffleDeck(deck);

function createDeck() {
  const deck = [];
  for (const suit of suits) {
    for (const rank of ranks) {
      deck.push({ suit, rank });
    }
  }
  return deck;
}

function shuffleDeck(deck) {
  for (let i = deck.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [deck[i], deck[j]] = [deck[j], deck[i]];
  }
}

function drawCard(deck) {
  return deck.pop();
}

function displayHand(hand, handElement) {
  handElement.innerHTML = "";
  for (const card of hand) {
    const cardElement = document.createElement("div");
    cardElement.classList.add("card");
    cardElement.textContent = `${card.rank}${card.suit}`;
    handElement.appendChild(cardElement);
  }
}

function switchPlayer() {
  currentPlayer = currentPlayer === 1 ? 0 : 1;
}

drawButton.addEventListener("click", () => {
  if (deck.length > 0) {
    const drawnCard = drawCard(deck);
    playerHands[currentPlayer].push(drawnCard);
    displayHand(playerHands[currentPlayer], currentPlayer === 0 ? hand1 : hand2);
    switchPlayer();
  }
});

// Initial deal
displayHand(playerHands[0], hand1);
displayHand(playerHands[1], hand2);
