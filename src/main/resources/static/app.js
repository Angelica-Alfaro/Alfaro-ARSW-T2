var app = (function () {
    function getWeatherByCity(ciudad) {
        var body = $("tbody");
        if (body !== null) {
            body.remove();
        }
        if (ciudad != null && ciudad != "") {
            apiclient.getWeatherByCity(ciudad, actualizarTabla);
        }
    }

    function actualizarTabla(data) {
        var tabla = $("table");
        var body = $("tbody");
        if (body != null) {
            body.remove();
        }
        tabla.append("<tbody>");
        var tblBody = $("tbody");

        var fila = '<tr> <td>' + data.country + '</td> <td>' + data.city + '</td> <td>' + data.weather + '</td> <td>' + data.description + '</td> <td>' + data.temperatura + '</td></tr>';
        tblBody.append(fila);

        tabla.append("</tbody>");
    }

    return {
        getWeatherByCity: getWeatherByCity
    }

})();