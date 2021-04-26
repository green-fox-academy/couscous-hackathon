<<<<<<< HEAD
import { React, useEffect, useState } from 'react';
=======
import React, {useEffect, useState } from 'react';
>>>>>>> 2c8e24a (parent a110fd8e6841b7d96639433f98d1eb502db2da5b)
import './Shop.css';
import { useSelector, useDispatch } from 'react-redux';
import { saveAllItems } from '../../actions/itemActions';
import ItemCard from '../../components/ItemCard/ItemCard';
import { Collapse } from 'antd';
import Tilt from 'react-tilt';
import { Pagination } from 'antd';
import 'antd/dist/antd.css';

const { Panel } = Collapse;
//const backendUrl = process.env.REACT_APP_API_URL;
//const url = `${backendUrl}/item`;
//const url2 = `https://fakestoreapi.com/products`;

const Shop = () => {
<<<<<<< HEAD
  const reducerItemState = useSelector((state) => state.itemState);
=======

  const reducerItemState = useSelector(state => state.itemState);
>>>>>>> 2c8e24a (parent a110fd8e6841b7d96639433f98d1eb502db2da5b)
  const [error, setError] = useState(null);
  const dispatch = useDispatch();

  const [page, setPage] = useState(1);
  const onChange = (page) => {
    setPage(page);
  };
  const [pageSize, setPageSize] = useState(6);
  const onShowSizeChange = (current, size) => {
    setPageSize(size);
  };

  useEffect(() => {
    try {
      const response = fetch(
        `http://localhost:8080/item?page=${page - 1}&pageSize=${pageSize}`
      )
        .then((response) => response.json())
        .then((response) => dispatch(saveAllItems(response)));
      if (response.status !== 200) {
        throw Error(response.error);
      }
    } catch (error) {
      console.log(error.message);
      setError(error.message);
<<<<<<< HEAD
    }
  }, [page, pageSize, dispatch]);

  function callback(key) {
    console.log(key);
  }
=======
  }
  },[page, pageSize, dispatch])

  function callback(key) {
  console.log(key);
}
>>>>>>> 2c8e24a (parent a110fd8e6841b7d96639433f98d1eb502db2da5b)

  return (
    <div className="store-wrapper">
      <div className="searchbar">
        <Collapse defaultActiveKey={['1']} onChange={callback}>
          <Panel header=" Filter by name" key="1">
            <form className="form">
              <label for="name">Name</label>
              <input
                type="text"
                id="name"
                name="name"
                autoComplete="off"
              ></input>
            </form>
          </Panel>
          <Panel header=" Filter by price" key="2">
            <form className="form">
              <label for="price">Price min</label>
              <input
                type="number"
                id="price"
                name="price"
                autoComplete="off"
              ></input>
            </form>
          </Panel>
          <Panel header=" Filter by description" key="3">
            <form className="form">
              <label for="category">Category</label>
              <input
                type="text"
                id="category"
                name="category"
                autoComplete="off"
              ></input>
            </form>
          </Panel>
        </Collapse>
        <button>Search</button>
      </div>
      <div className="store-container">
        <div className="store">
          <div className="welcome">
            <h2>
              Welcome! If you are looking for a good cause, please consider
              helping us to rehabilitate and foster our foxes for release back
              into the wild. Your purchase will provide basic medicine for
              lifesaving treatment - and you will have a cute fox buddy too!
            </h2>
          </div>
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
