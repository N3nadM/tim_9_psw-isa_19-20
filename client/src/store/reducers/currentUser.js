import { SET_CURRENT_USER, SET_ERROR } from "../actionTypes";

const DEFAULT_STATE = {
  isAuthenticated: false,
  user: {},
  error: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_CURRENT_USER:
      return {
        isAuthenticated: !!Object.keys(action.user).length,
        user: action.user,
        error: null
      };
    case SET_ERROR:
      return {
        ...state,
        error: action.error
      };
    default:
      return state;
  }
};
