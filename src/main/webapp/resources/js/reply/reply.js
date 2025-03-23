function refreshReplyList(currPage){
    $.ajax({
        url : contextPath + "/reply/selectReplyPagination",
        data : { 
			"boardNo" : boardNo, 
			"cp" : currPage
		},
        dataType : "JSON",
        success : function(map){
            const replyArea = document.getElementsByClassName("section4")[0];
            replyArea.innerText = "";

            for( let reply of map.reply ){
                const review = document.createElement("div");
                review.classList.add("section4-review"); 
                replyArea.append(review);

                const writer = document.createElement("div");
                writer.classList.add("section4-writer");
                review.append(writer);

                const user = document.createElement("div");
                user.classList.add("section4-user");
                writer.append(user);

                const img = document.createElement("img");
                if( reply.memberProfileImage ){
                    img.setAttribute("src", contextPath + reply.memberProfileImage);
                } else {
                    img.setAttribute("src", contextPath + "/resources/images/user.png")
                }

                const nickname = document.createElement("span");
                nickname.innerText = reply.memberNickname;

                user.append(img, nickname);

                const content = document.createElement("p");
                content.innerHTML = reply.replyContent;

                const createDate = document.createElement("span");
                createDate.innerText = reply.createDate;
                createDate.classList.add("section4-enrolltime");


                writer.append(content, createDate);

                if( loginMemberNo == reply.memberNo ){
                    const btnArea = document.createElement("div");
                    btnArea.classList.add("section4-btn");

                    const deleteBtn = document.createElement("button");
                    deleteBtn.classList.add("delete-btn");
                    deleteBtn.innerText = "delete";
                    deleteBtn.setAttribute("onclick", "deleteReply(" + reply.replyNo + "," + currPage + ")");

                    const seperate = document.createElement("span");
                    seperate.innerText = "|";

                    const updateBtn = document.createElement("button");
                    updateBtn.classList.add("update-btn");
                    updateBtn.innerText = "update";
                    updateBtn.setAttribute("onclick", "showUpdateBtn(" + reply.replyNo + "," + currPage+ ", this)")

                    btnArea.append(deleteBtn, seperate, updateBtn);
                    review.append(btnArea);
                }

            }
            
            // pagination 
            
            const paginationArea = document.getElementsByClassName("pagination")[0];
            if( paginationArea != null ){
                paginationArea.innerText = "";
            }
		
			if( map.reply.length > 0 ){
				const pagination = map.pagination;
		
				const first = document.createElement("li");
				const fa = document.createElement("a");
				
				paginationArea.append(first);
				first.append(fa);
				
				fa.setAttribute("href", "detail?reviewNo=" + boardNo + "&cp=1");
				fa.innerHTML = '&lt;&lt;';
				
				const prev = document.createElement("li");
				const prevA = document.createElement("a");
				
				paginationArea.append(prev);
				prev.append(prevA);
				
				prevA.setAttribute("href", "detail?reviewNo=" + boardNo + "&cp=" + pagination.prevPage);
				prevA.innerHTML = "&lt;";
							
				for( let i = pagination.startPage; i <= pagination.endPage; i++ ){
					const li = document.createElement("li");
					const a = document.createElement("a");
					
					paginationArea.append(li);
					li.append(a);
					
					if( pagination.currentPage == i ){
						a.classList.add("current");
						a.innerText = i;
					} else {
						a.setAttribute("href", "detail?reviewNo=" + boardNo + "&cp=" + i);
						a.innerText = i;
					}
				}
				
	            const next = document.createElement("li");
	            const nextA = document.createElement("a");
	            
	            paginationArea.append(next);
	            next.append(nextA);
	            
	            nextA.setAttribute("href", "detail?reviewNo=" + boardNo + "&cp=" + pagination.nextPage);
	            nextA.innerHTML = "&gt;";
	            
	            const max = document.createElement("li");
	            const maxA = document.createElement("a");
	            
	            paginationArea.append(max);
	            max.append(maxA);
	            	            
	            maxA.setAttribute("href", "detail?reviewNo=" + boardNo + "&cp=" + pagination.maxPage);
		        maxA.innerHTML = "&gt;&gt;";
			}
			
	 
        },
        error : function(req, status, error){
            console.log(req.responseText);
        }
    });


}

const insertReply = document.getElementById("reply-insert");
const replyContent = document.getElementById("reply-content");

if( insertReply != null ){
    insertReply.addEventListener("click", function(){

        if( replyContent.value.trim().length == 0 ){
            replyContent.focus();
            replyContent.value = "";
    
            return;
        }
    
        $.ajax({
            url : contextPath + "/reply/insert",
            data : {
                "boardNo" : boardNo,
                "memberNo" : loginMemberNo,
                "replyContent" : replyContent.value
            },
            type : "POST",
            success : function(res){
                if( res > 0 ){
                    alert("댓글이 등록되었습니다.");
                    refreshReplyList(1);
                } else {
                    alert("댓글 등록에 실패하였습니다");
                }
            },
            error : function(req, status, error){
                console.log(req.responseText);
            }
        })
    });
}


function deleteReply(replyNo, currPage){
    console.log("hi");
    console.log(replyNo);
    $.ajax({
        url : contextPath + "/reply/delete",
        data : { 
			"replyNo" : replyNo,
			"cp" : currPage
		},
        success : function(res){
            if( res > 0 ){
                console.log("hi!")
                refreshReplyList(currPage);
            }
        },
        error : function(req, status, error){
            console.log(req.responseText);
        }
    });
}

let beforeReply;
function showUpdateBtn(replyNo, cp, btn){
    const temp = document.getElementsByClassName("update-textarea");
    if( temp.length > 0 ){
        if( confirm("다른 댓글이 수정 중입니다. 현재 댓글을 수정하시겠습니까?") ){
            temp[0].parentElement.innerHTML = beforeReply;
        } else {
            return;
        }
    }

    const reply = btn.parentElement.parentElement;
    beforeReply = reply.innerHTML;

    let beforeContent = reply.children[0].children[1].innerHTML;
    
    reply.innerHTML = "";

    const textArea = document.createElement("textarea");

    beforeContent = beforeContent.replaceAll("&amp;", "&");
    beforeContent = beforeContent.replaceAll("&lt;", "<");
    beforeContent = beforeContent.replaceAll("&gt;", ">");
    beforeContent = beforeContent.replaceAll("&Quot;", "\"");

    beforeContent = beforeContent.replaceAll("<br>", "\n");

    textArea.value = beforeContent;
    textArea.classList.add("reply-content");
    textArea.focus();

    const updateBtn = document.createElement("button");
    const seperate = document.createElement("span");
    const cancelBtn = document.createElement("button");

    updateBtn.classList.add("update-btn");
    cancelBtn.classList.add("delete-btn");

    updateBtn.setAttribute("onclick", "updateReply(" + replyNo + "," + cp + ", this)");
    cancelBtn.setAttribute("onclick", "updateCancel(this)");

    updateBtn.innerText = "update";
    cancelBtn.innerText = "cancel";
    seperate.innerText = "|";

    const buttonSection = document.createElement("div");
    buttonSection.classList.add("section4-btn");
    buttonSection.append(updateBtn, seperate, cancelBtn);

    const replyUpdateDiv = document.createElement("div");
    replyUpdateDiv.classList.add("reply-insert-area");
    replyUpdateDiv.append(textArea, buttonSection);

    reply.append(replyUpdateDiv);
}

function updateCancel(btn){
    if( confirm("댓글 수정을 취소하겠습니까?") ){
        const reply = btn.parentElement.parentElement;

        reply.innerHTML = beforeReply;
    }
}

function updateReply(replyNo, cp, btn){
    console.log("haha");
    console.log(btn.parentElement.previousElementSibling);
    $.ajax({
        url : contextPath + "/reply/update",
        data : {
            "replyNo" : replyNo,
            "replyContent" : btn.parentElement.previousElementSibling.value
        },
        type : "POST",
        success : function(res){
            if( res > 0 ){
                refreshReplyList(cp);
            }
        },
        error : function(req, status, error){
            console.log(req.responseText);
        }
    })
}
