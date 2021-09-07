import { Link } from "react-router-dom";
import "./Navigation.css";

import React from 'react'

function Navigation() {
    return (
        <div>
            <nav>
            <Link to="/">Home</Link>
            <Link to="/Board">Board</Link>
            <Link to="/Login">Login</Link>
            <Link to="/SingUp">SingUp</Link>
            </nav>
        </div>
    )
}

export default Navigation
