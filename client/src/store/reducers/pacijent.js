import {
  SET_PACIJENT_PROFILE,
  SET_EDIT_PACIJENT,
  SET_PACIJENT_ZDR
} from "../actionTypes";

const DEFAULT_STATE = {
  pacijent: null,
  pacijenti: null,
  zdrKarton: null
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
    case SET_PACIJENT_ZDR:
      return {
        ...state,
        zdrKarton: action.zdrKarton
      };

    default:
      return state;
  }
};
