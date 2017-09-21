var App = (function () {
    var url_base ="http://localhost:8080/glass/deal/main";
    return {
        postRequest: function (vue, data, successCallback,failCallback) {
            vue.$http.post(url_base, data).then(successCallback).catch(failCallback);
        }
    }
});