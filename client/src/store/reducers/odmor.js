import { SET_ODMOR } from "../actionTypes";

const DEFAULT_STATE = {
  odmor: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ODMOR:
      return {
        odmor: action.odmor
      };
    default:
      return state;
  }
};
