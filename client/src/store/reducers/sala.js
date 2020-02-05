import {
  SET_ADDED_SALA,
  SET_LISTA_SALA,
  SET_LISTA_DOSTUPNIH_SALA,
  SET_SALA_ZA_PREGLED,
  SET_SALE_KOJE_SE_MOGU_OBRISATI,
  SET_OBRISANA_SALA,
  SET_EDITED_SALA,
  SET_TERMINI_SALA
} from "../actionTypes";

const DEFAULT_STATE = {
  newSala: null,
  listaSala: [],
  listaDostupnihSala: [],
  salaZaPregled: "",
  saleZaBrisanje: null,
  obrisanaSala: null,
  editedSala: null,
  slobodniTerminiSala: null
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
          s => s.id !== action.obrisanaSala.id
        )
      };
    case SET_EDITED_SALA:
      return {
        ...state,
        editedSala: action.editedSala,
        saleZaBrisanje: state.saleZaBrisanje.map(l =>
          l.id === action.editedSala.id ? action.editedSala : l
        )
      };
    case SET_TERMINI_SALA:
      return {
        ...state,
        slobodniTerminiSala: action.slobodniTerminiSala
      };
    default:
      return state;
  }
};
