import {
  SET_ALL_KLINIKE,
  SET_ADDED_KLINIKA,
  SET_KLINIKA,
  SET_ALL_TIPOVI
} from "../actionTypes";

const DEFAULT_STATE = {
  klinike: null,
  newKlinika: null,
  klinika: null,
  tipoviPregleda: []
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
    case SET_KLINIKA:
      return {
        ...state,
        klinika: action.klinika
      };
    case SET_ALL_TIPOVI:
      return {
        ...state,
        tipoviPregleda: action.tipoviPregleda
      };
    default:
      return state;
  }
};
