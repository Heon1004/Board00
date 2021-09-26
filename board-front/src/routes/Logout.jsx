import React from 'react'
import Cookies from 'universal-cookie';
// import axios from "axios";

function Logout({history}) {
    const cookies = new Cookies();
    const logout = () => {
        // axios.get('/user/logout',{
        //     headers: {
        //         'Content-Type': 'application/json',
        //     } 
        // }).then(res => {
        //     cookies.remove('token');
        //     window.location.reload();
        //     history.push('/Login');
        // })

        cookies.remove('token');
        cookies.remove('name');
        window.location.reload();
        history.push('/Login');
    }
        logout();
    return (
        <div>
            
        </div>
    )
}

export default Logout
