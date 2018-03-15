var selectedRow ;
var isEdit = false;
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
                );
            }
        },
        error: function (response) {
            alert(response);
        }
    });
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
        if(!edit)
            $(form).slideDown('slow');
        if(selectedRow !== undefined && edit)
            $(form).slideDown('slow');
        if (divId === 'formCity'){
            document.getElementById('cityNameInput').value = "";
            if(document.getElementById('cityNameInput').className == 'input error')
            document.getElementById('cityNameInput').classList.remove('error');
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
        },
        error: function (response) {
            alert(response);
        }
    });
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
}
