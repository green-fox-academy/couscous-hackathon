import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Cart from './pages/Cart/Cart';
import Header from './parts/Header/Header';
import Item from './pages/Item/Item';
import Login from '../src/pages/Login/Login';
import Register from './pages/Register/Register';
import Shop from './pages/Shop/Shop';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <Header />
        <Switch>
          <Route path="/item">
            <Item />
          </Route>
          <Route exact path="/login">
            <Login />
          </Route>
          <Route exact path="/register">
            <Register />
          </Route>
          <Route path="/" component={Shop} />
          <Route exact path="/cart">
            <Cart />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
