import { SET_ALL_TIPOVI_PREGLEDA } from "../actionTypes";

const DEFAULT_STATE = {
  tipoviPregleda: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ALL_TIPOVI_PREGLEDA:
      return {
        ...state,
        tipoviPregleda: action.tipoviPregleda
      };
    default:
      return state;
  }
};
