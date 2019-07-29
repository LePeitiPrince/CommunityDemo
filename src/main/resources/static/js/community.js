/**
 * 提交回复
 */
function comment() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2target(questionId, 1, content);
}

function replay(e) {
    var replayId = e.getAttribute("data");
    var content = $("#replay-" + replayId).val();
    comment2target(replayId, 2, content);
}

function comment2target(targetId, type, content) {
    if (!content) {
        alert("评论不能为空~~~");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    var conf = confirm(response.message);
                    if (conf) {
                        window.open("https://github.com/login/oauth/authorize?client_id=f77c703fe9c05ae389c4&redirect_uri=http://localhost:8888/callback&scope=user repo&state=1");
                        window.localStorage.setItem("closeable", "true");
                    }
                } else {
                    alert(response.message)
                }
            }
        },
        dataType: "json"
    });
}

/**
 * 展开二级评论
 * @param e
 */
function collapseComments(e) {
    var id = e.getAttribute("data");
    var comment = $("#comment-" + id);
    //获取二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        //折叠二级评论
        comment.removeClass("in");
        e.removeAttribute("data-collapse")
        e.classList.remove("active");
    } else {
        var commentBody = $("#comment-" + id);
        //判断是否加载过二级评论，如果加载过，就不去重新请求
        if (commentBody.children().length != 2) {
            //展开二级评论
            comment.addClass("in");
            //添加展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            //展开二级评论前异步查询回复列表
            $.getJSON("/comment/" + id, function (data) {
                //根据返回的api拼接页面
                $.each(data.data.reverse(), function (index, comment) {

                    var mediaBodyElement = $("<div/>",{
                        class:"media-body",
                    }).append($("<h5/>",{
                        class:"media-heading",
                        html:comment.user.name
                    })).append($("<div/>",{
                        html:comment.content
                    })).append($("<div/>",{
                        class:"menu"
                    }).append($("<span/>",{
                        class:"pull-right",
                        html:moment(comment.gmtCreate).format("YYYY-MM-DD HH:mm")
                    })));

                    var mediaLeftElement = $("<div/>", {
                        class: "media-left"
                    }).append($("<img/>", {
                        class: "media-object img-rounded",
                        src: comment.user.avatar_URL
                    }));

                    var mediaElement = $("<div/>", {
                        class: "media comments",
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    commentBody.prepend(mediaElement)
                });

                //展开二级评论
                comment.addClass("in");
                //添加展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}

function showSelectTag() {
    $("#select-tag").show()
}

function selectTag(e) {
    var value = e.getAttribute("data");
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1){
        if (previous){
            $("#tag").val(previous+'，'+value);
        }else {
            $("#tag").val(value)
        }
    }
}