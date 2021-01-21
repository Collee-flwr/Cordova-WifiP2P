cordova.define('cordova/plugin_list', function(require, exports, module) {
  module.exports = [
    {
      "id": "cordova-plugin-wifiP2P.wifiP2P",
      "file": "plugins/cordova-plugin-wifiP2P/www/wifiP2P.js",
      "pluginId": "cordova-plugin-wifiP2P",
      "clobbers": [
        "cordova.plugins.wifiP2P"
      ]
    }
  ];
  module.exports.metadata = {
    "cordova-plugin-whitelist": "1.3.4",
    "cordova-plugin-wifiP2P": "0.0.1"
  };
});