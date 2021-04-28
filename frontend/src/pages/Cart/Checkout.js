import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useHistory } from 'react-router';

const Checkout = () => {
  const [username, setUserName] = useState('');
  const [email, setEmail] = useState('');
  const [address, setAddress] = useState('');
  const [inputStatus, setInputStatus] = useState('checkout-input-OK');
  const [error, setError] = useState(null);
  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const userData = { username, email, address };
    const URL = process.env.REACT_APP_API_URL;

    try {
      const response = await fetch(`${URL}/checkout`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(userData),
      });
      const responseBody = await response.json();
      if (response.status !== 200) {
        throw Error(responseBody.error);
      }
      history.push('/success');
    } catch (error) {
      console.log(error.message);
      setError(error.message);
      setInputStatus('checkout-input-ERROR');
    }
  };

  return (
    <div className="checkout-info">
      <h2>Couscous Fox Hospital Checkout:</h2>
      <form className="checkout-form" onSubmit={handleSubmit}>
        <input
          className={inputStatus}
          type="text"
          required
          value={username}
          minLength="3"
          placeholder="username"
          onChange={(e) => {
            setUserName(e.target.value);
            setInputStatus('checkout-input-OK');
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
            setInputStatus('checkout-input-OK');
            setError(null);
          }}
        />
        <input
          className={inputStatus}
          type="text"
          required
          value={address}
          placeholder="address"
          onChange={(e) => {
            setAddress(e.target.value);
            setInputStatus('checkout-input-OK');
            setError(null);
          }}
        />
        <div className="checkout-message"></div>
        <div className="errorBox">
          {error && <div className="input-error-message">{error}</div>}
        </div>
        <button>Checkout</button>
      </form>
    </div>
  );
};

export default Checkout;
