import React, { useEffect, useState } from 'react';
import './Cart.css';
import { useSelector, useDispatch } from 'react-redux';
import { saveAllCart } from '../../actions/cartActions';
import CartList from './CartList';
import CartPrice from './CartPrice';
import { useHistory } from 'react-router';

const Cart = () => {
  const URL = process.env.REACT_APP_API_URL;
  const cartState = useSelector((state) => state.cartState);
  const [error, setError] = useState(null);
  const dispatch = useDispatch();
  const history = useHistory();
  const userToken = useSelector((state) => state.login.token);
  const cartId = useSelector((state) => state.cartState.cart_id);
  console.log(userToken);

  useEffect(() => {
    try {
      const getCart = async () => {
        const response = await fetch(`${URL}/cart`, {
          method: 'GET',
          headers: {
            cart_id: cartId,
          },
        });
        dispatch(saveAllCart(await response.json()));
      };
      getCart();
    } catch (error) {
      console.log(error.message);
      setError(error.message);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [dispatch]);

  const handleCheckToken = () => {
    if (!userToken) {
      history.push('/login');
    } else {
      // history.push('');
    }
  };

  return (
    <div className="cart-container">
      <h2>Couscous Fox Hospital Shopping Cart:</h2>
      <div className="cart">
        {error && <div>{error}</div>}
        {cartState.cart.item_list &&
          cartState.cart.item_list.map((item) => (
            <div className="cart-componens" key={item.id}>
              <CartList item={item} />
              <CartPrice item={item} />
            </div>
          ))}
        <div className="go-checkout">
          <button onClick={handleCheckToken}>Proceed to Checkout</button>
        </div>
      </div>
    </div>
  );
};

export default Cart;
