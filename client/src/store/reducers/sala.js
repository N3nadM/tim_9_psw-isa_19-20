import {
  SET_ADDED_SALA,
  SET_LISTA_SALA,
  SET_LISTA_DOSTUPNIH_SALA,
  SET_SALA_ZA_PREGLED
} from "../actionTypes";

const DEFAULT_STATE = {
  newSala: null,
  listaSala: null,
  listaDostupnihSala: null,
  salaZaPregled: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ADDED_SALA:
      return {
        ...state,
        newSala: action.newSala
      };
    case SET_LISTA_SALA:
      return {
        ...state,
        listaSala: action.listaSala
      };
    case SET_LISTA_DOSTUPNIH_SALA:
      return {
        ...state,
        listaDostupnihSala: action.listaDostupnihSala
      };
    case SET_SALA_ZA_PREGLED:
      return {
        ...state,
        salaZaPregled: action.salaZaPregled
      };
    default:
      return state;
  }
};
