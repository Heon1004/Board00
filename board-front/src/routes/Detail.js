import React, { useState,useEffect} from 'react'
import './Detail.css';
import axios from "axios";
import { Link } from "react-router-dom";

function Detail({location,history}) {
    const [board] = useState(location.state ?? null);

    const deletePost = () => {
        if(window.confirm('削除しますか?')){
            axios.post('/board/deletePost',{
                headers:{
                    'Content-Type': 'application/json',
                }
            },{
                params:{
                    boardId:board.boardId
            }
        }).then((res) => {
                alert('削除完了');
                history.push('/Board?page='+board.page);
            })
        }
    }

    useEffect(() => {
    }, []);

    return (
        <div className="board">
            <div className="detail_box">
                <div className="page-title">
                    <h3>Board</h3>
                </div>
                <div className="items">
                    <span className="title">{board.title}</span>
                    <span className="writer">{board.writer}</span>
                    <span className="regDate">{board.regDate.slice(0,10)}</span>
                    <span className="hit">{board.hitCount}</span>
                    <span className="updateDate"></span>
                    <div className="item_btn">
                        <button onClick={() => history.goBack()} className="list_btn">List</button>
                        <a href="#" className="comment_btn">Comment</a>
                        <button className="delete_btn" onClick={deletePost}>Delete</button>
                        <Link to={{
                            pathname:'/Write'+board.boardId,
                            params:{
                                boardId: board.boardId,
                                title:board.title,
                                content:board.content,
                                writer:board.writer
                            }
                        }}>
                            <button className="updata_btn">Edit</button>
                        </Link>
                    </div>
                </div>
                <div className="contents">
                    {board.content}
                </div>
            </div>
        </div>
    )
}

export default Detail
