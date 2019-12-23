import {
  SET_ZAHTEV_ODSUSTVO,
  SET_ZAHTEVI_ADMIN_ODSUSTVO
} from "../actionTypes";

const DEFAULT_STATE = {
  zahtevOdsustvo: null,
  listaZahtevaOdsustvo: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ZAHTEV_ODSUSTVO:
      return {
        zahtevOdsustvo: action.zahtevOdsustvo
      };
    case SET_ZAHTEVI_ADMIN_ODSUSTVO:
      return {
        listaZahtevaOdsustvo: action.listaZahtevaOdsustvo
      };
    default:
      return state;
  }
};
