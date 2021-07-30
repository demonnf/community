function post() {
    var commenttext = $("#commenttext").val();
    var parentid = $("#pid").val();
    if(!commenttext){
        alert("response is null")
        return
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
                "type": 1,
                "parentId": parentid,
                "content": commenttext,
            }
        ),
        success: function (response) {
            if (response.code == 200) {
                //$("#comment_hide").hide()
                window.location.reload()
            } else {
                if (response.code == 2003) {
                    var com = confirm(response.message)
                    if (com) {

                        window.open("https://github.com/login/oauth/authorize?client_id=39aa3b52606aa47cddeb&redirect_uri=http://localhost:8088/callback&scope=user&state=1")
                       window.localStorage.setItem("clearitem",true)
                    }
                } else {
                    alert(response.message)
                }
            }


        },
        dataType: "json"
    })

}