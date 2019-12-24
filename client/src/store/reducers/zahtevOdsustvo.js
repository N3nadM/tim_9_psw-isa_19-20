import {
  SET_ZAHTEV_ODSUSTVO,
  SET_ZAHTEVI_ADMIN_ODSUSTVO,
  SET_ODBIJEN_ZAHTEV_ODSUSTVO
} from "../actionTypes";

const DEFAULT_STATE = {
  zahtevOdsustvo: null,
  listaZahtevaOdsustvo: null,
  odbijenZahtev: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ZAHTEV_ODSUSTVO:
      return {
        ...state,
        zahtevOdsustvo: action.zahtevOdsustvo
      };
    case SET_ZAHTEVI_ADMIN_ODSUSTVO:
      return {
        ...state,
        listaZahtevaOdsustvo: action.listaZahtevaOdsustvo
      };
    case SET_ODBIJEN_ZAHTEV_ODSUSTVO:
      return {
        ...state,
        odbijenZahtev: action.odbijenZahtev,
        listaZahtevaOdsustvo: state.listaZahtevaOdsustvo.filter(
          zahtev => zahtev.id !== action.odbijenZahtev.id
        )
      };
    default:
      return state;
  }
};
