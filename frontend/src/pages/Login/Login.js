import React, { useState } from 'react';
import './Login.css';
import Tilt from 'react-tilt';
import jwt from 'jsonwebtoken';
import Cookies from 'universal-cookie';
import { useHistory } from 'react-router';
import { useDispatch } from 'react-redux';
import {
  loadUserToken,
  loadUserTokenPayload,
} from '../../actions/loginActions';

const Login = () => {
  const [username, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [inputStatus, setInputStatus] = useState('login-input-OK');
  const [error, setError] = useState(null);
  const cookie = new Cookies();
  const history = useHistory();

  const dispatch = useDispatch();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const userData = { username, password };
    const URL = process.env.REACT_APP_API_URL;

    try {
      const response = await fetch(`${URL}/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(userData),
      });
      const responseBody = await response.json();
      if (response.status !== 200) {
        throw Error(responseBody.error);
      }
      const decodedJWTToken = jwt.decode(responseBody.token, {
        complete: true,
      });
      dispatch(loadUserToken(responseBody.token));
      dispatch(loadUserTokenPayload(decodedJWTToken.payload));
      cookie.set('accessToken', responseBody.token, {
        path: '/',
      });
      history.push('/main');
    } catch (error) {
      console.log(error.message);
      setError(error.message);
      setInputStatus('login-input-ERROR');
    }
  };

  return (
    <div className="container">
      <Tilt className="Tilt" options={{ max: 25, speed: 400 }}>
        <div className="box">
          <h1 className="name">Login</h1>
          <form className="login-form" onSubmit={handleSubmit}>
            <input
              className={inputStatus}
              type="text"
              required
              value={username}
              minLength="3"
              placeholder="username"
              onChange={(e) => {
                setUserName(e.target.value);
                setInputStatus('login-input-OK');
                setError(null);
              }}
            />
            <input
              className={inputStatus}
              type="password"
              required
              value={password}
              minLength="8"
              placeholder="password"
              onChange={(e) => {
                setPassword(e.target.value);
                setInputStatus('login-input-OK');
                setError(null);
              }}
            />
            <div className="errorBox">
              {error && <div className="input-error-message">{error}</div>}
            </div>
            <button className="button">Login</button>
          </form>
        </div>
      </Tilt>
    </div>
  );
};

export default Login;
