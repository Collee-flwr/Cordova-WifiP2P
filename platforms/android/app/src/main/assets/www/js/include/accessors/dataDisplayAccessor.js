
exports.setup = function() {

    // Establish accessor input
    this.input('dataIn');

};

exports.initialize = function() {

    // Upon receiving input, trigger the display function
    this.addInputHandler("dataIn", display.bind(this));

};

function display(){



}
