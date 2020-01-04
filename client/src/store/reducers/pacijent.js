import {
  SET_PACIJENT_PROFILE,
  SET_EDIT_PACIJENT,
  SET_PACIJENT_ZDR,
  SET_PREGLEDAO_PACIJENTA
} from "../actionTypes";

const DEFAULT_STATE = {
  pacijent: null,
  pacijenti: null,
  zdrKarton: null,
  pregledanPacijent: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_PACIJENT_PROFILE:
      return {
        ...state,
        pacijent: action.pacijent
      };
    case SET_EDIT_PACIJENT:
      return {
        ...state,
        pacijent: { ...state.pacijent, korisnik: action.korisnik }
      };
    case SET_PACIJENT_ZDR:
      return {
        ...state,
        zdrKarton: action.zdrKarton
      };
    case SET_PREGLEDAO_PACIJENTA:
      return {
        ...state,
        pregledanPacijent: action.pregledanPacijent
      };

    default:
      return state;
  }
};
