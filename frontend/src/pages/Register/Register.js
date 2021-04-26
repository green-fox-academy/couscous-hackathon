import React, { useState } from 'react';
import './Register.css';
import Tilt from 'react-tilt';
import { useHistory } from 'react-router';

const Register = () => {
  const [username, setUserName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [inputStatus, setInputStatus] = useState('register-input-OK');
  const [error, setError] = useState(null);
  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const userData = { username, email, password };
    const URL = process.env.REACT_APP_API_URL;

    try {
      const response = await fetch(`${URL}/register`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(userData),
      });
      const responseBody = await response.json();
      if (response.status !== 200) {
        throw Error(responseBody.error);
      }

      history.push('/login');
    } catch (error) {
      console.log(error.message);
      setError(error.message);
      setInputStatus('register-input-ERROR');
    }
  };

  return (
    <div className="container">
      <Tilt className="Tilt" options={{ max: 25, speed: 400 }}>
        <div className="box">
          <h1 className="name">Register</h1>
          <form className="register-form" onSubmit={handleSubmit}>
            <input
              className={inputStatus}
              type="text"
              required
              value={username}
              minLength="3"
              placeholder="username"
              onChange={(e) => {
                setUserName(e.target.value);
                setInputStatus('register-input-OK');
                setError(null);
              }}
            />
            <input
              className={inputStatus}
              type="email"
              required
              value={email}
              placeholder="email"
              onChange={(e) => {
                setEmail(e.target.value);
                setInputStatus('register-input-OK');
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
                setInputStatus('register-input-OK');
                setError(null);
              }}
            />
            <div className="errorBox">
              {error && <div className="input-error-message">{error}</div>}
            </div>
            <button className="button">Sign Up</button>
          </form>
        </div>
      </Tilt>
    </div>
  );
};

export default Register;
