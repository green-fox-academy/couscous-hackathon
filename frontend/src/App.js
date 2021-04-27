import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Login from '../src/pages/Login/Login';
import Header from './parts/Header/Header';
import Register from './pages/Register/Register';
import Shop from './pages/Shop/Shop';

function App() {
  return (
    <Router>
      <div className="App">
        <Header />
        <Switch>
          <Route exact path="/login">
            <Login />
          </Route>
          <Route exact path="/register">
            <Register />
          </Route>
          <Route exact path="/" component={Shop} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;
