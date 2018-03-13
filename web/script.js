function redirecting(path) {
    window.location.href = path;
}

function selectCities() {
    $.ajax({
        url: "city?action=get",
        type: 'GET',

        success: function (response) {
            for(var i=0; i< response.length; i++){
                var tbody = document.getElementById('tableCity');
                var row = document.createElement("TR");
                var td1 = document.createElement("TD");
                td1.appendChild(document.createTextNode(response[i].id));
                var td2 = document.createElement("TD");
                td2.appendChild (document.createTextNode(response[i].cityName));
                row.appendChild(td1);
                row.appendChild(td2);
                tbody.appendChild(row);
            }
        },
        error: function (response) {
            alert(response);
        }
    });
}

function showFormCity(id_div)
{
    if(document.getElementById(id_div).style.display=="none")
        document.getElementById(id_div).style.display="block";
    else
        document.getElementById(id_div).style.display="none";
}
function addCity(value) {
    $.ajax({
        url: "city?action=add&value=" + value,
        type: 'GET',

        success: function (response) {
            selectCities();
            //удалить строки таблицы
            showFormCity("formCity");
        },
        error: function (response) {
            alert(response);
        }
    });
}