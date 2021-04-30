import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';
import Tilt from 'react-tilt';
import Cookies from 'universal-cookie';
import './Header.css';
import deleteStoreAction from '../../actions/deleteStore';
import logo from '../../assets/fox-logo-full.png';
import login from '../../../src/assets/login-black.png';
import login2 from '../../../src/assets/login-blue.png';
import logout from '../../../src/assets/logout-black.png';
import logout2 from '../../../src/assets/logout-blue.png';
import signup from '../../../src/assets/signup-black.png';
import signup2 from '../../../src/assets/signup-blue.png';
import cart from '../../../src/assets/cart-black.png';
import cart2 from '../../../src/assets/cart-blue.png';
import cart3 from '../../../src/assets/cart-orange.png';

const Header = () => {
  const cookie = new Cookies();
  const token = useSelector((state) => state.login.token);
  const accessToken = new Cookies().get('accessToken');
  //  const userName = useSelector((state) => state.login.data.userName);
  const dispatch = useDispatch();

  const handleLogoutClick = () => {
    cookie.remove('accessToken');
    dispatch(deleteStoreAction());
  };

  const setHeaderToLogin = (
    <nav className="header">
      <div>
        <Link to="/">
          <img className="header-logo" src={logo} alt="fox logo" />
        </Link>
      </div>
      <h1>Welcome to Couscous Fox Sanctuary!</h1>

      <h2>
        If you are looking for a good cause, please consider helping us
        rehabilitate and foster our foxes for release back into the wild. Your
        purchase will provide basic medicine for lifesaving treatment - and you
        will have a cute fox buddy too!
      </h2>

      <div className="headerLinks">
        <Tilt className="Tilt" options={{ max: 25, speed: 400 }}>
          <Link to="/login">
            <img
              className="pawicon"
              src={login}
              alt="paw icon"
              height="60px"
              onMouseEnter={(e) => (e.target.src = login2)}
              onMouseLeave={(e) => (e.target.src = login)}
            />
          </Link>
        </Tilt>
        <Tilt className="Tilt" options={{ max: 25, speed: 400 }}>
          <Link to="/register">
            <img
              className="pawicon"
              src={signup}
              alt="paw icon"
              height="60px"
              onMouseEnter={(e) => (e.target.src = signup2)}
              onMouseLeave={(e) => (e.target.src = signup)}
            />
          </Link>
        </Tilt>
        <Tilt className="Tilt" options={{ max: 25, speed: 400 }}>
          <Link to="/cart">
            <img
              className="pawicon"
              src={cart}
              alt="paw icon"
              height="60px"
              onMouseEnter={(e) => (e.target.src = cart2)}
              onMouseLeave={(e) => (e.target.src = cart)}
            />
          </Link>
        </Tilt>
      </div>
    </nav>
  );

  const setHeaderToMain = (
    <nav className="header">
      <div>
        <Link to="/">
          <img className="header-logo" src={logo} alt="fox logo" />
        </Link>
      </div>
      <h1>Welcome to Couscous Fox Sanctuary!</h1>
      <div className="headerLinks">
        <Tilt className="Tilt" options={{ max: 25, speed: 400 }}>
          <Link to="/login" onClick={handleLogoutClick}>
            <img
              className="pawicon"
              src={logout}
              alt="paw icon"
              height="60px"
              onMouseEnter={(e) => (e.target.src = logout2)}
              onMouseLeave={(e) => (e.target.src = logout)}
            />
          </Link>
        </Tilt>
        <Tilt className="Tilt" options={{ max: 25, speed: 400 }}>
          <Link to="/cart">
            <img
              className="pawicon"
              src={cart}
              alt="paw icon"
              height="60px"
              onMouseEnter={(e) => (e.target.src = cart2)}
              onMouseLeave={(e) => (e.target.src = cart)}
            />
          </Link>
        </Tilt>
      </div>
    </nav>
  );
  return token || accessToken ? setHeaderToMain : setHeaderToLogin;
};

export default Header;
