import { SET_RECEPTI, SET_OVEREN_RECEPT } from "../actionTypes";

const DEFAULT_STATE = {
  recepti: [],
  overenRecept: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_RECEPTI:
      return {
        recepti: action.recepti
      };
    case SET_OVEREN_RECEPT:
      return {
        overenRecept: action.overenRecept
      };
    default:
      return state;
  }
};
