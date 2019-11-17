import { SET_CURRENT_USER, SET_ERROR, SET_ZAHTEV } from "../actionTypes";

const DEFAULT_STATE = {
  isAuthenticated: false,
  user: {},
  error: null,
  zahtev: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_CURRENT_USER:
      return {
        isAuthenticated: !!Object.keys(action.user).length,
        user: action.user,
        error: null
      };
    case SET_ZAHTEV:
      return {
        ...state,
        zahtev: action.zahtev
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
