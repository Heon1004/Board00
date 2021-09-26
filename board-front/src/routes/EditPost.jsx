import React,{useState,useRef} from 'react'
import './BoardWrite.css';
import axios from "axios";

function PostUpdate({history,location}) {
    const board = useRef(location.params ?? '');
    const [title, setTitle] = useState(board.current.title ?? '');
    const [content, setContent] = useState(board.current.content ?? '');
    const writePost = () => {
        
        try{
            axios.post('/board/updatePost', {
                headers:{ 
                    'Content-Type': 'application/json',
            }}, {
                params:{
                    boardId: board.current.boardId,
                    title:title,
                    content:content,
                    writer:board.current.writer
                }
            })
            alert('投稿アップデート完了');
            history.push("/Board?page="+board.page);
        }catch(error){
            console.log(error);
        }
    }

    return (
        <div className="container">
            <div className="write_box">
                <div className="page-title">
                    <h3>Write</h3>
                </div>
                <div className="title">
                    <label htmlFor="title_item" className="title_label">タイトル</label>
                    <input 
                        type="text" 
                        id="title_item" 
                        value={title}
                        onChange={(e) => {setTitle(e.target.value)}}
                    />
                </div>
                <div className="content">
                    <label htmlFor="content_item" className="content_label">Content</label>
                    <textarea 
                        id="content_item"
                        value={content}
                        onChange={(e) => {setContent(e.target.value)}}
                    />
                </div>
            </div>
            <button type="button" onClick={writePost}>作成</button>
        </div>
    )
}

export default PostUpdate
