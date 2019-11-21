<html>

<head>
    <title>websocket测试</title>
    <meta charset="utf-8">
</head>

<body>
<button onclick="sendMessage()">测试</button>
<script>
    var socket = new WebSocket("ws://localhost:8090/websocket");
    socket.onerror = err => {
        console.log(err);
    };
    socket.onopen = function (event)  {
        console.log(event);
    };
    socket.onmessage = function(mess) {
        console.log(mess);
    };
    socket.onclose = function () {
        console.log("连接关闭");
    };

    function sendMessage() {
        if (socket.readyState === 1)
            socket.send("这是一个测试数据");
        else
            alert("尚未建立websocket连接");
    }
</script>
</body>

</html>