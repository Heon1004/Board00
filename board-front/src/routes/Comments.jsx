import React from 'react'
import './Comments.css';

function Comments({regDate,writer,content,updateDate,likes}) {
    return (
        <div className="comments">
            <div className="comment_writer">{writer}</div>
            <div className="comment_regDate">
                {updateDate ? updateDate : regDate.replace("T"," ")}
            </div>
            <div className="comment_content">{content}</div>
            <div className="comment_likes">{likes}</div>
            <div className="edit_items">
                <div className="comment_edit_btn">Edit</div>
                <div className="comment_delete_btn">Delete</div>
            </div>
        </div>
    )
}

export default Comments
