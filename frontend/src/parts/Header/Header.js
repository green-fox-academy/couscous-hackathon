import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';
import Cookies from 'universal-cookie';
import './Header.css';
import deleteStoreAction from '../../actions/deleteStore';

const Header = () => {
  const cookie = new Cookies();
  const token = useSelector((state) => state.login.token);
  const accessToken = new Cookies().get('accessToken');
  const userName = useSelector((state) => state.login.data.userName);
  const dispatch = useDispatch();

  const handleLogoutClick = () => {
    cookie.remove('accessToken');
    dispatch(deleteStoreAction());
  };

  const setHeaderToLogin = (
    <nav className="header">
      <h1>Hackathon Webshop</h1>
      <div className="headerLinks">
        <Link to="/login">Login</Link>
        <Link to="/register">Register</Link>
        <Link to="/main/cart">Cart</Link>
      </div>
    </nav>
  );

  const setHeaderToMain = (
    <nav className="header">
      <Link to="/">
        <h1>Welcome {userName}!</h1>
      </Link>
      <div className="headerLinks">
        <Link to="/login" onClick={handleLogoutClick}>
          Logout
        </Link>
        <Link to="/main/cart">Cart</Link>
      </div>
    </nav>
  );
  return token || accessToken ? setHeaderToMain : setHeaderToLogin;
};

export default Header;