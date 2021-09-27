import React from 'react'
import { Link } from "react-router-dom";
import "./Posts.css";
import "../routes/Detail";

function Posts({boardId,title, writer,content, regDate, updateDate, hitCount,page,likes}) {
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
