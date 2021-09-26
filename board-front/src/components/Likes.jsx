import React, {useState} from 'react'
import axios from "axios";

function Likes({likes,boardId}) {
    const [like, setLike] = useState(likes);
    const likeEvent = () => {
        try {
            axios.post('/board/updateLikes',{
                headers:{'Content-Type': 'application/json',}
            },{
                params:{boardId:boardId}
            }).then(() => {
                setLike(() => like+1)
            })
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <button 
            className="like_btn"
            onClick={likeEvent}
        >
            👍{like}
        </button>
    )
}

export default Likes
