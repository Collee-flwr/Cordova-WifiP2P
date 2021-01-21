cordova.define("cordova-plugin-wifiP2P.wifiP2P", function(require, exports, module) {
var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'WifiP2P', 'coolMethod', [arg0]);
};

});
