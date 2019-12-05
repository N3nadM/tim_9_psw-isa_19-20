import {
  SET_LEKAR_PROFILE,
  SET_EDIT_LEKAR,
  SET_LEKAR_LIST,
  SET_ADDED_LEKAR,
  SET_LEKARI_NA_KLINICI
} from "../actionTypes";

const DEFAULT_STATE = {
  lekar: null,
  lekarList: null,
  addedLekar: null,
  listaLekaraNaKlinici: null
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
        addedLekar: action.addedLekar
      };
    case SET_LEKARI_NA_KLINICI:
      return {
        listaLekaraNaKlinici: action.listaLekaraNaKlinici
      };

    default:
      return state;
  }
};
