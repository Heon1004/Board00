import React from 'react'
import Cookies from 'universal-cookie';
import axios from "axios";

function Logout({history}) {
    const cookies = new Cookies();
    
    const logout = () => {
        axios.get('/user/logout',{
            headers: {
                'Content-Type': 'application/json',
                'Authorization': cookies.get('refresh_token')
            } 
        }).then(res => {
            cookies.remove('refresh_token');
            history.push('/Login');
        })}
    
    return (
        <div>
            
        </div>
    )
}

export default Logout
