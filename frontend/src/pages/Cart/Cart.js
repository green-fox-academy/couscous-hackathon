import React, { useEffect, useState } from 'react';
import './Cart.css';
import { useSelector, useDispatch } from 'react-redux';
import { saveAllCart } from '../../actions/cartActions';
import CartList from './CartList';
import CartPrice from './CartPrice';
import Checkout from './Checkout';

const Cart = () => {
  const URL = process.env.REACT_APP_API_URL;
  const cartState = useSelector((state) => state.cartState);
  const [error, setError] = useState(null);
  const [showResult, setShowResult] = useState(false);
  const dispatch = useDispatch();

  useEffect(() => {
    try {
      const getCart = async () => {
        const response = await fetch(`${URL}/cart`);
        dispatch(saveAllCart(await response.json()));
      };
      getCart();
    } catch (error) {
      console.log(error.message);
      setError(error.message);
    }
  }, [dispatch]);

  const handleShow = () => setShowResult(true);

  return (
    <div className="cart-container">
      <h2>Couscous Fox Hospital Shopping Cart:</h2>
      <div className="cart">
        {error && <div>{error}</div>}
        {cartState.cart &&
          cartState.cart.map((item) => (
            <div className="cart-componens" key={item.id}>
              <CartList item={item} />
              <CartPrice item={item} />
            </div>
          ))}
        <div className="go-checkout">
          <button onClick={handleShow}>Proceed to Checkout</button>
        </div>
      </div>

      <div className="checkout">
        <div className="checkout-component">
          {showResult ? <Checkout /> : null}
        </div>
      </div>
    </div>
  );
};

export default Cart;
