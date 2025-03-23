function updateDetail(boardNo){
    const section = document.getElementsByClassName("section3")[0];

    $.ajax({
        url : contextPath + "/board/detailAjax",
        data : { "boardNo" : boardNo },
        dataType : "JSON",
        success : function(detail){
            console.log(section);

            console.log("hi~");
            section.innerText = "";
            
            const h1 = document.createElement("h1");
            h1.innerText = detail.boardCategory;
            section.append(h1);

            const contextDiv = document.createElement("div");
            contextDiv.classList.add("section3-context");
            section.append(contextDiv);

            const title = document.createElement("h2");
            title.innerHTML = detail.boardTitle;

            const content = document.createElement("p");
            content.innerHTML = detail.boardContent;

            contextDiv.append(title, content);

            const buttons = document.createElement("div");
            buttons.classList.add("section3-btn");

            const deleteBtn = document.createElement("a");
            deleteBtn.classList.add("delete-btn");
            deleteBtn.setAttribute("href", "${contextPath}/mypage/deleteBoard?boardNo=" + detail.boardNo); 
        },
        error : function(req, status, error){
            console.log(req.responseText);
        }
    })
}