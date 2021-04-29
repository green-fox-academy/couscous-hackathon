import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router';
import { useLocation } from 'react-router-dom';
import ImagesGallery from '../../components/ImageGallery/ImageGallery';
import QuantitySetter from '../../components/QuantitySetter/QuantitySetter';
import './Item.css';
import Cookies from 'universal-cookie';
import { useDispatch, useSelector } from 'react-redux';
import { generateCartId } from '../../actions/cartActions';

const Item = () => {
  const [imageList, setImageList] = useState([]);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [price, setPrice] = useState('');
  const [error, setError] = useState(null);
  const history = useHistory();
  const cookie = new Cookies();
  const dispatch = useDispatch();
  const cartid = useSelector((state) => state.cartState.cart_id);

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

      setTitle(responseData.title);
      setDescription(responseData.description);
      setPrice(responseData.price);
      setImageList(responseData.image_url_list);

      console.log(responseData);
    } catch (error) {
      console.log(error);
      setError(error.message);
    }
  };

  useEffect(() => {
    getItem();
  }, []);

  const handleClick = async (e) => {
    e.preventDefault();

    const quantity = document.getElementById('quantity').value;
    const itemData = { item_id: itemId, item_amount: quantity };
    console.log(itemData);

    try {
      const response = await fetch(`${url}/cart`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        //TODO: ha van sessionID add to POST method
        body: JSON.stringify(itemData),
      });
      if (!cartid) {
        function makeid(length) {
          var result = [];
          var characters =
            'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
          var charactersLength = characters.length;
          for (var i = 0; i < length; i++) {
            result.push(
              characters.charAt(Math.floor(Math.random() * charactersLength))
            );
          }
          return result.join('');
        }
        let cart_id = makeid(50);

        cookie.set('cart_id', cart_id, {
          complete: true,
        });
        dispatch(generateCartId(cart_id));
      }
      if (response.status !== 200) {
        throw Error(response.message);
      }
      history.push('/');
    } catch (error) {
      console.log(error);
      setError(error.message);
    }
  };

  return (
    <div className="item-wrapper">
      <div className="item-title">
        <h2>{title}</h2>
      </div>
      <div className="item-gallery">
        <ImagesGallery imageList={imageList} />
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
        <div className="item-errorBox">
          {error && <div className="item-error-message">{error}</div>}
        </div>
        <button className="add-cart-button" onClick={handleClick}>
          Add to cart
        </button>
      </div>
    </div>
  );
};

export default Item;
