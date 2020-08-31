/**
 * @file overview demoTrack.js
*/

$(document).ready(function () {
    function init() {
        var dataBox = require('track/demo');
        dataBox.initEvents();
        dataBox.getTraceDetail();
    }
    try {
        init();
    } catch (e) {
        setTimeout(function () {
            init();
        }, 1000);
    }

});