import { GET_CART } from '../constans/actionTypes';

const INITIAL_STATE = {
  cart: [],
};

const cartReducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case GET_CART:
      return {
        cart: action.payload,
      };
    default:
      return state;
  }
};

export default cartReducer;
