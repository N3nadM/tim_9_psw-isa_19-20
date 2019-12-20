import { SET_ODSUSTVO } from "../actionTypes";

const DEFAULT_STATE = {
  odsustvo: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ODSUSTVO:
      return {
        odsustvo: action.odsustvo
      };
    default:
      return state;
  }
};
