import { BrowserRouter, Route, Switch} from 'react-router-dom';
import Home from './routes/Home';
import LoginForm from './routes/LoginForm';
import Navigation from './components/Navigation';
import Board from './routes/Board';
import SingUpForm from './routes/SingUpForm';
import BoardWrite from './routes/BoardWrite';
import Detail from './routes/Detail';

function App() {

  return (
      <BrowserRouter >
        <Navigation />
          <Route path="/" exact={true} component={Home} />
          <Switch>
            <Route path="/Board" component={Board} />
            <Route path="/Board:page" component={Board} />
            <Route path="/Write" component={BoardWrite} />
            <Route path="/Write:id" component={BoardWrite} />
          </Switch>
          <Route path="/Login" component={LoginForm} />
          <Route path="/SingUp" component={SingUpForm} />
          <Route path="/Detail:id" component={Detail} />
      </BrowserRouter >
  );
}

export default App;
