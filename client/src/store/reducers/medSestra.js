import { SET_MEDSESTRA_PROFILE, SET_EDIT_MEDSESTRA } from "../actionTypes";

const DEFAULT_STATE = {
  medsestra: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_MEDSESTRA_PROFILE:
      return {
        medsestra: action.medsestra
      };
    case SET_EDIT_MEDSESTRA:
      return {
        medsestra: { ...state.medsestra, korisnik: action.korisnik }
      };

    default:
      return state;
  }
};
