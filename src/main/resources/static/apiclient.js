apiclient = (function () {
    let localhost = "http://localhost:8080";
    let despliegue = "https://arsw-weather.herokuapp.com";

    function getWeatherByCity(city_name, callback) {

        const promise = new Promise((resolve, reject) => {
            $.ajax({
                url: despliegue + "/weather/" + city_name,
                type: 'GET',
                contentType: "application/json"
            }).done(function (response) {
                resolve(response);

            }).fail(function (msg) {
                reject(msg);
            });
        });

        promise
            .then(res => {
                callback(res);
            });
    }

    return {
        getWeatherByCity: getWeatherByCity,
    }

})();