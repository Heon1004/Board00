import React,{useState} from 'react'
import './BoardWrite.css';
import axios from "axios";
import Cookies from 'universal-cookie';
function BoardWrite({history,location}) {
    
    const [board, setBoard] = useState(location.params ?? '');
    const [title, setTitle] = useState(board.title ?? '');
    const [content, setContent] = useState(board.content ?? '');

    const writePost = () => {
        const cookies = new Cookies();
        try{
            axios.post('/board/write', {
                headers:{ 
                    'Content-Type': 'application/json',
            }}, {
                params:{
                    title: title,
                    content: content,
                }
            })
            alert('投稿完了');
            // history.push("/Board");
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

export default BoardWrite
