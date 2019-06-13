$(document).ready(function() {
    $('.seat').click(function() {
        if(!this.dataset.ticketid) return;
        if($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            if($('.seat.selected').length >= 1) {
                alert('只能选一个座位哦~');
                return;
            }
            $(this).addClass('selected');
        }
    });
});

function fillTickets(fieldName) {
    var selectedSeats = $('.seat.selected');
    var selectedTickets = [];
    if(selectedSeats.length == 0) {
        alert('您还没有选座。');
        return false;
    }
    for(var i = 0; i < selectedSeats.length; i++) {
        selectedTickets.push(selectedSeats[i].dataset.ticketid);
    }
    document.getElementById(fieldName).value = JSON.stringify(selectedTickets);
    return true;
}