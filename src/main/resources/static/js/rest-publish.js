$(function() {
  marked.setOptions({
    gfm: true,
    tables: true,
    breaks: false,
    pedantic: false,
    sanitize: true,
    smartLists: true,
    smartypants: false
  });

  var RoomStomp = function() {};

  /**
   * STOMPの接続処理です。
   */
  RoomStomp.prototype.connect = function() {
    var socket = new WebSocket('ws://' + location.host + '/subscribe');
    console.log(socket);
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, this.onConnected.bind(this));
  };

  /**
   * STOMPの接続後の処理です。
   */
  RoomStomp.prototype.onConnected = function(frame) {
    var client = this.stompClient;
    //var whoami = frame.headers['user-name'];

    client.subscribe('/topic/send', function(message) {
      var subMessage = JSON.parse(message.body);

      var $panel = $('<div/>').attr({class: "panel panel-info"});
      var $panelHeader = $('<div/>').attr({class: "panel-heading"});
      var $panelBody = $('<div/>').attr({class: "panel-body"});

      $panelHeader.text(subMessage.username + ' Posted At ' + subMessage.timestamp);
      $panelBody.html(marked(subMessage.message));
      $('#room-message-list').prepend($panel.append($panelHeader).append($panelBody));

      $panelBody.find('pre').each(function(i, block) {
        hljs.highlightBlock(block);
      });
    });
  };

  RoomStomp.prototype.connect();
});