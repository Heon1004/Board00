import React,{useRef} from 'react'
import "./SearchPost.css";

function SearchPost({history}) {
    const keyword = useRef();
    //검색결과 잘 받아와짐.
    const search = () => {
        history.push('/Search/'+keyword.current.value);
    }
    
    return (
        <div>
            <input 
                type="text" 
                className="keyword_box" 
                placeholder="keyword"
                ref={keyword}
            />
            <button className="search_btn" onClick={search}>検索</button>
        </div>
    )
}

export default SearchPost
