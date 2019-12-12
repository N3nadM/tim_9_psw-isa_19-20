import { SET_ADDED_SALA, SET_LISTA_SALA } from "../actionTypes";

const DEFAULT_STATE = {
  newSala: null,
  listaSala: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ADDED_SALA:
      return {
        ...state,
        newSala: action.newSala
      };
    case SET_LISTA_SALA:
      return {
        ...state,
        listaSala: action.listaSala
      };
    default:
      return state;
  }
};
