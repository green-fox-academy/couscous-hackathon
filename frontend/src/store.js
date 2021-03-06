import { createStore, applyMiddleware, combineReducers } from 'redux';
import thunk from 'redux-thunk';
import { composeWithDevTools } from 'redux-devtools-extension';
import loginReducer from './reducers/loginReducer';
import itemReducer from './reducers/itemReducer';
import cartReducer from './reducers/cartReducer';

const rootReducer = combineReducers({
  login: loginReducer,
  itemState: itemReducer,
  cartState: cartReducer,
});

const store = createStore(
  rootReducer,
  composeWithDevTools(applyMiddleware(thunk))
);

export default store;
