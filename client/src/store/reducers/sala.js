import {
  SET_ADDED_SALA,
  SET_LISTA_SALA,
  SET_LISTA_DOSTUPNIH_SALA,
  SET_SALA_ZA_PREGLED,
  SET_SALE_KOJE_SE_MOGU_OBRISATI,
  SET_OBRISANA_SALA,
  SET_SALA_ZA_IZMENU
} from "../actionTypes";

const DEFAULT_STATE = {
  newSala: null,
  listaSala: null,
  listaDostupnihSala: null,
  salaZaPregled: null,
  saleZaBrisanje: null,
  obrisanaSala: null,
  salaZaIzmeu: null
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
    case SET_SALE_KOJE_SE_MOGU_OBRISATI:
      return {
        ...state,
        saleZaBrisanje: action.saleZaBrisanje
      };
    case SET_OBRISANA_SALA:
      return {
        ...state,
        obrisanaSala: action.obrisanaSala,
        saleZaBrisanje: state.saleZaBrisanje.filter(
          s => s.id != action.obrisanaSala.id
        )
      };
    case SET_SALA_ZA_IZMENU:
      return {
        ...state,
        salaZaIzmeu: action.salaZaIzmeu
      };
    default:
      return state;
  }
};
