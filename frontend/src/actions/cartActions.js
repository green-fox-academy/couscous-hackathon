import { GENERATE_CART_ID, GET_CART } from '../constans/actionTypes';

export function saveAllCart(payload) {
  return {
    type: GET_CART,
    payload,
  };
}
export function generateCartId(payload) {
  return {
    type: GENERATE_CART_ID,
    payload,
  };
}
