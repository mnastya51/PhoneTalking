var selectedRow;
function redirecting(path) {
    window.location.href = path;
}

function selectCities() {
    $.ajax({
        url: "city?action=get",
        type: 'GET',

        success: function (response) {
            var table = document.createElement("table");
            table.id = 'tableCity';
            document.body.appendChild(table);
            var tr = document.createElement("tr");
            var th1 = document.createElement("th");
            th1.appendChild(document.createTextNode("Код"));
            var th2 = document.createElement("th");
            th2.appendChild(document.createTextNode("Город"));
            tr.appendChild(th1);
            tr.appendChild(th2);
            table.appendChild(tr);
            for(var i=0; i< response.length; i++){
                var row = document.createElement("tr");
                var td1 = document.createElement("td");
                td1.appendChild(document.createTextNode(response[i].id));
                var td2 = document.createElement("td");
                td2.appendChild (document.createTextNode(response[i].cityName));
                row.appendChild(td1);
                row.appendChild(td2);
                table.appendChild(row);
                $(row).click(
                    function()    {
                        if(selectedRow ==="")
                            selectedRow = this;
                        else if(selectedRow !== this){
                            $(selectedRow).removeClass('selected');
                            $(this).addClass('selected');
                            selectedRow = this;
                        }
                    }
                   /* function()    {
                        $(this).removeClass('selected');
                    }*/
                );
            }
        },
        error: function (response) {
            alert(response);
        }
    });
}

function deleteTable() {
    var table = document.getElementById("tableCity");
    table.parentNode.removeChild(table);

}
function showFormCity(id_div)
{
    if(document.getElementById(id_div).style.display=="none")
        document.getElementById(id_div).style.display="block";
    else {
        document.getElementById(id_div).style.display = "none";
        if (document.getElementById('cityNameInput').className == 'error') { // сбросить состояние "ошибка", если оно есть
            document.getElementById('cityNameInput').classList.remove('error')
        }
    }
}
function addCity(value) {
    focus(document.getElementById('cityNameInput'));
    if(value !== "") {
        $.ajax({
            url: "city?action=add&value=" + value,
            type: 'GET',

            success: function (response) {
                if (response == 1)
                    alert("Данная запись уже существует!");
                else {
                    document.getElementById('cityNameInput').value = "";
                    deleteTable();
                    selectCities();
                    showFormCity("formCity");
                }
            },
            error: function (response) {
                alert(response);
            }
        });
    }
    else {
        document.getElementById('cityNameInput').classList.add('error');
    }
}

function deleteCity(){
    var id = selectedRow.cells[0].textContent;
    $.ajax({
        url: "city?action=delete&value=" + id,
        type: 'GET',
        success: function (response){
            if(response == 1)
                alert("Данная запись используется в других таблицах!");
            deleteTable();
            selectCities();
        },
        error: function (response) {
            alert(response);
        }
    });
}

function focus(input){
    input.onfocus = function() {
        if (this.className == 'error') { // сбросить состояние "ошибка", если оно есть
            this.classList.remove('error')
        }
    };
}


