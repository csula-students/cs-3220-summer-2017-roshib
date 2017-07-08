var $ = function (id) {
    return document.getElementById(id);
}
var calculateClick = function () {
    var billAmount = parseFloat($("billAmount").value);
    var percentTip = parseFloat($("percentTip").value);


    if (isNaN(billAmount) || billAmount <= 0) {
        alert("Your bill can't be 0 or less.");
    }

    else if (isNaN(percentTip) || percentTip <= 0) {
        alert("The percentage should be a whole number.");
    }

    else {
        var tip = billAmount * (percentTip / 100);
        var total = billAmount + tip;
        $("tip").value = tip.toFixed(2);
        $("total").value = total.toFixed(2);
    }
}

window.onload = function () {
    $("calculate").onclick = calculateClick;
    $("billAmount").focus();
}