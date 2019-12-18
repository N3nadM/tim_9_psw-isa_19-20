import { SET_DIJAGNOZA } from "../actionTypes";

const DEFAULT_STATE = {
  dijagnoza: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_DIJAGNOZA:
      return {
        ...state,
        dijagnoza: action.dijagnoza
      };
    default:
      return state;
  }
};
