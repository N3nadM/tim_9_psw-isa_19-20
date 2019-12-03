import {
  SET_ALL_TIPOVI_PREGLEDA,
  SET_ADDED_TIP_PREGLEDA
} from "../actionTypes";

const DEFAULT_STATE = {
  tipoviPregleda: null,
  newTipPregleda: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ALL_TIPOVI_PREGLEDA:
      return {
        ...state,
        tipoviPregleda: action.tipoviPregleda
      };
    case SET_ADDED_TIP_PREGLEDA:
      return {
        ...state,
        newTipPregleda: action.newTipPregleda
      };
    default:
      return state;
  }
};
