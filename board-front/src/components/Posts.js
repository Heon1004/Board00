import React,{useState} from 'react'
import { Link } from "react-router-dom";
import "./Posts.css";
import "../routes/Detail.js";
import axios from "axios";

function Posts({boardId,title, writer,content, regDate, hitCount,page}) {
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
                            regDate: regDate.slice(0,10),
                            hitCount,
                            page
                        }}}
                    onClick={updateHitCount}
                    >
                    {title.slice(0,25)}
                </Link>
                    <td className="writer">{writer}</td>
                    <td className="regdate">{regDate.slice(0,10)}</td>
                    <td className="hitCount">{hitCount}</td>
            </tr>
        </tbody>
    )
}

export default Posts
