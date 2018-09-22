var taskListInternal;
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
        success: function (taskListExternal) {
            taskListInternal = taskListExternal;
            drawTable();
        }
    });
}

function start(id, i){
    $.ajax({
        type: "POST",
        url: '/workLog/api/start/' + id,
        data: JSON.stringify(taskListInternal[i].spentTime),
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
        success: function(taskSpentTime) {
            taskListInternal[i].inProcess = taskSpentTime.inProcess;
            taskListInternal[i].spentTime = taskSpentTime.spentTime;
            drawTable();
        },error: function (e) {
            alert(e);
        }
    });
}

function stop(id, i){
    $.ajax({
        type: "POST",
        url: '/workLog/api/stop/' + id,
        data: JSON.stringify(taskListInternal[i].spentTime),
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
        success: function (taskSpentTime) {
            taskListInternal[i].inProcess = taskSpentTime.inProcess;
            taskListInternal[i].spentTime = taskSpentTime.spentTime;
            drawTable();
        },error: function (e) {
            alert(e);
        }

    });
}

$( document ).ready( // запускается при загрузке документа
    function(){
        setInterval(function() { // циклично выполняет функцию с промежутком
            if(typeof taskListInternal !== 'undefined') {
                for (var i = 0; i < taskListInternal.length; i++) {
                    if (taskListInternal[i].inProcess) {
                        taskListInternal[i].spentTime = taskListInternal[i].spentTime + 1;
                    }

                    time = taskListInternal[i].spentTime; // просто наращиваем счетчик
                    var sec, min, hour;

                    sec = Math.abs(Math.floor(time % 60));
                    min = sec > 0 ? Math.abs(Math.floor((time / 60) % 60)) : 0;
                    hour = time > 0 ? Math.abs(Math.floor((time / 60 / 60 / 60) % 24)) : 0;

                    if (sec.toString().length == 1) sec = '0' + sec;
                    if (min.toString().length == 1) min = '0' + min;
                    if (hour.toString().length == 1) hour = '0' + hour;

                    $("#hour_" + taskListInternal[i].id).html(hour);
                    $("#min_" + taskListInternal[i].id).html(min);
                    $("#sec_" + taskListInternal[i].id).html(sec);
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



    for (var i = 0; i < taskListInternal.length; i++) {
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

        cellId.innerHTML = i+1;
        cellName.innerHTML = taskListInternal[i].name;
        cellDescription.innerHTML = taskListInternal[i].description;
        cellTeg.innerHTML = taskListInternal[i].tag.name;
        cellParentTask.innerHTML = "";//taskListInterne[i].parentTask.name;
        cellUser.innerHTML = taskListInternal[i].user.name;
        cellCondition.innerHTML = taskListInternal[i].inProcess;
        cellStatus.innerHTML = taskListInternal[i].status.name;
        if (taskListInternal[i].inProcess == false){
            cellAction.innerHTML = '<button type="button" class="btn btn-primary" onclick=\"start((' + taskListInternal[i].id +'), (' + i +'));\" >start</button>';
        } else{
            cellAction.innerHTML = '<button type="button" class="btn btn-primary" onclick=\"stop((' + taskListInternal[i].id +'), (' + i +'));" >stop</button type="button" class="btn btn-primary>';
        }
        cellSpentTime.innerHTML = taskListInternal[i].spentTime;

        cellSW.innerHTML = '\t<p style="display:inline" id="hour_' + taskListInternal[i].id +'" class="sw"></p>' +
            '\t<p style="display:inline" id="min_' + taskListInternal[i].id +'" class="sw"></p>\n' +
            '\t<p style="display:inline" id="sec_' + taskListInternal[i].id +'" clsss="sw"></p>\n';
    }
}