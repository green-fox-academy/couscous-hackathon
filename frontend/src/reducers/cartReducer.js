import { GET_CART, GENERATE_CART_ID } from '../constans/actionTypes';

const INITIAL_STATE = {
  cart: [],
  cart_id: '',
};

const cartReducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case GET_CART:
      return {
        ...state,
        cart: action.payload,
      };
    case GENERATE_CART_ID:
      return {
        ...state,
        cart_id: action.payload,
      };
    default:
      return state;
  }
};

export default cartReducer;
