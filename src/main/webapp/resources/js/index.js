
// 검색창에 이전 검색 기록 반영하기
(function(){
        const input = document.getElementById("search-query");
    
        if(select != null){ // 검색창 화면이 존재할 때만 코드 적용
    
            // 현재 주소에서 쿼리스트링(파라미터) 얻어오기
            const params = new URL(location.href).searchParams;
    
            const query = params.get("query");
            
            // input에 query값 대입
            input.value = query;
    
        
        }
    })();
    
// 검색 유효성 검사(검색어를 입력했는지 확인)
function searchValidate(){

    const query = document.getElementById("search-query");

    if(query.value.trim().length == 0){ // 미작성
        query.value = ""; // 빈칸
        query.focus();

        return false;
    }

    return true;
}




