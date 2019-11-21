import { SET_ZDRKARTON } from "../actionTypes";

const DEFAULT_STATE = {
  zdrKarton: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ZDRKARTON:
      return {
        zdrKarton: action.zdrKarton
      };
    default:
      return state;
  }
};
