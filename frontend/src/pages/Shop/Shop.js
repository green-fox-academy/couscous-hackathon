import React, {useEffect, useState } from 'react';
import './Shop.css';
import { useSelector, useDispatch } from 'react-redux';
import { saveAllItems } from '../../actions/itemActions';
import ItemCard from '../../components/ItemCard/ItemCard';
import { Collapse } from 'antd';
import Tilt from 'react-tilt';
import { Pagination } from 'antd';
import 'antd/dist/antd.css';

const { Panel } = Collapse;
const backendUrl = process.env.REACT_APP_API_URL;


const Shop = () => {
  const reducerItemState = useSelector((state) => state.itemState);
  const [error, setError] = useState(null);
  const dispatch = useDispatch();

  const [page, setPage] = useState(1);
  const onChange = (page) => {
    setPage(page);
  };
  const [pageSize, setPageSize] = useState(6);
  const onShowSizeChange = (current, size) => {
    setPageSize(size);
  }
  const [name, setName] = useState('');
  const [min, setMin] = useState(0);
  const [max, setMax] = useState(1000);

  
  //document.addEventListener('readystatechange', (event) => {
  //  ShowAll(event)
  //});
  
    useEffect(() => {
    const url = `${backendUrl}/item?page=${page-1}&pageSize=${pageSize}`
    try {
      const response = fetch(url)
        .then((response) => response.json())
        .then((response) => dispatch(saveAllItems(response)));
      if (response.status !== 200) {
        throw Error(response.message);
      }
    } catch (error) {
      console.log(error.message);
      setError(error.message);
    }
  }, [page, pageSize, dispatch]);
   
  
  function callback(key) {
  console.log(key);
  }

  const onClick = async (event) => {
    setError(null);
    event.preventDefault();
    const searchUrl = `${backendUrl}/item?page=${page - 1}&pageSize=${pageSize}&search=title:${name}&search=price<${max}&search=price>${min}`
    try {
      const response = await fetch(searchUrl)
        if (response.status !== 200) {
          throw Error(response.error);
        }
      const responseObject = await response.json();
      dispatch(saveAllItems(responseObject));
      if (responseObject.length !== 0) {
        return responseObject
      }
      setError('We are sorry we have no items with these criterias!')
    } catch (error) {
      console.log(error);
      setError(error);
    }
  }
  const showAll = () => {
    setName('');
    setMin(0);
    setMax(1000);
  }
  return (
    <div className="store-wrapper">
      <div className="searchbar">
        <form className="form">
          
          <Collapse defaultActiveKey={['1']} onChange={callback}>
            <Panel header=" Filter by name" key="1">
              <label for="name"></label>
              <input
                type="text"
                id="name"
                name="name"
                autoComplete="off"
                onChange={ (e) => {
                  setName(e.target.value);
                } }>
              </input>
            </Panel>
            <Panel header=" Filter by price" key="2">
              <label for="min">min   </label>
              <input type="number" id="min" name="min" autoComplete="off"
                onChange={ (e) => {
                  setMin(e.target.value);
                } }>
              </input>
          
              <label for="max">max</label>
              <input type="number" id="max" name="max" autoComplete="off"
              onChange={ (e) => {
                  setMax(e.target.value);
                } }>
              </input>
            </Panel>
            <Panel header=" Filter by description" key="3">
              <label for="category">Funny  </label>
              <input type="radio" id="category" name="category" autoComplete="off"></input>
              <label for="category">Dramatic  </label>
              <input type="radio" id="category" name="category" autoComplete="off"></input>
              <label for="category">Fairy  </label>
              <input type="radio" id="category" name="category" autoComplete="off"></input>
              <label for="category"><span><span></span></span>Surreal</label>
              <input type="radio" id="category" name="category" autoComplete="off"></input>
            </Panel>
          </Collapse>
          <button onClick={ onClick }>Search</button>
          <button onClick={ showAll}>Back to all</button>
        </form>
      </div>
      <div className="store-container">
          
        <div className="store">
          {error && <div>{error}</div>}
          {reducerItemState.items &&
            reducerItemState.items.map((item) => (
              <Tilt className="Tilt" options={{ max: 25, speed: 400 }}>
                <div className="items" key={item.id}>
                  <ItemCard item={item} />
                </div>
              </Tilt>
            ))}
          <div className="pagination">
            {
              <Pagination
                total={70}
                pageSizeOptions={[6, 12, 24, 48]}
                onShowSizeChange={onShowSizeChange}
                onChange={onChange}
              />
            }
          </div>
        </div>
      </div>
    </div>
  );
};

export default Shop;
