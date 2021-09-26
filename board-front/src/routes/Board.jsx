import React,{ useState, useEffect, useRef } from 'react'
import axios from "axios";
import "./Board.css";
import Posts from '../components/Posts';
import { Link} from "react-router-dom";
import Pagebtn from '../components/Pagebtn';
import SearchPost from '../components/SearchPost';

function Board({match,history}) {
    const pageNow = useRef(1);
    const [boards,setBoards] = useState({
        isLoading: true,
        list: [],
        total: '',
    });
    
    const getBoards = () => {
        try{
            axios.get('/board/list',{
                headers:{ 'Content-Type': 'application/json' },
                params:{ page:pageNow.current }
            }).then(res =>{
                console.log(res);
                setBoards({
                    list:res.data, 
                    isLoading: false, 
                    total:res.data.totalPages,
                });
            });
        }catch(error){
            console.log(error);
        }
    };


    useEffect(() => {
        pageNow.current = (match.params.page ?? 1);
        getBoards();
    }, [match]);
    
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
                                <th className="header_hit">Like</th>
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
                            updateDate={board.updateDate}
                            hitCount={board.hitCount}
                            likes={board.likes}
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
                    <SearchPost history={history}/>
                    <div className="write_btn">
                        <Link to="/Write"  className="link"><button type="button" >作成</button></Link>
                    </div>
                </div> 
            </div>
            )}
        </div>
    )
}


export default Board
