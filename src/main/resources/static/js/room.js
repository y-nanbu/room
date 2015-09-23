$(function(){

  if (notify.isSupported) {
    notify.requestPermission();
  }

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
  ws.onopen = function() {
    $('#room-loading').hide();
  };
  ws.onclose = function() {
    alert('WebSocketの通信がCLOSEされました。');
  };
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
      addMessage({username: msg.userName, timestamp: msg.timestamp, message: msg.data}, true);
      $('#room-message-body').val('');

      var existViewMessage = $('#room-message-list').find('.panel');
      if (existViewMessage.length == 1) {
        $('#room-next-result-link').attr('data-last-timestamp', msg.timestamp);
      }
    }
  };
  ws.onerror = function() {
    alert('WebSocket通信で予期せぬエラーが発生しました。');
  };

  // post message events
  var sendDbMessage = function(message) {
    var csrfToken = $('#_csrf').attr("content");
    var csrfHeader = $('#_csrf_header').attr("content");
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

  $('#room-next-result-link').click(function(e) {
    e.preventDefault();
    $.ajax({
      type: 'GET',
      url: 'http://' + location.host + '/api/messages',
      dataType: 'JSON',
      data: {timestamp: $('#room-next-result-link').attr('data-last-timestamp')}
    }).done(function(data) {
      if(data[0]) {
        $.each(data, function() {
          var $this = $(this)[0];

          addMessage({username: $this.userName, timestamp: $this.updatedAt, message: $this.message});
          $('#room-next-result-link').attr('data-last-timestamp', $this.updatedAt);
        });
      } else {
        alert('最後の結果を表示しています。');
      }
    });
  });

  function addMessage(params, prepend) {
    var username = params.username;
    var timestamp = params.timestamp;
    var message = params.message;

    var $panel = $('<div/>').attr({class: "panel panel-default"});
    var $panelHeader = $('<div/>').attr({class: "panel-heading"});
    var $panelBody = $('<div/>').attr({class: "panel-body"});

    $panelHeader.text(username + ' Posted At ' + timestamp);
    $panelBody.html(marked(message));
    if (prepend) {
      $('#room-message-list').prepend($panel.append($panelHeader).append($panelBody));
      notify.config({autoClose: 10000});
      notify.createNotification(username, { body: message, icon: "alert.ico"});
    } else {
      $('#room-message-list').append($panel.append($panelHeader).append($panelBody));
    }

    $panelBody.find('pre').each(function(i, block) {
      hljs.highlightBlock(block);
    });
  }
});