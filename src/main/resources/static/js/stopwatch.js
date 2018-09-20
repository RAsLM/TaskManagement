var taskListInterne;
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

function getTable() {
    $.ajax({
        type: "GET",
        url: '/getTable',
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
            drawTable();
        }
    });
}

function start(id){
    $.ajax({
        type: "POST",
        url: '/workLog/api/start/' + id,
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
        url: '/workLog/api/stop/' + id,
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
    time = parseInt($("#SW").val()); // просто задаем значение счетчику
}

$( document ).ready( // запускается при загрузке документа
    function(){
        setInterval(function() { // циклично выполняет функцию с промежутком
            if(typeof taskListInterne !== 'undefined') {
                for (var i = 0; i < taskListInterne.length; i++) {
                    if (taskListInterne[i].inProcess) {
                        taskListInterne[i].spentTime = taskListInterne[i].spentTime + 1;
                    }

                    time = taskListInterne[i].spentTime; // просто наращиваем счетчик
                    var sec, min, hour;

                    sec = Math.abs(Math.floor(time % 60));
                    min = sec > 0 ? Math.abs(Math.floor((time / 60) % 60)) : 0;
                    hour = time > 0 ? Math.abs(Math.floor((time / 60 / 60 / 60) % 24)) : 0;

                    if (sec.toString().length == 1) sec = '0' + sec;
                    if (min.toString().length == 1) min = '0' + min;
                    if (hour.toString().length == 1) hour = '0' + hour;

                    $("#hour_" + taskListInterne[i].id).html(hour);
                    $("#min_" + taskListInterne[i].id).html(min);
                    $("#sec_" + taskListInterne[i].id).html(sec);
                }
            }
        }, 1000) // значение промежутка в милисекундах (1 сек)
    }
);


function drawTable() {

    var $tasks = $("#tasks tdbody")
    var $row = $("tr#id-")

    while(tasks.rows.length > 1) {
        tasks.deleteRow(1);
    }



    for (var i = 0; i < taskListInterne.length; i++) {
        var row = tasks.insertRow(1);
        var cellId = row.insertCell(0);
        var cellName = row.insertCell(1);
        var cellDescription = row.insertCell(2);
        var cellTeg = row.insertCell(3);
        var cellParentTask = row.insertCell(4);
        var cellUser = row.insertCell(5);
        var cellCondition = row.insertCell(6);
        var cellStatus = row.insertCell(7);
        var cellAction = row.insertCell(8);
        var cellSpentTime = row.insertCell(9);
        var cellSW = row.insertCell(10);

        cellId.innerHTML = taskListInterne[i].id;
        cellName.innerHTML = taskListInterne[i].name;
        cellDescription.innerHTML = taskListInterne[i].description;
        cellTeg.innerHTML = taskListInterne[i].tag.name;
        cellParentTask.innerHTML = "";//taskListInterne[i].parentTask.name;
        cellUser.innerHTML = taskListInterne[i].user.name;
        cellCondition.innerHTML = taskListInterne[i].inProcess;
        cellStatus.innerHTML = taskListInterne[i].status.name;
        cellAction.innerHTML = '<button onclick=\"start(' + taskListInterne[i].id +');\" >start</button> <button onclick=\"stop(' + taskListInterne[i].id +');" >stop</button>';
        cellSpentTime.innerHTML = taskListInterne[i].spentTime;

        cellId.innerHTML = taskListInterne[i].id;
        cellSW.innerHTML = '\t<p style="display:inline" id="hour_' + taskListInterne[i].id +'" class="sw"></p>' +
            '\t<p style="display:inline" id="min_' + taskListInterne[i].id +'" class="sw"></p>\n' +
            '\t<p style="display:inline" id="sec_' + taskListInterne[i].id +'" clsss="sw"></p>\n';
    }
}