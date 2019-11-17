import {
  SET_ZAHTEV_LOADING,
  SET_ALL_ZAHTEV,
  DELETE_ZAHTEV
} from "../actionTypes";

const DEFAULT_STATE = {
  loading: false,
  zahtevi: []
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ZAHTEV_LOADING:
      return {
        ...state,
        loading: true
      };
    case SET_ALL_ZAHTEV:
      return {
        ...state,
        loading: false,
        zahtevi: [...action.zahtevi]
      };
    case DELETE_ZAHTEV:
      return {
        ...state,
        zahtevi: [...state.zahtevi.filter(z => z.id !== action.id)]
      };
    default:
      return state;
  }
};
