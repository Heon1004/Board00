import React, { useState } from 'react'
import axios from "axios";
import "./SingUpForm.css";

function SingUpForm({history}) {
    const [email, setEmail] = useState();
    const [pw, setPw] = useState();
    const [name,setName] = useState();
    const [msg,setMsg] = useState();
    
    function singupCheck(){
        const regId = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
        //正規表現が正しい時trueを返す
        if(regId.test(email)&&pw&&name){
            axios.post('/user/singup',null, { 
                params:{
                    userEmail: email,
                    userPw: pw,
                    userName: name
                }
            }).then((response) => {
                console.log(response);
                if(response.data){
                    alert('会員登録完了');
                    history.push('/Login');
                }else{
                    setMsg("Failled Sing-Up");
                }
                
            }) 
            
        }else{
            alert('正しいメールアドレスを入力してください。');
        }
    }
    return (
        <div className="container">
            <div className="page-title">
                <h3>Sing-Up</h3>
            </div>
            <div className="input-box">
                <input 
                    type="Email" 
                    id="Email" 
                    name="Email" 
                    placeholder="Email" 
                    autoComplete="off"
                    onChange={(e) => { setEmail(e.target.value) }}
                />
                <label htmlFor="Email">Email</label>
            </div>
            <div className="input-box">
                <input 
                    type="password" 
                    id="password" 
                    name="password" 
                    placeholder="Password" 
                    autoComplete="off"
                    onChange={(e) => { setPw(e.target.value) }}
                />
                <label htmlFor="password">Password</label>
            </div>
            <div className="input-box">
                <input 
                    type="text" 
                    id="username" 
                    name="username" 
                    placeholder="Name" 
                    autoComplete="off"
                    onChange={(e) => { setName(e.target.value) }}
                />
                <label htmlFor="username">Name</label>
            </div>
            <div className="input-box">
                <button type="button" onClick={singupCheck}>Sing Up</button>
            </div>
            {msg}
        </div>
    )
}

export default SingUpForm
