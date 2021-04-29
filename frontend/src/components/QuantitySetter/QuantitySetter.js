import React, { useState } from 'react';
import './QuantitySetter.css';

const QuantitySetter = () => {
  const [quantity, setQuantity] = useState(1);

  const handleMinusClick = (e) => {
    e.preventDefault();
    if (quantity > 1) {
      setQuantity(quantity - 1);
    }
  }

  const handlePlusClick = (e) => {
    e.preventDefault();
    setQuantity(quantity + 1);
  }

  return (
    <form className="quantity-wrapper">
      <label className="quantity-label" htmlFor="quantity">Quantity</label>
      <div className="quantity-button dec-button" onClick={handleMinusClick}>-</div>
      <input
        id="quantity"
        type="text"
        name="quantity"
        size="3"
        value={quantity}
        onChange={(e) => {
          setQuantity(Number(e.target.value));
        }}
      />
      <div className="quantity-button inc-button" onClick={handlePlusClick}>+</div>
    </form>
  )
}

export default QuantitySetter;
