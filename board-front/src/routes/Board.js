import React,{ useState, useEffect, useRef } from 'react'
import axios from "axios";
import "./Board.css";
import Posts from '../components/Posts';
import { Link} from "react-router-dom";
import Pagebtn from '../components/Pagebtn';

function Board({match}) {
    const pageNow = useRef(1);
    const userAuth = localStorage.getItem('auth');
    console.log(localStorage);
    const [boards,setBoards] = useState({
        isLoading: true,
        list: [],
        total: '',
    });
    console.log(match);
    
    const getBoards = async () => {
        console.log(pageNow.current);
        try{
            await axios.get('/board/list',{
                params:{
                    page:pageNow.current
                }
            }).then(res =>{
                setBoards({
                    list:res.data, 
                    isLoading: false, 
                    total:res.data.totalPages,
                });
                console.log(res.data)
            });
        }catch(error){
            console.log(error);
        }
    };

    useEffect(() => {
        console.log("useEffect Render");
        pageNow.current = (typeof match.params.page == 'undefined' ? 1 : match.params.page);
        getBoards();
    }, [match]);
    console.log("Board 렌더링")
    
    return (
        <div className="container">
            {boards.isLoading ? (
                <div className="loader">
                <span className="loader-text">Loading...</span>
            </div>
            ) : (
                <div>
                <div className="page-title">
                    <h3>Board</h3>
                </div>
                <div id="board-list">
                    <table>
                        <thead>
                            <tr>
                                <th className="header_num">番号</th>
                                <th className="header_title">タイトル</th>
                                <th className="header_writer">作成者</th>
                                <th className="header_date">作成日</th>
                                <th className="header_hit">ヒット</th>
                            </tr>
                        </thead>
                        {boards.list.content.map(board => {
                            return <Posts
                            key={board.boardId}
                            boardId={board.boardId}
                            title={board.title}
                            content={board.content}
                            writer={board.writer}
                            regDate={board.regDate}
                            hitCount={board.hitCount}
                            page={pageNow.current}
                            />
                        })}
                    </table>
                    <div className="page_btn">
                        <ul>
                            <Pagebtn 
                                page={pageNow.current}
                                total={boards.total}
                            >
                            </Pagebtn>
                        </ul>
                    </div>
                    <div className="write_btn">
                        <Link to="/Write" className="link"><button type="button" >作成</button></Link>
                    </div>
                </div> 
            </div>
            )}
        </div>
    )
}


export default Board