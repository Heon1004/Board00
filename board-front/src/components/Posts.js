import React, { useState,useEffect } from 'react'
import { Link } from "react-router-dom";
import "./Posts.css";
import "../routes/Detail.js";

function Posts({boardId,title, writer, regDate, hitCount}) {
    return (
        <tbody>
            <tr key={boardId} className="post">
                <td className="num">{boardId}</td>
                <Link
                    className="title"
                    to={{
                        pathname: '/Detail'+boardId,
                        // board: {
                        //     board_id,
                        //     title,
                        //     content,
                        //     writer,
                        //     regDate,
                        // }
                    }}>
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
