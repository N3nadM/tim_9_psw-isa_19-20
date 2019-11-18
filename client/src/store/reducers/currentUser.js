import { SET_CURRENT_USER, SET_ERROR, SET_REAL_KORISNIK } from "../actionTypes";

const DEFAULT_STATE = {
  isAuthenticated: false,
  user: {},
  korisnik: null,
  error: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_CURRENT_USER:
      return {
        ...state,
        isAuthenticated: !!Object.keys(action.user).length,
        user: action.user,
        error: null
      };
    case SET_ERROR:
      return {
        ...state,
        error: action.error
      };
    case SET_REAL_KORISNIK:
      return {
        ...state,
        korisnik: action.korisnik
      };
    default:
      return state;
  }
};
