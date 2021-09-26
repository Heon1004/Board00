import { Link } from "react-router-dom";
import "./Navigation.css";
import Cookies from 'universal-cookie';
import React from 'react';

function Navigation() {
    const cookies = new Cookies();
    return (
        <div>
            <nav>
            <Link to="/">Home</Link>
            <Link to="/Board">Board</Link>
            {cookies.get('token') ? <Link to="/Logout">Logout</Link> 
            : (<div className="not_login">
                <Link to="/Login">Login</Link>
                <Link to="/SingUp">SingUp</Link>
            </div>)}
            <div className="user_con">
                {cookies.get('name') ? (<>{cookies.get('name')}ようこそ</>) : (null)}
            </div>
            </nav>
        </div>
    )
}

export default Navigation
