var App = (function () {
    var url_base = '';
    return {
        name: 'APP Html5 Framework',
        postRequest: function (vue, url, data, successCallback,failCallback) {
            vue.$http.post(url, JSON.stringify(data), {emulateJSON: true}).then(successCallback).catch(failCallback);
        }
    }
});