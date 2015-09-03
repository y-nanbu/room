$(function(){
  var _CONNECT = 'CONNECT';
  var _DISCONNECT = 'DISCONNECT';
  var _MESSAGE = "MESSAGE";

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

    } else if (msg.category === _MESSAGE) {
      var $panel = $('<div/>').attr({class: "panel panel-default"});
      var $panelHeader = $('<div/>').attr({class: "panel-heading"});
      var $panelBody = $('<div/>').attr({class: "panel-body"});

      $panelHeader.text(msg.userName);
      $panelBody.text(msg.data);

      $('#room-message-list').prepend(
          $panel
              .append($panelHeader)
              .append($panelBody)
      );
    }
  };
  ws.onerror = function() {
    console.log("on error");
  };

  $('#room-send-message-btn').click(function() {
    var messageBody = $('#room-message-body').val();
    ws.send(messageBody);
  });
});