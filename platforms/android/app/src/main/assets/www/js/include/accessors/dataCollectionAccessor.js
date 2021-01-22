

exports.setup = function() {

    this.output('dataOut');
};

exports.initialize = function() {


    wifiP2P.discover(function(response){
                var size = document.createTextNode(response);
                document.querySelector('#message').appendChild(size);
                },function(error){console.log(error);});




    this.send('dataOut');
};

