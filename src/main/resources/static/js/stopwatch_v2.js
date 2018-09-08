var taskListInterne;
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

/*function add() {
    $.ajax({
        url: 'stopwatch/api/add',
        success: function(stopwatchList) {
            swList = stopwatchList;
            drawTable();
        }
    });
}

function remove() {
    $.ajax({
        type: post,
        dataType: 'json',
        url: 'stopwatch/api/remove',
        beforeSend: function(xhr) {
            if (header && token) {
                xhr.setRequestHeader(header, token);
            }
        },
        success: function() {
            drawTable();
        }
    });
}*/

function start(id){
    $.ajax({
        type: "POST",
        url: 'stopwatch/api/start/' + id,
        data: JSON.stringify(taskListInterne),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: 'json',
        cache: false,
        timeout: 600000,
        beforeSend: function(xhr) {
            if (header && token) {
                xhr.setRequestHeader(header, token);
            }
        },
        success: function(taskListExterne) {
            taskListInterne = taskListExterne;
            drawTable();
        },error: function (e) {
            alert(e);
        }
    });
}

function stop(id){
    $.ajax({
        type: "POST",
        url: 'stopwatch/api/stop/' + id,
        data: JSON.stringify(taskListInterne),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: 'json',
        cache: false,
        timeout: 600000,
        beforeSend: function(xhr) {
            if (header && token) {
                xhr.setRequestHeader(header, token);
            }
        },
        success: function (taskListExterne) {
            taskListInterne = taskListExterne;
        },error: function (e) {
            alert(e);
        }

    });
}

function setTime(){
    time = parseInt($("#time").val()); // просто задаем значение счетчику
}

$( document ).ready( // запускается при загрузке документа
    function(){
        setInterval(function() { // циклично выполняет функцию с промежутком
            if(typeof swList !== 'undefined') {
                for (var i = 0; i < swList.length; i++) {
                    if (swList[i].started) {
                        swList[i].time = swList[i].time + 1;
                    }

                    time = swList[i].time; // просто наращиваем счетчик
                    var sec, min, hour;

                    sec = Math.abs(Math.floor(time % 60));
                    min = sec > 0 ? Math.abs(Math.floor((time / 60) % 60)) : 0;
                    hour = time > 0 ? Math.abs(Math.floor((time / 60 / 60 / 60) % 24)) : 0;

                    if (sec.toString().length == 1) sec = '0' + sec;
                    if (min.toString().length == 1) min = '0' + min;
                    if (hour.toString().length == 1) hour = '0' + hour;

                    $("#hour_" + swList[i].id).html(hour);
                    $("#min_" + swList[i].id).html(min);
                    $("#sec_" + swList[i].id).html(sec);
                }
            }
        }, 1000) // значение промежутка в милисекундах (1 сек)
    }
);


function drawTable() {

    var $tasks = $("#tasks tdbody")
    var $row = $("tr#id-")

    while($tasks.rows.length > 1) {
        $tasks.deleteRow(1);
    }



    for (var i = 0; i < taskListInterne.length; i++) {
        var row = swTable.insertRow(1);
        var cellId = row.insertCell(0);
        var cellTime = row.insertCell(1);
        var cellActions = row.insertCell(2);

        cellId.innerHTML = swList[i].id;
        cellTime.innerHTML = '\t<p style="display:inline" id="hour_' + swList[i].id +'" class="sw"></p>' +
            '\t<p style="display:inline" id="min_' + swList[i].id +'" class="sw"></p>\n' +
            '\t<p style="display:inline" id="sec_' + swList[i].id +'" clsss="sw"></p>\n';


        cellActions.innerHTML = '<button onclick="start(' + swList[i].id +');" >start</button> <button onclick="stop(' + swList[i].id +');" >stop</button>';
    }
}