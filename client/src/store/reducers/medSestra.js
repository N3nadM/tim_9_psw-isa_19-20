import {
  SET_MEDSESTRA_PROFILE,
  SET_EDIT_MEDSESTRA,
  SET_LISTA_DOSTUPNIH_SESTARA,
  SET_SESTRA_ZA_PREGLED
} from "../actionTypes";

const DEFAULT_STATE = {
  medsestra: null,
  listaDostupnihSestara: [],
  sestraZaPregled: null
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
    case SET_LISTA_DOSTUPNIH_SESTARA:
      return {
        listaDostupnihSestara: action.listaDostupnihSestara
      };
    case SET_SESTRA_ZA_PREGLED:
      return {
        ...state,
        sestraZaPregled: action.sestraZaPregled
      };

    default:
      return state;
  }
};
