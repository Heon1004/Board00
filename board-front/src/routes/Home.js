import React, { useState, useEffect } from 'react';
import './Home.css';
import moment from 'moment';

function Home() {
    let now = new Date();
    const week = ['日','月','火','水','木','金','土'];
    let days = week[now.getDay()];
    let timer: any = null;
  const [time, setTime] = useState(moment());
  useEffect(() => {
    timer = setInterval(() => {
      setTime(moment());
    }, 1000);
    return () => {
      clearInterval(timer);
    };
  }, []);

   
    return (
        <div className="home">
            <div className="clock" id="clock">
                <div className="date">
                    {time.format('YYYY-MM-DD')} {days}
                </div>
                <div className="time">{time.format('HH:mm:ss')}</div>
                <div className="text">
                    This is React
                </div>
            </div>
        </div>
    )
}

export default Home
