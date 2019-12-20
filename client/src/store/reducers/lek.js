import { SET_LISTA_LEKOVA, SET_LEK } from "../actionTypes";

const DEFAULT_STATE = {
  lekovi: [],
  lek: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_LISTA_LEKOVA:
      return {
        ...state,
        lekovi: action.lekovi
      };
    case SET_LEK:
      return {
        ...state,
        lek: action.lek
      };
    default:
      return state;
  }
};
