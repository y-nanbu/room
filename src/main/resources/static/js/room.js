$(function(){
  var _CONNECT = 'CONNECT';
  var _DISCONNECT = 'DISCONNECT';

  var ws = new WebSocket("ws://" + location.host + "/ws");
  ws.onopen = function() {
    console.log("on open");
  };
  ws.onclose = function() {
    console.log("on close");
  };
  ws.onmessage = function(message) {
    var msg = JSON.parse(message.data);

    if (msg.category === _CONNECT) {
      var $connectedUsers = $('#connected-users');
      $connectedUsers.append($('<li/>').text(msg.data)
          .addClass("list-group-item").attr("id", msg.userId));

    } else if (msg.category === _DISCONNECT) {
      $('#' + msg.userId).remove();
    }
  };
  ws.onerror = function() {
    console.log("on error");
  };
});