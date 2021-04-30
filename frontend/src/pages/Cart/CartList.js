import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { saveAllCart } from '../../actions/cartActions';

const CartList = (props) => {
  const { item } = props;
  const URL = process.env.REACT_APP_API_URL;
  const dispatch = useDispatch();
  const cartId = useSelector((state) => state.cartState.cart_id);

  const handleDelete = () => {
    try {
      const deleteCart = async () => {
        const response = await fetch(`${URL}/cart/` + item.id, {
          method: 'DELETE',
          headers: {
            cart_id: cartId,
          },
        });
        dispatch(saveAllCart(await response.json()));
      };
      deleteCart();
    } catch (error) {
      console.log(error.message);
    }
  };

  return (
    <div className="cart-info">
      <div className="cart-image">
        <img src={item.image} alt={item.title} width="100%" />
      </div>
      {/* <div className="cart-title">{item.title}</div>
      <div className="cart-price">{item.price} Ft</div> */}
      <div className="cart-amount">{item.user_amount} db</div>
      <button className="cart-delete" onClick={handleDelete} id={item.id}>
        Delete
      </button>
    </div>
  );
};

export default CartList;
