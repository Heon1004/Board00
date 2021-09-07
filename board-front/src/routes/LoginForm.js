import React, {useState, } from 'react'
import { Link, } from "react-router-dom";
import './LoginForm.css';
import axios from "axios";


function LoginForm({history,dispatch}) {
    const [email,setEmail] = useState();
    const [pw,setPw] = useState();
    const [msg, setMsg] = useState('');
    function loginCheck(){
        const regId = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
        //正規表現が正しい時trueを返す
        if(regId.test(email)&&pw){
            axios.post('/user/login',{
                headers: {
                    'Content-Type': 'application/json',
                } 
            },{ 
                params:{
                    userEmail: email,
                    userPw: pw
                }
            }).then((response) => {
                const { accessToken } = response.data;
                console.log(response.data);
                // API 요청하는 콜마다 헤더에 accessToken 담아 보내도록 설정
                axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
                // accessToken을 localStorage, cookie 등에 저장하지 않는다!
                console.log({accessToken});
            }) 
        }else{
            setMsg("Check to Email");
        }
    }
    return (
        <div className="container">
            <div className="page-title">
                <h3>Login</h3>
            </div>
            <div className="input-box">
                <input 
                    type="text" 
                    id="user_email" 
                    name="user_email" 
                    placeholder="Email" 
                    autoComplete="off"
                    onChange={(e) => {setEmail(e.target.value)}}
                />
                <label htmlFor="user_email">Email</label>
            </div>
            <div className="input-box">
                <input 
                    type="password" 
                    id="user_pw" 
                    name="user_pw" 
                    placeholder="Password" 
                    autoComplete="off"
                    onChange={(e) => {setPw(e.target.value)}}
                />
                <label htmlFor="user_pw">Password</label>
            </div>
            <div className="msg">
                {msg}
            </div>
            <div id="sing_up"><Link to="/SingUp">SingUp</Link></div>
            <div className="input-box">
                <button type="button" onClick={loginCheck}>Login</button>
            </div>
        </div>
    )
}

export default LoginForm
