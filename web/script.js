var selectedRow ;
var isEdit = false;
var clickSortMas = [false, false, false, false, false, false];
function redirecting(path) {
    window.location.href = path;
}

function selectCities() {
    $.ajax({
        url: "city?action=get",
        type: 'GET',

        success: function (response) {
            createTableCity(response);
        },
        error: function (response) {
            alert(response);
        }
    });
}

function createTableCity(response) {
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
                if(selectedRow !== this){
                    $(selectedRow).removeClass('selected');
                    $(this).addClass('selected');
                    selectedRow = this;
                }
            }
        );
    }
    $(tr.cells[1]).click(
        function()    {
            if(!clickSortMas[0]) {
                $.ajax({
                    url: "city?action=sort&value=" + "asc",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableCity');
                        createTableCity(responseSort);
                        clickSortMas[0] = true;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
            else{
                $.ajax({
                    url: "city?action=sort&value=" + "desc",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableCity');
                        createTableCity(responseSort);
                        clickSortMas[0] = false;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
        }
    );
}
function deleteTable(table) {
    var table = document.getElementById(table);
    table.parentNode.removeChild(table);

}
function showForm(divId, edit) {
    var form = document.getElementById(divId);
    if ($(form).is(':visible')) {
        $(form).slideUp('slow');
    }
    if ($(form).is(':hidden')) {
        if(!edit) {
            if (divId !== 'formTarif' && divId !== 'formTalking')
                $(form).slideDown('slow');
            else if(divId === 'formTalking'){
                addSelectCitiesAndPhone();
                $(form).slideDown('slow');
            }
            else{
                addSelectCities();
                $(form).slideDown('slow');
            }
        }
        if(selectedRow !== undefined && edit)
            $(form).slideDown('slow');
        if (divId === 'formCity'){
            document.getElementById('cityNameInput').value = "";
            if(document.getElementById('cityNameInput').className == 'input error')
            document.getElementById('cityNameInput').classList.remove('error');
        }
        else if (divId === 'formTarif'){
            document.getElementById('minCost').value = "";
            if(document.getElementById('minCost').className == 'input error')
                document.getElementById('minCost').classList.remove('error');
        }
        else if(divId === 'formAbonent') {
            document.getElementById('abonentPhoneInput').onkeyup = function () {
                document.getElementById('abonentPhoneInput').value = document.getElementById('abonentPhoneInput').value.replace(/\D/, '');
            };
            if(edit)
                editTable(divId);
            else {
                document.getElementById('abonentNameInput').value = "";
                document.getElementById('abonentPhoneInput').value = "";
                document.getElementById('abonentAddressInput').value = "";
                document.getElementById('abonentFacilityInput').checked = false;
            }
            if(document.getElementById('abonentNameInput').className == 'input error')
                document.getElementById('abonentNameInput').classList.remove('error');
            if(document.getElementById('abonentPhoneInput').className == 'input error')
                document.getElementById('abonentPhoneInput').classList.remove('error');
        }
        else {
            if(edit)
                editTable(divId);
            else {
                document.getElementById('dateTalking').value = "2018-03-17";
                document.getElementById('timeTalking').value = "00:00";
                document.getElementById('cost').value = "";
                document.getElementById('min').value = "";
               // document.getElementById("citySelect").options[0].selected=true;
               // document.getElementById("phoneSelect").options[0].selected=true;
            }
            if(document.getElementById('cost').className == 'input error')
                document.getElementById('cost').classList.remove('error');
            if(document.getElementById('min').className == 'input error')
                document.getElementById('min').classList.remove('error');
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
                    deleteTable('tableCity');
                    selectCities();
                    showForm("formCity", false);
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
            deleteTable('tableCity');
            selectCities();
        },
        error: function (response) {
            alert(response);
        }
    });
}

function focus(input){
    input.onfocus = function() {
        if (this.className == 'input error') {
            this.classList.remove('error')
        }
    };
}

function selectAbonent() {
    $.ajax({
        url: "abonent?action=get",
        type: 'GET',
        success: function (response) {
            createTableAbonent(response)
        },
        error: function (response) {
            alert(response);
        }
    });
}

function createTableAbonent(response) {
    var table = document.createElement("table");
    table.id = 'tableAbonent';
    table.style.marginLeft = "18%";
    document.body.appendChild(table);
    var tr = document.createElement("tr");
    var th = document.createElement("th");
    th.appendChild(document.createTextNode("Код"));
    var th1 = document.createElement("th");
    th1.appendChild(document.createTextNode("ФИО"));
    var th2 = document.createElement("th");
    th2.appendChild(document.createTextNode("Телефон"));
    var th3 = document.createElement("th");
    th3.appendChild(document.createTextNode("Адрес"));
    var th4 = document.createElement("th");
    th4.appendChild(document.createTextNode("Льготы"));
    tr.appendChild(th);
    tr.appendChild(th1);
    tr.appendChild(th2);
    tr.appendChild(th3);
    tr.appendChild(th4);
    table.appendChild(tr);
    for(var i=0; i< response.length; i++){
        var row = document.createElement("tr");
        var td1 = document.createElement("td");
        td1.appendChild(document.createTextNode(response[i].id));
        var td2 = document.createElement("td");
        td2.appendChild(document.createTextNode(response[i].fio));
        var td3 = document.createElement("td");
        td3.appendChild (document.createTextNode(response[i].phone));
        var td4 = document.createElement("td");
        td4.appendChild (document.createTextNode(response[i].address));
        var td5 = document.createElement("td");
        if(response[i].facility === false)
            td5.appendChild (document.createTextNode("нет"));
        else
            td5.appendChild (document.createTextNode("да"));
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
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
        );
    }
    $(tr.cells[1]).click(
        function()    {
            if(!clickSortMas[0]) {
                $.ajax({
                    url: "abonent?action=sort&value=" + "asc" + "&field=" + "fio",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableAbonent');
                        createTableAbonent(responseSort);
                        clickSortMas[0] = true;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
            else{
                $.ajax({
                    url: "abonent?action=sort&value=" + "desc" + "&field=" + "fio",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableAbonent');
                        createTableAbonent(responseSort);
                        clickSortMas[0] = false;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
        }
    );
    $(tr.cells[2]).click(
        function()    {
            if(!clickSortMas[1]) {
                $.ajax({
                    url: "abonent?action=sort&value=" + "asc" + "&field=" + "phone",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableAbonent');
                        createTableAbonent(responseSort);
                        clickSortMas[1] = true;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
            else{
                $.ajax({
                    url: "abonent?action=sort&value=" + "desc" + "&field=" + "phone",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableAbonent');
                        createTableAbonent(responseSort);
                        clickSortMas[1] = false;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
        }
    );
    $(tr.cells[3]).click(
        function()    {
            if(!clickSortMas[2]) {
                $.ajax({
                    url: "abonent?action=sort&value=" + "asc" + "&field=" + "address",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableAbonent');
                        createTableAbonent(responseSort);
                        clickSortMas[2] = true;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
            else{
                $.ajax({
                    url: "abonent?action=sort&value=" + "desc" + "&field=" + "address",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableAbonent');
                        createTableAbonent(responseSort);
                        clickSortMas[2] = false;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
        }
    );
    $(tr.cells[4]).click(
        function()    {
            if(!clickSortMas[3]) {
                $.ajax({
                    url: "abonent?action=sort&value=" + "asc" + "&field=" + "facility",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableAbonent');
                        createTableAbonent(responseSort);
                        clickSortMas[3] = true;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
            else{
                $.ajax({
                    url: "abonent?action=sort&value=" + "desc" + "&field=" + "facility",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableAbonent');
                        createTableAbonent(responseSort);
                        clickSortMas[3] = false;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
        }
    );
}

function addAndEditAbonent(fio, phone, address, facility) {
    focus(document.getElementById('abonentNameInput'));
    focus(document.getElementById('abonentPhoneInput'));
    if(facility === true)
        facility = true;
    else facility = false;
    if(fio !== "" && phone !== "") {
        if(phone.length != 11)
            document.getElementById('abonentPhoneInput').classList.add('error');
        else {
            if(!isEdit) {
                $.ajax({
                    url: "abonent?action=add&fio=" + fio + "&phone=" + phone + "&address=" + address + "&facility=" + facility,
                    type: 'GET',

                    success: function (response) {
                        if (response == 1)
                            alert("Данная запись уже существует!");
                        else {
                            document.getElementById('abonentNameInput').value = "";
                            document.getElementById('abonentPhoneInput').value = "";
                            document.getElementById('abonentAddressInput').value = "";
                            document.getElementById('abonentFacilityInput').checked = false;
                            deleteTable('tableAbonent');
                            selectAbonent();
                            showForm("formAbonent", false);
                        }
                    },
                    error: function (response) {
                        alert(response);
                    }
                });
            }
            else{
                isEdit = false;
                var id = selectedRow.cells[0].textContent;
                selectedRow = undefined;
                $.ajax({
                    url: "abonent?action=update&fio=" + fio + "&phone=" + phone + "&address=" + address + "&facility=" + facility + "&id=" + id,
                    type: 'GET',

                    success: function (response) {
                        if (response == 1)
                            alert("Данная запись уже существует!");
                        else {
                            document.getElementById('abonentNameInput').value = "";
                            document.getElementById('abonentPhoneInput').value = "";
                            document.getElementById('abonentAddressInput').value = "";
                            document.getElementById('abonentFacilityInput').checked = false;
                            deleteTable('tableAbonent');
                            selectAbonent();
                            showForm("formAbonent", false);
                        }
                    },
                    error: function (response) {
                        alert(response);
                    }
                });
            }
        }
    }
    else if(fio === "" && phone === ""){
        document.getElementById('abonentNameInput').classList.add('error');
        document.getElementById('abonentPhoneInput').classList.add('error');
    }
    else if(fio === "")
        document.getElementById('abonentNameInput').classList.add('error');
    else if(phone === "")
        document.getElementById('abonentPhoneInput').classList.add('error');
}

function deleteAbonent(){
    var id = selectedRow.cells[0].textContent;
    $.ajax({
        url: "abonent?action=delete&value=" + id,
        type: 'GET',
        success: function (response){
            if(response == 1)
                alert("Данная запись используется в других таблицах!");
            deleteTable('tableAbonent');
            selectAbonent();
        },
        error: function (response) {
            alert(response);
        }
    });
}

function editTable(divId){
    isEdit = true;
    if(divId === 'formAbonent') {
        document.getElementById('titleFormAbonent').textContent = "Изменение абонента";
        document.getElementById('abonentNameInput').value = selectedRow.cells[1].textContent;
        document.getElementById('abonentPhoneInput').value = selectedRow.cells[2].textContent;
        document.getElementById('abonentAddressInput').value = selectedRow.cells[3].textContent;
        if(selectedRow.cells[3].textContent === "да")
            document.getElementById('abonentFacilityInput').checked = true;
        else document.getElementById('abonentFacilityInput').checked = false;
    }
    else{
        document.getElementById('titleFormTalking').textContent = "Изменение разговора";
        addSelectCitiesAndPhone();
        var phone = selectedRow.cells[1].textContent;
        $('#phoneSelect').val(phone);
        var city = selectedRow.cells[2].textContent;
        $("#citySelect").val(city);
        document.getElementById('min').value = selectedRow.cells[3].textContent;
        document.getElementById('dateTalking').value = selectedRow.cells[4].textContent;
        document.getElementById('timeTalking').value = selectedRow.cells[5].textContent;
        document.getElementById('cost').value = selectedRow.cells[6].textContent;
    }
}

function selectTarif() {
    $.ajax({
        url: "tarif?action=get",
        type: 'GET',

        success: function (response) {
            createTableTarif(response);
        },
        error: function (response) {
            alert(response);
        }
    });
}

function createTableTarif(response) {
    var table = document.createElement("table");
    table.id = 'tableTarif';
    document.body.appendChild(table);
    var tr = document.createElement("tr");
    var th2 = document.createElement("th");
    th2.appendChild(document.createTextNode("Город"));
    var th3 = document.createElement("th");
    th3.appendChild(document.createTextNode("Начало периода"));
    var th4 = document.createElement("th");
    th4.appendChild(document.createTextNode("Конец периода"));
    var th5 = document.createElement("th");
    th5.appendChild(document.createTextNode("Цена за минуту"));
    tr.appendChild(th2);
    tr.appendChild(th3);
    tr.appendChild(th4);
    tr.appendChild(th5);
    table.appendChild(tr);
    for(var i=0; i< response.length; i++){
        var row = document.createElement("tr");
        var td1 = document.createElement("td");
        td1.appendChild(document.createTextNode(response[i].cityName));
        var td2 = document.createElement("td");
        td2.appendChild (document.createTextNode(response[i].startPeriod.substring(0,5)));
        var td3 = document.createElement("td");
        td3.appendChild (document.createTextNode(response[i].finishPeriod.substring(0,5)));
        var td4 = document.createElement("td");
        td4.appendChild (document.createTextNode(response[i].cost));
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
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
        );
    }
    $(tr.cells[0]).click(
        function()    {
            if(!clickSortMas[0]) {
                $.ajax({
                    url: "tarif?action=sort&value=" + "asc" + "&field=" + "city",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTarif');
                        createTableTarif(responseSort);
                        clickSortMas[0] = true;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
            else{
                $.ajax({
                    url: "tarif?action=sort&value=" + "desc" + "&field=" + "city",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTarif');
                        createTableTarif(responseSort);
                        clickSortMas[0] = false;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
        }
    );
    $(tr.cells[1]).click(
        function()    {
            if(!clickSortMas[1]) {
                $.ajax({
                    url: "tarif?action=sort&value=" + "asc" + "&field=" + "startPeriod",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTarif');
                        createTableTarif(responseSort);
                        clickSortMas[1] = true;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
            else{
                $.ajax({
                    url: "tarif?action=sort&value=" + "desc" + "&field=" + "startPeriod",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTarif');
                        createTableTarif(responseSort);
                        clickSortMas[1] = false;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
        }
    );
    $(tr.cells[2]).click(
        function()    {
            if(!clickSortMas[2]) {
                $.ajax({
                    url: "tarif?action=sort&value=" + "asc" + "&field=" + "finishPeriod",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTarif');
                        createTableTarif(responseSort);
                        clickSortMas[2] = true;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
            else{
                $.ajax({
                    url: "tarif?action=sort&value=" + "desc" + "&field=" + "finishPeriod",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTarif');
                        createTableTarif(responseSort);
                        clickSortMas[2] = false;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
        }
    );
    $(tr.cells[3]).click(
        function()    {
            if(!clickSortMas[3]) {
                $.ajax({
                    url: "tarif?action=sort&value=" + "asc" + "&field=" + "minCost",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTarif');
                        createTableTarif(responseSort);
                        clickSortMas[3] = true;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
            else{
                $.ajax({
                    url: "tarif?action=sort&value=" + "desc" + "&field=" + "minCost",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTarif');
                        createTableTarif(responseSort);
                        clickSortMas[3] = false;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
        }
    );
}

function addTarif(city, startPeriod, finishPeriod, minCost) {
    focus(document.getElementById('minCost'));
    if(minCost !== "") {
        $.ajax({
            url: "tarif?action=add&city=" + city + "&startPeriod=" + startPeriod + "&finishPeriod=" + finishPeriod + "&minCost=" + minCost,
            type: 'GET',

            success: function (response) {
                if (response == 1)
                    alert("Данная запись уже существует!");
                else {
                    document.getElementById('minCost').value = "";
                    document.getElementById('startPeriod').value = "00:00";
                    document.getElementById('finishPeriod').value = "00:00";
                    deleteTable('tableTarif');
                    selectTarif();
                    showForm("formTarif", false);
                }
            },
            error: function (response) {
                alert(response);
            }
        });
    }
    else {
        document.getElementById('minCost').classList.add('error');
    }
}

function addSelectCities() {
    var cities = [];
    $.ajax({
        url: "tarif?action=getCity",
        type: 'GET',

        success: function (response) {
            var list = $("#citySelect");
            $("#citySelect > option").remove();
            for (var i = 0; i < response.length; i++) {
                list.append('<option>' + response[i].cityName + '</option>');
            }
        },
        error: function (response) {
            alert(response);
        }
    });
    document.getElementById("minCost").onkeyup = function () {
        document.getElementById("minCost").value =  document.getElementById("minCost").value.replace(/\.(?=.*\.)|[^\d\.]/g, '');
    }
}

function deleteTarif(){
    var nameCity = selectedRow.cells[0].textContent;
    var startPeriod = selectedRow.cells[1].textContent;
    var finishPeriod = selectedRow.cells[2].textContent;
    $.ajax({
        url: "tarif?action=delete&nameCity=" + nameCity + "&startPeriod=" + startPeriod + "&finishPeriod=" + finishPeriod,
        type: 'GET',
        success: function (response){
            if(response == 1)
                alert("Данная запись используется в других таблицах!");
            deleteTable('tableTarif');
            selectTarif();
        },
        error: function (response) {
            alert(response);
        }
    });
}

function selectTalking(){
    $.ajax({
        url: "talking?action=get",
        type: 'GET',

        success: function (response) {
            createTableTalking(response);
        },
        error: function (response) {
            alert(response);
        }
    });
}

function createTableTalking(response) {
    var table = document.createElement("table");
    table.id = 'tableTalking';
    document.body.appendChild(table);
    var tr = document.createElement("tr");
    var th1 = document.createElement("th");
    th1.appendChild(document.createTextNode("Код"));
    var th2 = document.createElement("th");
    th2.appendChild(document.createTextNode("Телефон"));
    var th3 = document.createElement("th");
    th3.appendChild(document.createTextNode("Город"));
    var th4 = document.createElement("th");
    th4.appendChild(document.createTextNode("Количество минут"));
    var th5 = document.createElement("th");
    th5.appendChild(document.createTextNode("Дата"));
    var th6 = document.createElement("th");
    th6.appendChild(document.createTextNode("Время"));
    var th7 = document.createElement("th");
    th7.appendChild(document.createTextNode("Цена"));
    tr.appendChild(th1);
    tr.appendChild(th2);
    tr.appendChild(th3);
    tr.appendChild(th4);
    tr.appendChild(th5);
    tr.appendChild(th6);
    tr.appendChild(th7);
    table.appendChild(tr);
    for(var i=0; i< response.length; i++){
        var row = document.createElement("tr");
        var td1 = document.createElement("td");
        td1.appendChild(document.createTextNode(response[i].talkId));
        var td2 = document.createElement("td");
        td2.appendChild(document.createTextNode(response[i].phoneAbonent));
        var td3 = document.createElement("td");
        td3.appendChild(document.createTextNode(response[i].cityName));
        var td4 = document.createElement("td");
        td4.appendChild(document.createTextNode(response[i].minCount));
        var td5 = document.createElement("td");
        td5.appendChild(document.createTextNode(response[i].talkDate));
        var td6 = document.createElement("td");
        td6.appendChild (document.createTextNode(response[i].talkTime.substring(0,5)));
        var td7 = document.createElement("td");
        td7.appendChild (document.createTextNode(response[i].talkCost));
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);
        row.appendChild(td7);
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
        );
    }
    $(tr.cells[1]).click(
        function()    {
            if(!clickSortMas[0]) {
                $.ajax({
                    url: "talking?action=sort&value=" + "asc" + "&field=" + "phone",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTalking');
                        createTableTalking(responseSort);
                        clickSortMas[0] = true;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
            else{
                $.ajax({
                    url: "talking?action=sort&value=" + "desc" + "&field=" + "phone",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTalking');
                        createTableTalking(responseSort);
                        clickSortMas[0] = false;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
        }
    );
    $(tr.cells[2]).click(
        function()    {
            if(!clickSortMas[1]) {
                $.ajax({
                    url: "talking?action=sort&value=" + "asc" + "&field=" + "city",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTalking');
                        createTableTalking(responseSort);
                        clickSortMas[1] = true;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
            else{
                $.ajax({
                    url: "talking?action=sort&value=" + "desc" + "&field=" + "city",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTalking');
                        createTableTalking(responseSort);
                        clickSortMas[1] = false;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
        }
    );
    $(tr.cells[3]).click(
        function()    {
            if(!clickSortMas[2]) {
                $.ajax({
                    url: "talking?action=sort&value=" + "asc" + "&field=" + "min",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTalking');
                        createTableTalking(responseSort);
                        clickSortMas[2] = true;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
            else{
                $.ajax({
                    url: "talking?action=sort&value=" + "desc" + "&field=" + "min",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTalking');
                        createTableTalking(responseSort);
                        clickSortMas[2] = false;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
        }
    );
    $(tr.cells[4]).click(
        function()    {
            if(!clickSortMas[3]) {
                $.ajax({
                    url: "talking?action=sort&value=" + "asc" + "&field=" + "date",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTalking');
                        createTableTalking(responseSort);
                        clickSortMas[3] = true;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
            else{
                $.ajax({
                    url: "talking?action=sort&value=" + "desc" + "&field=" + "date",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTalking');
                        createTableTalking(responseSort);
                        clickSortMas[3] = false;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
        }
    );
    $(tr.cells[5]).click(
        function()    {
            if(!clickSortMas[4]) {
                $.ajax({
                    url: "talking?action=sort&value=" + "asc" + "&field=" + "time",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTalking');
                        createTableTalking(responseSort);
                        clickSortMas[4] = true;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
            else{
                $.ajax({
                    url: "talking?action=sort&value=" + "desc" + "&field=" + "time",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTalking');
                        createTableTalking(responseSort);
                        clickSortMas[4] = false;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
        }
    );
    $(tr.cells[6]).click(
        function()    {
            if(!clickSortMas[5]) {
                $.ajax({
                    url: "talking?action=sort&value=" + "asc" + "&field=" + "cost",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTalking');
                        createTableTalking(responseSort);
                        clickSortMas[5] = true;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
            else{
                $.ajax({
                    url: "talking?action=sort&value=" + "desc" + "&field=" + "cost",
                    type: 'GET',

                    success: function (responseSort) {
                        deleteTable('tableTalking');
                        createTableTalking(responseSort);
                        clickSortMas[5] = false;
                    },
                    error: function (responseSort) {
                        alert(responseSort);
                    }
                });
            }
        }
    );
}

function addSelectCitiesAndPhone() {
    var cities = [];
    var phone = [];
    $.ajax({
        url: "talking?action=getCity",
        type: 'GET',

        success: function (response) {
            var list = $("#citySelect");
            $("#citySelect > option").remove();
            for (var i = 0; i < response.length; i++) {
                list.append('<option>' + response[i].cityName + '</option>');
            }
        },
        error: function (response) {
            alert(response);
        }
    });
    $.ajax({
        url: "talking?action=getPhone",
        async: false,
        type: 'GET',

        success: function (response) {
            var list = $("#phoneSelect");
            $("#phoneSelect > option").remove();
            for (var i = 0; i < response.length; i++) {
                list.append('<option>' + response[i].phone + '</option>');
            }
        },
        error: function (response) {
            alert(response);
        }
    });
    document.getElementById("cost").onkeyup = function () {
        document.getElementById("cost").value =  document.getElementById("cost").value.replace(/\.(?=.*\.)|[^\d\.]/g, '');
    }
    document.getElementById("min").onkeyup = function () {
        document.getElementById("min").value =  document.getElementById("min").value.replace(/[^0-9]/, '');
    }
}

function addTalking(phone, city, min, date, time, cost) {
    focus(document.getElementById('cost'));
    focus(document.getElementById('min'));
    if(min !== "" && cost !== "") {
        if (!isEdit) {
            $.ajax({
                url: "talking?action=add&phone=" + phone + "&city=" + city + "&min=" + min + "&date=" + date + "&time=" + time + "&cost=" + cost,
                type: 'GET',

                success: function (response) {
                    if (response == 1)
                        alert("Данная запись уже существует!");
                    else {
                        document.getElementById('dateTalking').value = "2018-03-17";
                        document.getElementById('timeTalking').value = "00:00";
                        document.getElementById('cost').value = "";
                        document.getElementById('min').value = "";
                        deleteTable('tableTalking');
                        selectTalking();
                        showForm("formTalking", false);
                    }
                },
                error: function (response) {
                    alert(response);
                }
            });
        }
        else {
            isEdit = false;
            var id = selectedRow.cells[0].textContent;
            selectedRow = undefined;
            $.ajax({
                url: "talking?action=update&phone=" + phone + "&city=" + city + "&min=" + min + "&date=" + date + "&time=" + time + "&cost=" + cost + "&id=" + id,
                type: 'GET',

                success: function (response) {
                    if (response == 1)
                        alert("Данная запись уже существует!");
                    else {
                        document.getElementById('dateTalking').value = "2018-03-17";
                        document.getElementById('timeTalking').value = "00:00";
                        document.getElementById('cost').value = "";
                        document.getElementById('min').value = "";
                        deleteTable('tableTalking');
                        selectTalking();
                        showForm("formTalking", false);
                    }
                },
                error: function (response) {
                    alert(response);
                }
            });
        }
    }
    if(cost === "" && min === ""){
        document.getElementById('cost').classList.add('error');
        document.getElementById('min').classList.add('error');
    }
    else if(cost === "")
        document.getElementById('cost').classList.add('error');
    else if(min === "")
        document.getElementById('min').classList.add('error');
}

function deleteTalking(){
    var id = selectedRow.cells[0].textContent;
    $.ajax({
        url: "talking?action=delete&value=" + id,
        type: 'GET',
        success: function (response){
            if(response == 1)
                alert("Данная запись используется в других таблицах!");
            deleteTable('tableTalking');
            selectTalking();
        },
        error: function (response) {
            alert(response);
        }
    });
}

function filtrCity(city) {
    $.ajax({
        url: "city?action=filtr&value=" + city,
        type: 'GET',

        success: function (response) {
            deleteTable('tableCity');
            createTableCity(response);
        },
        error: function (response) {
            alert(response);
        }
    });
}

function filtrAbonent(fio, phone, address, facility){
    $.ajax({
        url: "abonent?action=filtr&fio=" + fio + "&phone=" + phone + "&address=" + address + "&facility=" + facility,
        type: 'GET',

        success: function (response) {
                deleteTable('tableAbonent');
                createTableAbonent(response);
        },
        error: function (response) {
            alert(response);
        }
    });
}