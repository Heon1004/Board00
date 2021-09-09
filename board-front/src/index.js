import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import axios from "axios";
import Cookies from 'universal-cookie';

axios.defaults.withCredentials = true;
const cookies = new Cookies();
axios.interceptors.request.use(
  config => {
      config.headers.token = cookies.get('token');
      return config;
  },
  error => {
      return Promise.reject(error);
  }
)

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
