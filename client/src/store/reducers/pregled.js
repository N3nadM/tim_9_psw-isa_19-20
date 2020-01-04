import {
  SET_PREGLEDI,
  SET_PREGLEDI_ZA_SALU,
  SET_LISTA_PREDEFINISANIH_PREGLEDA,
  SET_FILTERED_PREGLEDI,
  SET_FILTERED_PREDEFINISANI,
  SET_PREGLEDI_LEKAR,
  SET_PREGLEDI_SESTRA,
  SET_PREGLEDI_LEKAR_ODMOR,
  SET_PREGLEDI_SESTRA_ODMOR,
  SET_ZAVRSENI_PREGLEDI
} from "../actionTypes";

const DEFAULT_STATE = {
  pregledi: [],
  preglediZaSalu: [],
  listaPredefinisanih: [],
  preglediKodSestre: [],
  preglediKodLekara: [],
  preglediKodLekaraOdmor: [],
  preglediKodSestreOdmor: [],
  zavrseniPregledi: []
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_PREGLEDI:
      return {
        ...state,
        pregledi: action.pregledi.sort(
          (a, b) => new Date(b.datum) - new Date(a.datum)
        )
      };
    case SET_PREGLEDI_ZA_SALU:
      return {
        ...state,
        preglediZaSalu: action.preglediZaSalu.sort(
          (a, b) => new Date(b.datum) - new Date(a.datum)
        )
      };
    case SET_LISTA_PREDEFINISANIH_PREGLEDA:
      return {
        ...state,
        listaPredefinisanih: action.listaPredefinisanih
      };
    case SET_FILTERED_PREGLEDI:
      return {
        ...state,
        pregledi: state.pregledi.filter(p => p.id !== action.id)
      };
    case SET_FILTERED_PREDEFINISANI:
      return {
        ...state,
        listaPredefinisanih: state.listaPredefinisanih.filter(
          p => p.id !== action.id
        )
      };
    case SET_PREGLEDI_SESTRA:
      return {
        ...state,
        preglediKodSestre: action.preglediKodSestre
      };
    case SET_PREGLEDI_LEKAR:
      return {
        ...state,
        preglediKodLekara: action.preglediKodLekara
      };
    case SET_PREGLEDI_LEKAR_ODMOR:
      return {
        ...state,
        preglediKodLekaraOdmor: action.preglediKodLekaraOdmor
      };
    case SET_PREGLEDI_SESTRA_ODMOR:
      return {
        ...state,
        preglediKodSestreOdmor: action.preglediKodSestreOdmor
      };
    case SET_ZAVRSENI_PREGLEDI:
      return {
        ...state,
        zavrseniPregledi: action.zavrseniPregledi
      };
    default:
      return state;
  }
};
