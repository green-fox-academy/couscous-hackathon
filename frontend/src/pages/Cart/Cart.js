import React, { useEffect, useState } from 'react';
import './Cart.css';
import { useSelector, useDispatch } from 'react-redux';
import { saveAllCart } from '../../actions/cartActions';
import CartList from './CartList';

const Cart = () => {
  const URL = process.env.REACT_APP_API_URL;
  const cartState = useSelector((state) => state.cartState);
  const [error, setError] = useState(null);
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

  return (
    <div className="cart-container">
      <h2>Couscous Fox Hospital Shopping Cart:</h2>
      <div className="cart">
        {error && <div>{error}</div>}
        {cartState.cart &&
          cartState.cart.map((item) => (
            <div className="" key={item.id}>
              <CartList item={item} />
            </div>
          ))}
      </div>
    </div>
  );
};

export default Cart;
