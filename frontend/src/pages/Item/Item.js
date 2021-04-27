import { Link } from 'react-router-dom';
import React from 'react';

function Item(props) {
  const { item } = props;
  
  return (
    <div className="item-info">
      <div className="item-image">
        <img src={item.image} alt="itempicture" width="100%" />
      </div>
      <div className="item-title">{item.title}</div>
      <div className="item-price">$ {item.price}</div>
      <div className="item-details">
        <Link to={`/item/${item.id}`}>See details</Link>
      </div>
    </div>
  );
}

export default Item;
