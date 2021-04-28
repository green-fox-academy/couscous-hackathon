import {React, useEffect, useState } from 'react';
import './Shop.css';
import { useSelector, useDispatch } from 'react-redux';
import { saveAllItems } from '../../actions/itemActions';
import ItemCard from '../../components/ItemCard/ItemCard';
import { Collapse } from 'antd';
import { Pagination } from 'antd';
import 'antd/dist/antd.css';
import { Link } from 'react-router-dom';

const { Panel } = Collapse;
const backendUrl = process.env.REACT_APP_API_URL;
//const url2 = `https://fakestoreapi.com/products`;

const Search = (props) => {
  
  const reducerItemState = useSelector(state => state.itemState);
  const [error, setError] = useState(null);
  const dispatch = useDispatch();

  const [page, setPage] = useState(1);
  const onChange =(page) =>{
    setPage(page);
  }
  const [pageSize, setPageSize] = useState(6);
  const onShowSizeChange = (current, size) => {
    setPageSize(size);
  }
  const [name, setName] = useState('');

  const url = `${backendUrl}/item?page=${page-1}&pageSize=${pageSize}`
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
  },[page, pageSize, dispatch, url])
  
  function callback(key) {
  console.log(key);
}
  const onClick = (event) => {
    console.log('buttonclick')
    console.log(name)
    event.preventDefault();
    const searchUrl = `${backendUrl}/item?page=${page - 1}&pageSize=${pageSize}&&search=title:${name}`
    try {
      const response = fetch(searchUrl)
        //.then(response => console.log(response))
        .then(response => response.json())
        .then(response => dispatch(saveAllItems(response)))
      if (response.status !== 200) {
        throw Error(response.error);
      }
    } catch (error) {
      console.log(error.message);
      setError(error.message);
    }
  }
  return (
    <div className="store-wrapper">
      <div className="searchbar">
        <form className="form">
          <Collapse defaultActiveKey={['1']} onChange={callback}>
            <Panel header=" Filter by name" key="1">
              <label for="name">Name</label>
              <input
                type="text"
                id="name"
                name="name"
                autoComplete="off"
                placeholder="name"
                onChange={ (e) => {
                  setName(e.target.value);
                
                }}></input>
            </Panel>
            <Panel header=" Filter by price" key="2">
              
                <label for="price">Price min</label>
                <input type="number" id="price" name="price" autoComplete="off"></input>
            
            </Panel>
            <Panel header=" Filter by description" key="3">
                        <label for="category">Category</label>
                <input type="text" id="category" name="category" autoComplete="off"></input>
            </Panel>
          </Collapse>
            <button onClick={ onClick }>Search
              <Link to="/search"></Link>
            </button>
        </form>
      </div>
      <div className="store-container">
        <div className="store">
          <div className="welcome">
            <h2>Welcome! If you are looking for a good cause, please consider helping us to rehabilitate and foster our foxes for release back into the wild. Your purchase will provide basic medicine for lifesaving treatment - and you will have a cute fox buddy too!
            </h2>
          </div>
          {error && <div>{error}</div>}
          {reducerItemState.items &&
            reducerItemState.items.map(item => (
              <div className="items" key={item.id}>
                <ItemCard item={ item } />
              </div>
            ))}
        <div className="pagination">
        { <Pagination total={ 70 } pageSizeOptions={ [6, 12, 24, 48] } onShowSizeChange={ onShowSizeChange } onChange={ onChange } /> }
        </div>
        </div>
      </div>
    </div>
  );
};

export default Search;
