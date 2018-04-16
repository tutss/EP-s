// Colors
var numberColors = 9;
var colors = generateColors(numberColors);

// Variables
var squares = document.querySelectorAll(".square");
var display = document.getElementById("display");
var message = document.getElementById("message");
var h1 = document.querySelector("h1");

// Buttons
var buttonsDifficulty = document.querySelectorAll(".mode");
for (var i = 0; i < buttonsDifficulty.length; i++) {
  buttonsDifficulty[i].addEventListener("click", function(){
    buttonsDifficulty[0].classList.remove("difficulty");
    buttonsDifficulty[1].classList.remove("difficulty");
    buttonsDifficulty[2].classList.remove("difficulty");
    this.classList.add("difficulty");

    if (this.textContent === "Easy") {
      numberColors = 3;
      update(numberColors, true);
    }
    if (this.textContent === "Medium") {
      numberColors = 6;
      update(numberColors, true);
    }
    if (this.textContent === "Hard") {
      numberColors = 9;
      update(numberColors, false);
    }
    // this.textContent === "Easy" ? numberColors = 3: ;
    // this.textContent === "Easy" ? numberColors = 3: ;
    // this.textContent === "Easy" ? numberColors = 3: ;
    // this.textContent === "Easy" ? update(numberColors, true): update(numberColors, false);

    resetGame();
  });
}

// Reset game
var resetButton = document.querySelector("#reset");
resetButton.addEventListener("click", resetGame);

// pick a color
var picked = pickColor();
display.textContent = picked;

for (var i = 0; i < squares.length; i++) {
  // Pick random color
  squares[i].style.backgroundColor = colors[i];
  // Click listener
  squares[i].addEventListener("click", function() {
    // grab color
    var clickedColor = this.style.backgroundColor;
    // compare color
    if (clickedColor === picked) {
      message.textContent = "Correct!";
      resetButton.textContent = "Play again?";
      matchAll(clickedColor);
      h1.style.backgroundColor = clickedColor;
    }
    else {
      this.style.backgroundColor = "#232323";
      message.textContent = "Wrong!";
    }
  });
}

// Functions

// Update Game
function update(num, bool) {
  colors = generateColors(num);
  picked = pickColor();
  display.textContent = picked;
  for (var i = 0; i < squares.length; i++) {
    if (bool) {
      if (colors[i]) {
        squares[i].style.backgroundColor = colors[i];
      } else {
        squares[i].style.display = "none";
      }
    }
    else {
      squares[i].style.backgroundColor = colors[i];
      squares[i].style.display = "block";
    }
  }
}

// Reset the game
function resetGame() {
  // Reset things
  resetButton.textContent = "New Colors";
  h1.style.backgroundColor = "steelblue";
  message.textContent = "";
  // generate new Colors
  colors = generateColors(numberColors);
  // pick a new colors
  picked = pickColor();
  // change display
  display.textContent = picked;
  // change the colors
  for (var i = 0; i < squares.length; i++) {
    squares[i].style.backgroundColor = colors[i];
  }
}

// make an array of num  random colors
function generateColors(num) {
  array = [];
  for (var i = 0; i < num; i++) {
    array.push(randomizeColor());
  }
  return array;
}

// Create random colors to fill an array
function randomizeColor() {
  // pick one red, one green and one blue
  var red = Math.floor(Math.random() * 256);
  var green = Math.floor(Math.random() * 256);
  var blue = Math.floor(Math.random() * 256);
  return "rgb(" + red + ", " + green + ", " + blue + ")";
}

// Change box colors to the matched one
function matchAll(color) {
  for (var i = 0; i < squares.length; i++) {
    squares[i].style.backgroundColor = color;
  }
}

// Pick a random color from an array
function pickColor() {
  var randomColor = Math.floor(Math.random() * colors.length);
  return colors[randomColor];
}
