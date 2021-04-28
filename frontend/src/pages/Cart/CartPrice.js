import React, { useState } from 'react';

const CartPrice = (props) => {
  const { item } = props;

  return (
    <div className="cart-price">
      <div className="cart-title">{item.title}</div>
      <div className="cart-price">{item.price} Ft</div>
      <div className="cart-amount">{item.user_amount} db</div>
      <div className="cart-final-price">{item.final_price} Ft</div>
      <div className="cart-final-price">{}</div>
    </div>
  );
};

export default CartPrice;
