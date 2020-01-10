import {
  SET_PREGLED,
  SET_PREGLEDI,
  SET_PREGLEDI_ZA_SALU,
  SET_LISTA_PREDEFINISANIH_PREGLEDA,
  SET_FILTERED_PREGLEDI,
  SET_FILTERED_PREDEFINISANI,
  SET_PREGLEDI_LEKAR,
  SET_PREGLEDI_SESTRA,
  SET_PREGLEDI_LEKAR_ODMOR,
  SET_PREGLEDI_SESTRA_ODMOR,
  SET_ZAVRSENI_PREGLEDI,
  SET_ISPRAVI_ZAPOCET_PREGLED,
  SET_PREGLEDI_PRONALAZENJE_SALE
} from "../actionTypes";

const DEFAULT_STATE = {
  pregled: null,
  pregledi: [],
  preglediZaSalu: [],
  listaPredefinisanih: [],
  preglediKodSestre: [],
  preglediKodLekara: [],
  preglediKodLekaraOdmor: [],
  preglediKodSestreOdmor: [],
  zavrseniPregledi: [],
  preglediPronalazenjeSale: []
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_PREGLED:
      return {
        ...state,
        pregled: action.pregled
      };
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
    case SET_ISPRAVI_ZAPOCET_PREGLED:
      return {
        ...state,
        pregledi: state.pregledi.map(p =>
          p.id === action.ispravkaZapocetPregled.id
            ? action.ispravkaZapocetPregled
            : p
        )
      };
    case SET_PREGLEDI_PRONALAZENJE_SALE:
      return {
        ...state,
        preglediPronalazenjeSale: action.preglediPronalazenjeSale
      };
    default:
      return state;
  }
};
