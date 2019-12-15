import {
  SET_PREGLEDI,
  SET_PREGLEDI_ZA_SALU,
  SET_LISTA_PREDEFINISANIH_PREGLEDA
} from "../actionTypes";

const DEFAULT_STATE = {
  pregledi: [],
  preglediZaSalu: [],
  listaPredefinisanih: []
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_PREGLEDI:
      return {
        pregledi: action.pregledi.sort(
          (a, b) => new Date(b.datum) - new Date(a.datum)
        )
      };
    case SET_PREGLEDI_ZA_SALU:
      return {
        preglediZaSalu: action.preglediZaSalu.sort(
          (a, b) => new Date(b.datum) - new Date(a.datum)
        )
      };
    case SET_LISTA_PREDEFINISANIH_PREGLEDA:
      return {
        ...state,
        listaPredefinisanih: action.listaPredefinisanih
      };
    default:
      return state;
  }
};
