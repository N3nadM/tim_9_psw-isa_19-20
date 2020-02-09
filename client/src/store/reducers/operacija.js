import {
  SET_OPERACIJA,
  SET_OPERACIJE,
  SET_OPERACIJE_ZA_SALU,
  SET_OPERACIJE_LEKAR,
  SET_OPERACIJE_SESTRA,
  SET_OPERACIJE_LEKAR_ODMOR,
  SET_OPERACIJE_SESTRA_ODMOR,
  SET_ZAVRSENE_OPERACIJE,
  SET_ISPRAVI_ZAPOCETA_OPERACIJA,
  SET_OPERACIJE_PRONALAZENJE_SALE,
  SET_EDIT_OPERACIJA
} from "../actionTypes";

const DEFAULT_STATE = {
  operacija: null,
  operacije: [],
  operacijeZaSalu: [],
  operacijeKodLekara: [],
  operacijeKodSestre: [],
  operacijeKodLekaraOdmor: [],
  operacijeKodSestreOdmor: [],
  zavrseneOperacije: [],
  operacijePronalazenjeSale: []
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_OPERACIJA:
      return {
        operacija: action.operacija
      };
    case SET_OPERACIJE:
      return {
        operacije: action.operacije.sort(
          (a, b) => new Date(b.datum) - new Date(a.datum)
        )
      };
    case SET_OPERACIJE_ZA_SALU:
      return {
        operacijeZaSalu: action.operacijeZaSalu.sort(
          (a, b) => new Date(b.datum) - new Date(a.datum)
        )
      };
    case SET_OPERACIJE_SESTRA:
      return {
        ...state,
        operacijeKodSestre: action.operacijeKodSestre
      };
    case SET_OPERACIJE_LEKAR:
      return {
        ...state,
        operacijeKodLekara: action.operacijeKodLekara
      };
    case SET_OPERACIJE_SESTRA_ODMOR:
      return {
        ...state,
        operacijeKodSestreOdmor: action.operacijeKodSestreOdmor
      };
    case SET_OPERACIJE_LEKAR_ODMOR:
      return {
        ...state,
        operacijeKodLekaraOdmor: action.operacijeKodLekaraOdmor
      };
    case SET_ZAVRSENE_OPERACIJE:
      return {
        ...state,
        zavrseneOperacije: action.zavrseneOperacije
      };
    case SET_ISPRAVI_ZAPOCETA_OPERACIJA:
      return {
        ...state,
        operacije: state.operacije.map(o =>
          o.id === action.ispravkaZapocetaOperacija.id
            ? action.ispravkaZapocetaOperacija
            : o
        )
      };
    case SET_OPERACIJE_PRONALAZENJE_SALE:
      return {
        ...state,
        operacijePronalazenjeSale: action.operacijePronalazenjeSale
      };
    case SET_EDIT_OPERACIJA:
      return {
        ...state,
        zavrseneOperacije: state.zavrseneOperacije.map(p =>
          p.id === action.data.id ? { ...p, izvestaj: action.data.izvestaj } : p
        )
      };
    default:
      return state;
  }
};
