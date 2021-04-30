import { GET_ITEMS } from '../constans/actionTypes';

const INITIAL_STATE = {
  items: [],
};

const itemReducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case GET_ITEMS:
      localStorage.setItem('items', JSON.stringify(action.payload));
      return {
        items: action.payload,
      };
    default:
      return state;
  }
};

export default itemReducer;
