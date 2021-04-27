import {React, useEffect, useState } from 'react';
import './Shop.css';
import { useSelector, useDispatch } from 'react-redux';
import { saveAllItems } from '../../actions/itemActions';
import Item from '../../pages/Item/Item';
import { Collapse } from 'antd';

//import { Pagination } from 'antd';
//import 'antd/dist/antd.css';

const { Panel } = Collapse;
const backendUrl = process.env.REACT_APP_BACKENDURL;
//const url = `${backendUrl}/item`;
const url2 = `https://fakestoreapi.com/products`;

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
      const response = fetch(url2)
      .then(response => response.json())
      .then(response => dispatch(saveAllItems(response)))
      if (response.status !== 200) {
        throw Error(response.error);
      }
    } catch (error) {
      console.log(error.message);
      setError(error.message);
  }
  },[dispatch])//page, pageSize
  
  function callback(key) {
  console.log(key);
}

const text = `
  A dog is a type of domesticated animal.
  Known for its loyalty and faithfulness,
  it can be found as a welcome guest in many households across the world.
`;
  
  return (
    <div className="store-wrapper">
      <div className="searchbar">
        <Collapse defaultActiveKey={['1']} onChange={callback}>
          <Panel header="Filter by name" key="1">
            <p>{text}</p>
          </Panel>
          <Panel header="Filter by price" key="2">
            <p>{text}</p>
          </Panel>
          <Panel header="Filter by description" key="3">
            <p>{text}</p>
          </Panel>
        </Collapse>
      </div>
      <div className="store-container">
        <div className="welcome">
        <h2>Welcome! If you are looking for a good cause, please consider helping us to rehabilitate and foster our foxes for release back into the wild. Your purchase will provide basic medicine for lifesaving treatment - and you will have a cute fox buddy too!
        </h2>
        </div>
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
    </div>
  );
};

export default Shop;
