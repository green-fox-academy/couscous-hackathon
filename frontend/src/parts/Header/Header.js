import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';
import Cookies from 'universal-cookie';
import './Header.css';
import deleteStoreAction from '../../actions/deleteStore';
import login2 from '../../../src/assets/login2.png'
import signup2 from '../../../src/assets/signup2.png'
import cart2 from '../../../src/assets/cart2.png'

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
      <h1>Welcome to Couscous Fox Sanctuary!</h1>
      <div className="headerLinks">
        <Link to="/login"><img
              className="pawicon"
              src={login2}
              alt="paw icon"
              height="60px"
            /></Link>
        <Link to="/register"><img
              className="pawicon"
              src={signup2}
              alt="paw icon"
              height="60px"
            /></Link>
        <Link to="/main/cart"><img
              className="pawicon"
              src={cart2}
              alt="paw icon"
              height="60px"
            /></Link>
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
        <Link to="/cart">Cart</Link>
      </div>
    </nav>
  );
  return token || accessToken ? setHeaderToMain : setHeaderToLogin;
};

export default Header;
