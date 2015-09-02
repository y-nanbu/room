$(function(){
  var ws = new WebSocket("ws://" + location.host + "/ws");
  ws.onopen = function() {
    console.log("on open");
  };
  ws.onclose = function() {
    console.log("on close");
  };
  ws.onmessage = function() {
    console.log("on message");
  };
  ws.onerror = function() {
    console.log("on error");
  };
});