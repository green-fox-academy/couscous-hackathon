import {React, useEffect, useState } from 'react';
import './Shop.css';
import { useSelector, useDispatch } from 'react-redux';
import { saveAllItems } from '../../actions/itemActions';
import Item from '../../pages/Item/Item';
//import { Pagination } from 'antd';
//import 'antd/dist/antd.css';

const backendUrl = process.env.REACT_APP_BACKENDURL;
const url = `${backendUrl}/item`;
//const url2 = `https://fakestoreapi.com/products?limit=${pageSize}`;

const Shop = () => {
  
  const reducerItemState = useSelector(state => state.itemState);
  const [error, setError] = useState(null);
  const dispatch = useDispatch();

  //const [page, setPage] = useState(1);
  //const onChange =(page) =>{
  //  setPage(page);
  //}
  //const [pageSize, setPageSize] = useState(6);
  //const onShowSizeChange = (current, size) => {
  //  setPageSize(size);
  //}

  useEffect(() => {
    try {
      const response = fetch(url)
      .then(response => response.json())
      .then(response => dispatch(saveAllItems(response)))
      if (response.status !== 200) {
        throw Error(response.error);
      }
    } catch (error) {
      console.log(error.message);
      setError(error.message);
  }
  },[])//page, pageSize
  
    
  console.log(reducerItemState.items);
  return (
    <div className="store-container">
      <h2>Welcome to...</h2>
      <div className="store">
        {error && <div>{error}</div>}
        {reducerItemState.items &&
          reducerItemState.items.map(item => (
            <div className="items" key={item.id}>
              <Item item={ item } />
            </div>
          ))}
      </div>
      {/*<Pagination total={ 70 } pageSizeOptions={ [6,12,24,48] } onShowSizeChange={ onShowSizeChange } onChange={ onChange } />;*/ }
    </div>
  );
};

export default Shop;
