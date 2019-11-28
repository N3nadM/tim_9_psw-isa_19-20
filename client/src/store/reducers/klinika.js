import { SET_ALL_KLINIKE, SET_ADDED_KLINIKA } from "../actionTypes";

const DEFAULT_STATE = {
  klinike: null,
  newKlinika: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ALL_KLINIKE:
      return {
        ...state,
        klinike: action.klinike
      };
    case SET_ADDED_KLINIKA:
      return {
        ...state,
        newKlinika: action.newKlinika
      };
    default:
      return state;
  }
};
