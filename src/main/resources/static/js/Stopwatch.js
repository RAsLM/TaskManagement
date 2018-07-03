var body = document.body;
var start = $("#start");
var stop = $("#stop");
var reset = $("#reset");

var mil = $("#milis");
var sec = $("#secs");
var min = $("#mins");
var hours = $("#hours");
var flag = false;

function displayStopButton() {
    //http://api.jquery.com/css/
    start.css("display", "none");
    stop.css("display", "block");
}

function displayStartButton() {
    start.css("display", "block");
    stop.css("display", "none");
}

// Get Date start point
function startStopwatch() {
    if (!flag) initialDate = new Date;
}

function getTime() {

    var currentDate = new Date;
    timer = new Date(currentDate - initialDate);

    milliseconds = timer.getMilliseconds();
    seconds = timer.getSeconds();
    minutes = timer.getMinutes();
    hours = timer.getUTCHours();
}


// display timer in document
function counter() {
    getTime();
    //http://api.jquery.com/html/
    mil.text(milliseconds);
    sec.text(seconds);
    min.innerHTML = minutes;
    hours.innerHTML = hours;
}

// interval for display
function displayTimer() {
    timerId = setInterval(counter, 10);
}


function stopTimer() {
    clearInterval(timerId);
    getTime();
    //createTimeBlock('STOP');
    flag = true;
}

function resetTimer() {
    flag = false;
    clearInterval(timerId);
    start.css("display", "block")
    //start.style.display = 'block';
    stop.css("display", "none")
    //stop.style.display = 'none';
    mil.innerHTML = '00';
    min.innerHTML = '00';
    sec.innerHTML = '00';
}