

exports.setup = function() {

    this.output('dataOut');
};

exports.initialize = function() {


    wifiP2P.discover(function(response){document.querySelector('#message').innerHTML =response;},function(error){console.log(error);});




    this.send('dataOut');
};

