$(function(){
  marked.setOptions({
    gfm: true,
    tables: true,
    breaks: false,
    pedantic: false,
    sanitize: true,
    smartLists: true,
    smartypants: false
  });

  var _CONNECT = 'CONNECT';
  var _DISCONNECT = 'DISCONNECT';
  var _MESSAGE = "MESSAGE";

  var ws = new WebSocket("ws://" + location.host + "/ws");
  ws.onopen = function() {};
  ws.onclose = function() {};
  ws.onmessage = function(message) {
    var msg = JSON.parse(message.data);

    if (msg.category === _CONNECT) {
      var $connectedUsers = $('#connected-users');
      $connectedUsers.append(
          $('<li/>')
              .text(msg.data)
              .addClass("list-group-item")
              .attr("id", msg.userId));

    } else if (msg.category === _DISCONNECT) {
      $('#' + msg.userId).remove();

    } else if (msg.category === _MESSAGE) {
      var $panel = $('<div/>').attr({class: "panel panel-default"});
      var $panelHeader = $('<div/>').attr({class: "panel-heading"});
      var $panelBody = $('<div/>').attr({class: "panel-body"});

      $panelHeader.text(msg.userName + ' Posted At ' + msg.timestamp);
      $panelBody.html(marked(msg.data));
      $('#room-message-list').prepend($panel.append($panelHeader).append($panelBody));

      var $messageBody = $('#room-message-body');
      $messageBody.val('');
      $messageBody.focus();
    }
  };
  ws.onerror = function() {
    console.log("on error");
  };

  // post message events
  var sendDbMessage = function(message) {
    var csrfToken = $('#_csrf').attr("content");
    var csrfHeader = $('#_csrf_header').attr("content");
    console.log('csrfToken=' + csrfToken);
    console.log('csrfHeader=' + csrfHeader);
    $.ajax({
      type: 'POST',
      url: 'http://' + location.host + '/api/messages',
      dataType: 'JSON',
      beforeSend: function(xhr){
        xhr.setRequestHeader(csrfHeader, csrfToken);
      },
      data: {
        message: message
      }
    });
  };
  $('#room-send-message-btn').click(function() {
    var messageBody = $('#room-message-body').val();
    ws.send(messageBody);
    sendDbMessage(messageBody);
  });
  $('#room-message-body').keydown(function(e) {
    if (e.ctrlKey && e.keyCode === 13) {
      $('#room-send-message-btn').trigger('click');
    }
  });

});