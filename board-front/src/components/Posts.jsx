import React,{useState} from 'react'
import { Link } from "react-router-dom";
import "./Posts.css";
import "../routes/Detail";
import axios from "axios";

function Posts({boardId,title, writer,content, regDate, updateDate, hitCount,page,likes}) {
    const [updateHit, setUpdateHit] = useState(hitCount);
    const updateHitCount = () => {
        try{
            axios.post('/board/updateHitCount',{
                Headers:{'Content-Type': 'application/json'}
            },{
                params:{ boardId: boardId }
            }).then(res => {
               setUpdateHit(res.data);
            })
        }catch(error){
            console.log(error);
        }
    }
    return (
        <tbody>
            <tr key={boardId} className="post">
                <td className="num">{boardId}</td>
                <Link
                    className="title"
                    to={{
                        pathname: "/Detail",
                        state: {
                            boardId,
                            title,
                            content,
                            writer,
                            regDate,
                            updateDate,
                            hitCount,
                            page,
                            likes
                        }}}
                    onClick={updateHitCount}
                    >
                    {title.slice(0,25)}
                </Link>
                    <td className="writer">{writer}</td>
                    <td className="regdate">{regDate}</td>
                    <td className="hitCount">{hitCount}</td>
                    <td className="hitCount">{likes}</td>
            </tr>
        </tbody>
    )
}

export default Posts
