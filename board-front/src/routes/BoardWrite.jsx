import React,{useRef} from 'react'
import './BoardWrite.css';
import axios from "axios";

function BoardWrite({history}) {
    const title = useRef();
    const content = useRef();

    const writePost = () => {
        try{
            axios.post('/board/write', {
                headers:{ 
                    'Content-Type': 'application/json',
            }}, {
                params:{
                    title: title.current.value,
                    content:content.current.value
                }
            })
            alert('投稿完了');
            history.push("/Board");
            window.location.reload();
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
                        autoComplete="off"
                        ref={title}
                    />
                </div>
                <div className="content">
                    <label htmlFor="content_item" className="content_label">Content</label>
                    <textarea 
                        id="content_item"
                        autoComplete="off"
                        ref={content}
                    />
                </div>
            </div>
            <button type="button" onClick={writePost}>作成</button>
        </div>
    )
}

export default BoardWrite
