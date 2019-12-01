import { SET_ADDED_SALA } from "../actionTypes";

const DEFAULT_STATE = {
  newSala: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ADDED_SALA:
      return {
        ...state,
        newSala: action.newSala
      };
    default:
      return state;
  }
};
