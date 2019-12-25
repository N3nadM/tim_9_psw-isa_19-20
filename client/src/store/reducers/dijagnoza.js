import { SET_DIJAGNOZA, SET_ALL_DIJAGNOZE } from "../actionTypes";

const DEFAULT_STATE = {
  dijagnoza: null,
  dijagnoze: []
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_DIJAGNOZA:
      return {
        ...state,
        dijagnoza: action.dijagnoza
      };
    case SET_ALL_DIJAGNOZE:
      return {
        ...state,
        dijagnoze: action.dijagnoze
      };
    default:
      return state;
  }
};
