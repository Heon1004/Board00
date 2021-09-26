import React, { useState,useEffect} from 'react'
import './Detail.css';
import axios from "axios";
import Editbtn from "../components/Editbtn";
import Likes from "../components/Likes";

function Detail({location,history}) {
    const [board] = useState(location.state ?? null);
    const [comment, setComment] = useState();
    const [isUser, setIsUser] = useState({
        isLoading: true,
        edit: false
    });
    
    const detailPost = () => {
        try{
            axios.post('/board/detail',{
                headers:{'Content-Type': 'application/json',}
            },{
                params:{
                    boardId: board.boardId
                }
            }).then(res => {
                setIsUser({
                    isLoading: false,
                    edit: res.data
                });

            })
        }catch(error){
            console.log(error);
        }
    }

    const getComments = () => {
        try{
            axios.get('/comment/getComments',{
                headers:{'Content-Type': 'application/json'},
                params:{boardId: board.boardId}
            }).then(res => {
                console.log(res.data);
                setComment({
                    comments:res.data,
                    isLoading: false
                })
            })
        }catch(error){
            console.log(error);
        }
    }
    useEffect(() => {
        detailPost();
        getComments();
    }, []);

    return (
        <div className="board">
            {isUser.isLoading ? (
                <div className="loader">
                    <span className="loader-text">Loading...</span>
                </div>
            ) : (
            <div className="detail_box">
                <div className="itemheader">
                    <span className="title">{board.title}</span>
                    <span className="writer">{board.writer}</span>
                    <span className="regDate">{board.regDate}</span>
                    <span className="hit">ヒット数 {board.hitCount}</span>
                    <span className="updateDate"></span>
                    <span className="updateDate">{(board.updateDate ==  board.regDate) ? <></> : (<>{board.updateDate}</>)}</span>
                    <div className="item_btn">
                        <button onClick={() => history.goBack()} className="list_btn">List</button>
                        <a href="#" className="comment_btn">Comment</a>
                        <Editbtn
                            board={board}
                            isUser={isUser}
                            history={history}
                        />
                        <Likes
                            likes={board.likes}
                            boardId={board.boardId}
                        />
                    </div>
                    
                </div>
                <div className="contents">
                    {board.content}
                </div>
                <div className="comments">
                    <div className="comment_item">
                        <div className="comment_regDate">2022.22.22</div>
                        <div className="comment_writer"></div>
                        <div className="comment_content"></div>
                        <div className="edit_items">
                            <div className="comment_edit_btn"></div>
                            <div className="comment_delete_btn"></div>
                        </div>
                    </div>
                </div>
            </div>
            )}
        </div>
    )
}

export default Detail
