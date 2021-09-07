import React, { useState,useEffect} from 'react'
import './Detail.css';
import axios from "axios";
import BoardWrite from './BoardWrite';
import { Link } from "react-router-dom";

function Detail({match,history}) {
    const [detail,setDetail] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const boardId = match.params.id;
    /* したのほうほうでもできる。 */
    const getDetail = async () => {
        try{//detail?board_id
            await axios.get('/board/detail',{
                params:{
                    boardId:boardId
                }
            })
            .then((res) => {
                if(res.data){
                    setDetail(res.data);
                    setIsLoading(false);
                    axios.put('/board/hitcount');
                }else{
                    alert('登録されていない投稿です。');
                    history.push('/Board');
                }
                })
        }catch(error){
            console.log(error);
        }
    };

    const deletePost = () => {
        if(window.confirm('削除しますか?')){
            axios.post('/board/deletePost',null,{
                params:{
                    boardId:boardId
            }
        }).then((res) => {
                alert('削除完了');
                history.push('/Board');
            })
        }
    }

    useEffect(() => {
        getDetail();
    }, []);

    return (
        <div className="board">
            {isLoading ? (
                <div className="loader">
                    <span className="loader-text">Loading...</span>
                </div>  
            ) : (
                <div className="detail_box">
                    <div className="page-title">
                        <h3>Board</h3>
                    </div>
                    <div className="items">
                        <span className="title">{detail.title}</span>
                        <span className="writer">{detail.writer}</span>
                        <span className="regDate">{detail.regDate.slice(0,10)}</span>
                        <span className="hit">{detail.hitCount}</span>
                        <span className="updateDate"></span>
                        <div className="item_btn">
                            <button onClick={() => history.goBack()} className="list_btn">List</button>
                            <a href="#" className="comment_btn">Comment</a>
                            <button className="delete_btn" onClick={deletePost}>Delete</button>
                            <Link to={{
                                pathname:'/Write'+boardId,
                                params:{
                                    boardId: boardId,
                                    title:detail.title,
                                    content:detail.content,
                                    writer:detail.writer
                                }
                            }}>
                                <button className="updata_btn">Edit</button>
                            </Link>
                        </div>
                    </div>
                    <div className="contents">
                        {detail.content}
                    </div>
                </div>
            )}
        </div>
    )
}

export default Detail
