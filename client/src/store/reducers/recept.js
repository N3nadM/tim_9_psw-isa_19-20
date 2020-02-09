import { SET_RECEPTI, SET_OVEREN_RECEPT } from "../actionTypes";

const DEFAULT_STATE = {
  recepti: [],
  overenRecept: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_RECEPTI:
      return {
        ...state,
        recepti: action.recepti
      };
    case SET_OVEREN_RECEPT:
      return {
        ...state,
        overenRecept: action.overenRecept,
        recepti: state.recepti.filter(r => r.id !== action.overenRecept.id)
      };
    default:
      return state;
  }
};
