import { SET_PACIJENT_PROFILE, SET_EDIT_PACIJENT } from "../actionTypes";

const DEFAULT_STATE = {
  pacijent: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_PACIJENT_PROFILE:
      return {
        pacijent: action.pacijent
      };
    case SET_EDIT_PACIJENT:
      return {
        pacijent: { ...state.pacijent, korisnik: action.korisnik }
      };

    default:
      return state;
  }
};
