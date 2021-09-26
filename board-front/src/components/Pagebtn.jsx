import React, { useRef } from 'react'
import {Link} from "react-router-dom";
import './Pagebtn.css'

function Pagebtn({total, page}) {
    const startNum = useRef(page-(page-1)%5);
    const endNum = useRef(startNum.current+5);

    const btn = () => {
        const result = [];
        if(page >= 6){
            result.push(
                <Link 
                    key={startNum.current-1} 
                    to={{ pathname: '/Board'+(startNum.current-5) }}
                    onClick={() => {
                        startNum.current -= 5; 
                        endNum.current -= 5;
                    }}
                >
                Prev
                </Link>
            )
        }
        for(let i=startNum.current; i < endNum.current; i++){
            if(i > total){ break;}
            result.push(
                <li key={i} className={(page == i ? 'page active' : 'page')}>
                    <Link 
                        to={{ pathname: '/Board'+i }}
                    >
                        {i}
                    </Link>
                </li>
            )
                
        }
        if(endNum.current <= total){
            result.push(
                <Link 
                    key={startNum-2} 
                    to={{ pathname: '/Board'+(endNum.current) }}
                    onClick={() => {
                        endNum.current += 5;
                        startNum.current += 5;
                        }}
                >
                Next
                </Link>
            )
        }
        return result;
    };

    return (
        <ul>
            {btn()}
        </ul>
    )
}

export default Pagebtn
