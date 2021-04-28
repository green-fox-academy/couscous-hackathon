import { GET_CART } from '../constans/actionTypes';

export function saveAllCart(payload) {
  return {
    type: GET_CART,
    payload,
  };
}
