import { BrowserRouter, Route, Switch} from 'react-router-dom';
import Home from './routes/Home';
import LoginForm from './routes/LoginForm';
import Navigation from './components/Navigation';
import Board from './routes/Board';
import SingUpForm from './routes/SingUpForm';
import BoardWrite from './routes/BoardWrite';
import Detail from './routes/Detail';
import EditPost from './routes/EditPost';
import Logout from './routes/Logout';
import Search from './routes/Search';

function App() {
  return (
      <BrowserRouter >
        <Navigation />
          <Route path="/" exact={true} component={Home} />
          <Switch>
            <Route path="/Board" component={Board} />
            <Route path="/Board:page" component={Board} />
            <Route path="/Write" component={BoardWrite} />
          </Switch>
          <Route path="/EditPost:id" component={EditPost} />
          <Route path="/Login" component={LoginForm} />
          <Route path="/SingUp" component={SingUpForm} />
          <Route path="/Detail" component={Detail} />
          <Route path="/Logout" component={Logout}/>
          <Route path="/Search/:keyword" component={Search} />
      </BrowserRouter >
  );
}

export default App;
