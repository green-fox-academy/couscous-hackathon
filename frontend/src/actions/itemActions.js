import {
  GET_ITEMS
} from '../constans/actionTypes';

export function saveAllItems(payload) {
  return {
    type: GET_ITEMS,
    payload,
  };
}
