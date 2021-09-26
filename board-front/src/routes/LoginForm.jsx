import React, { useState } from 'react'
import { Link, } from "react-router-dom";
import './LoginForm.css';
import Cookies from 'universal-cookie';
import axios from "axios";


function LoginForm({history}) {
    const [email,setEmail] = useState();
    const [pw,setPw] = useState();
    const [msg, setMsg] = useState('');
    const cookies = new Cookies();

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
            }).then((res) => {
                if(typeof res.data != 'undefined'){
                    cookies.set('token',res.data[0]);
                    cookies.set('name', res.data[1]);
                    history.push('/');
                    window.location.reload();
                }else{
                    setMsg("パスワード又はメールをもう一度確認してください。");
                }
            }) 
        }else{
            setMsg("正しくないメール形です。");
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
