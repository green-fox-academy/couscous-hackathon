import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import ImagesGallery from '../../components/ImageGallery/ImageGallery';
import QuantitySetter from '../../components/QuantitySetter/QuantitySetter';
import './Item.css';

const Item = () => {

  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [price, setPrice] = useState('');
  //  const [imageList, setImageList] = useState([]);

  const url = process.env.REACT_APP_API_URL;
  const location = useLocation();
  const itemId = location.pathname.slice(6);

  const getItem = async () => {
    try {
      const response = await fetch(`${url}/item/${itemId}`, {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
      });

      const responseData = await response.json();

      console.log(responseData);

      setTitle(responseData.title);
      setDescription(responseData.description);
      setPrice(responseData.price);
      //setImageList(responseData.images);

      console.log(responseData.images[0].url);

    } catch (err) {
      console.log(err);
    }
  }

  useEffect(() => {
    getItem();
  });

  const imageList = [
    "https://cdn.shopify.com/s/files/1/0416/0149/products/friendship-tree_plush_b_1024x1024.jpg?v=1596923674",
    "https://cdn.shopify.com/s/files/1/0416/0149/products/friendship-tree_plush_a_1024x1024.jpg?v=1596923672",
    "https://cdn.shopify.com/s/files/1/0416/0149/products/friendship-tree_plush_c_1024x1024.jpg?v=1596923668",
    "https://cdn.shopify.com/s/files/1/0416/0149/products/friendship-tree_plush_d_1024x1024.jpg?v=1596923676",
    "https://cdn.shopify.com/s/files/1/0416/0149/products/friendship-tree_plush_lifestyle_1024x1024.jpg?v=1596923678",
    "https://cdn.shopify.com/s/files/1/0416/0149/products/friendship-tree_room_29f15fdb-e0a3-4bef-a7ef-4aa8cd445c5d_1024x1024.jpg?v=1596923670"
  ];

  const handleClick = (e) => {
    e.preventDefault();

    const quantity = document.getElementById('quantity').value;

    alert(quantity);
  }

  return (
    <div className="item-wrapper">
      <div className="item-title">
        <h2>{title}</h2>
      </div>
      <div className="item-gallery">
        <ImagesGallery imageList={imageList}
        />
      </div>
      <div className="item-data">
        <div className="item-description">
          <p>{description}</p>
        </div>
        <div className="item-price">
          <p>${price}</p>
        </div>
        <div className="item-quantity">
          <QuantitySetter />
        </div>
        <button className="add-cart-button" onClick={handleClick}>Add to cart</button>
      </div>
    </div>
  )
}

export default Item;
