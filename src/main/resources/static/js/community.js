function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").text();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type":1
        }),
        success: function (response){
            if (response.code == 200){
                $("#comment-section").hide();
            }else {
                if (response.code == 2003){
                    var conf = confirm(response.message);
                    if (conf){
                        window.open("https://github.com/login/oauth/authorize?client_id=f77c703fe9c05ae389c4&redirect_uri=http://localhost:8888/callback&scope=user repo&state=1");
                        window.localStorage.setItem("closeable","true");
                    }
                }
            }
        },
        dataType: "json"
    });
}