import {
  SET_LEKAR_PROFILE,
  SET_EDIT_LEKAR,
  SET_LEKAR_LIST,
  SET_ADDED_LEKAR,
  SET_LEKARI_NA_KLINICI,
  SET_EDITED_LEKAR,
  SET_LEKAR_ZA_PREGLED,
  SET_TERMIN_ZA_PREGLED,
  SET_LEKARI_KOJI_SE_MOGU_OBRISATI,
  SET_OBRISAN_LEKAR
} from "../actionTypes";

const DEFAULT_STATE = {
  lekar: null,
  lekarList: null,
  addedLekar: null,
  listaLekaraNaKlinici: null,
  lekarZaPregled: null,
  terminZaPregled: null,
  lekariZaBrisanje: null,
  obrisanLekar: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_LEKAR_PROFILE:
      return {
        ...state,
        lekar: action.lekar
      };
    case SET_EDIT_LEKAR:
      return {
        ...state,
        lekar: { ...state.lekar, korisnik: action.korisnik }
      };
    case SET_LEKAR_LIST:
      return {
        ...state,
        lekarList: action.lekarList
      };
    case SET_ADDED_LEKAR:
      return {
        ...state,
        addedLekar: action.addedLekar
      };
    case SET_LEKARI_NA_KLINICI:
      return {
        ...state,
        listaLekaraNaKlinici: action.listaLekaraNaKlinici
      };
    case SET_EDITED_LEKAR:
      return {
        ...state,
        listaLekaraNaKlinici: state.listaLekaraNaKlinici.map(l =>
          l.id === action.lekar.id ? action.lekar : l
        )
      };
    case SET_LEKAR_ZA_PREGLED:
      return {
        ...state,
        lekarZaPregled: action.lekarZaPregled
      };
    case SET_TERMIN_ZA_PREGLED:
      return {
        ...state,
        terminZaPregled: action.terminZaPregled
      };
    case SET_LEKARI_KOJI_SE_MOGU_OBRISATI:
      return {
        ...state,
        lekariZaBrisanje: action.lekariZaBrisanje
      };
    case SET_OBRISAN_LEKAR:
      return {
        ...state,
        obrisanLekar: action.obrisanLekar,
        lekariZaBrisanje: state.lekariZaBrisanje.filter(
          l => l.id !== action.obrisanLekar.id
        )
      };
    default:
      return state;
  }
};
