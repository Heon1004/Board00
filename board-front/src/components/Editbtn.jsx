import React from 'react'
import { Link } from "react-router-dom";
import axios from "axios";

function Editbtn({board,history,isUser}) {
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
        }).then(() => {
                alert('削除完了');
                history.push('/Board?page='+board.page);
            })
        }
    }
    return (
        <>
        {isUser.edit ? (<>
            <button className="delete_btn" onClick={deletePost}>Delete</button>
            <Link to={{
                pathname:'/EditPost'+board.boardId,
                params:{
                    boardId: board.boardId,
                    title:board.title,
                    content:board.content,
                    writer:board.writer
                }
            }}>
                <button className="updata_btn">Edit</button>
            </Link>
            </>) : ('')}
        </>
    )
}

export default Editbtn
